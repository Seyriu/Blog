/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import javax.ws.rs.core.Response;
import org.forit.blog.dto.PostDTO;
import org.forit.blog.dto.UtenteDTO;
import org.forit.blog.exceptions.BlogException;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author Casper
 */
public class ImageUploadDAO {

    private static final String PROFILE_PIC = "profile-picture";
    private static final String POST_PIC = "post-picture";
    private static final long MAX_PROF_PIC_SIZE = 500;
    private static final long MAX_POST_PIC_SIZE = 800;
    private static final String PROF_PIC_PATH = "blog/profile-pictures/";
    private static final String PROF_PIC_UPLOAD_FOLDER = "H:\\Users\\Seyri\\Documents\\NetBeansProjects\\blog\\assets\\profilePictures\\";
    private static final String POST_PIC_PATH = "blog/post-pictures/";
    private static final String POST_PIC_UPLOAD_FOLDER = "H:\\Users\\Seyri\\Documents\\NetBeansProjects\\blog\\assets\\postPictures\\";
    InputStream uploadedInputStream;
    FormDataContentDisposition fileDetail;
    FormDataBodyPart body;

    public Response imageUpload(InputStream uploadedInputStream,
            FormDataContentDisposition fileDetail,
            FormDataBodyPart body,
            long id,
            String sender) {
        this.uploadedInputStream = uploadedInputStream;
        this.fileDetail = fileDetail;
        this.body = body;
        if (uploadedInputStream == null || fileDetail == null) {
            return Response.status(400).entity("Invalid form data").build();
        }
        try {
            if (sender.equals(PROFILE_PIC)) {
                saveProfilePic(id);
            } else if (sender.equals(POST_PIC)) {
                savePostPic(id);
            }
        } catch (BlogException ex) {
            System.out.println("Error encountered while saving the file: " + ex.getMessage());
        }
        return null;
    }

    private Response saveProfilePic(long userId) throws BlogException {
        try {
            createFolderIfNotExists(PROF_PIC_UPLOAD_FOLDER);
        } catch (BlogException ex) {
            return Response.status(500)
                    .entity("Can not create destination folder on server: " + ex.getLocalizedMessage())
                    .build();
        }
        try {
            UtenteDAO uDAO = new UtenteDAO();
            UtenteDTO uDTO = uDAO.loadUtenteDTO(userId);
            String oldUrl = uDTO.getImage();
            if (oldUrl != null) {
                File oldProfPic = new File(profPicPathToDirectory(oldUrl));
                oldProfPic.delete();
            }
            String fileName = uDTO.getId() + "_" + fileDetail.getFileName();
            try {
                uDAO.updateImage(PROF_PIC_PATH + fileName, userId);
                boolean isTooBig = saveToFile(uploadedInputStream, PROF_PIC_UPLOAD_FOLDER + fileName, MAX_PROF_PIC_SIZE);
                if (isTooBig == true) {
                    return Response.status(500).entity("File is too big").build();
                }
            } catch (BlogException ex) {
                throw ex;
            }
        } catch (IOException e) {
            BlogException ex = new BlogException(e);
            return Response.status(500).entity("Can not save file: " + ex.getLocalizedMessage()).build();
        }
        return null;
    }

    private Response savePostPic(long postId) throws BlogException {
        try {
            createFolderIfNotExists(POST_PIC_UPLOAD_FOLDER);
        } catch (BlogException ex) {
            return Response.status(500)
                    .entity("Can not create destination folder on server: " + ex.getLocalizedMessage())
                    .build();
        }
        try {
            PostDAO pDAO = new PostDAO();
            PostDTO pDTO = pDAO.loadPost(postId);
            String oldUrl = pDTO.getImage();
            if (oldUrl != null) {
                File oldProfPic = new File(postPicPathToDirectory(oldUrl));
                oldProfPic.delete();
            }
            String fileName = pDTO.getId() + "_" + fileDetail.getFileName();
            try {
                pDAO.updateImage(POST_PIC_PATH + fileName, postId);
                boolean isTooBig = saveToFile(uploadedInputStream, POST_PIC_UPLOAD_FOLDER + fileName, MAX_POST_PIC_SIZE);
                if (isTooBig == true) {
                    return Response.status(500).entity("File is too big").build();
                }
            } catch (BlogException ex) {
                throw ex;
            }
        } catch (IOException e) {
            BlogException ex = new BlogException(e);
            return Response.status(500).entity("Can not save file: " + ex.getLocalizedMessage()).build();
        }
        return null;
    }

    private String profPicPathToDirectory(String path) {
        return PROF_PIC_UPLOAD_FOLDER + path.substring(PROF_PIC_PATH.length());
    }

    private String postPicPathToDirectory(String path) {
        return POST_PIC_UPLOAD_FOLDER + path.substring(POST_PIC_PATH.length());
    }

    /**
     * Utility method to save InputStream data to target location/file returns
     * true if file is bigger than max size allowed
     *
     * @param inStream - InputStream to be saved
     * @param target - full path to destination file
     */
    private boolean saveToFile(InputStream inStream, String target, long maxAllowedSize)
            throws IOException {
        OutputStream out = null;
        long i = 0;
        int read = 0;
        byte[] bytes = new byte[1024];
        out = new FileOutputStream(new File(target));
        while ((read = inStream.read(bytes)) != -1 && (i <= maxAllowedSize)) {
            out.write(bytes, 0, read);
            i++;
        }
        out.flush();
        out.close();
        return i > maxAllowedSize;
    }

    /**
     * Creates a folder to desired location if it not already exists
     *
     * @param dirName - full path to the folder
     * @throws SecurityException - in case you don't have permission to create
     * the folder
     */
    private void createFolderIfNotExists(String dirName)
            throws BlogException {
        File theDir = new File(dirName);
        if (!theDir.exists()) {
            try {
                Files.createDirectories(theDir.toPath()); //theDir.mkdirs();
            } catch (IOException ex) {
                throw new BlogException(ex);
            }
        }
    }
}
