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
import org.forit.blog.dto.UtenteDTO;
import org.forit.blog.exceptions.BlogException;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author Casper
 */
public class ImageUploadDAO {

    private static final long MAX_FILE_SIZE = 800;
    private static final String PATH = "/blog/profile-pictures/";
    private static final String UPLOAD_FOLDER = "H:\\Users\\Seyri\\Documents\\NetBeansProjects\\blog\\assets\\profilePictures\\";

    public Response imageUpload(InputStream uploadedInputStream,
            FormDataContentDisposition fileDetail,
            FormDataBodyPart body,
            String email) {
        if (uploadedInputStream == null || fileDetail == null) {
            return Response.status(400).entity("Invalid form data").build();
        }

        try {
            createFolderIfNotExists(UPLOAD_FOLDER);
        } catch (BlogException ex) {
            return Response.status(500)
                    .entity("Can not create destination folder on server: " + ex.getLocalizedMessage())
                    .build();
        }
        try {
            UtenteDAO uDAO = new UtenteDAO();
            UtenteDTO uDTO = uDAO.loadUtenteDTOByEmail(email);
            String oldUrl = uDTO.getImage();
            if (oldUrl != null) {
                File oldProfPic = new File(pathToDirectory(oldUrl));
                oldProfPic.delete();
            }
            String fileName = uDTO.getId() + "_" + fileDetail.getFileName();
            try {
                uDAO.updateImage(PATH + fileName, email);
                boolean isTooBig = saveToFile(uploadedInputStream, UPLOAD_FOLDER + fileName);
                if (isTooBig == true) {
                    return Response.status(500).entity("File is too big").build();
                }
            } catch (BlogException ex) {
                System.out.println("Errore durante l'inserimento del path dell'immagine" + ex.getLocalizedMessage());
            }
        } catch (IOException e) {
            BlogException ex = new BlogException(e);
            return Response.status(500).entity("Can not save file: " + ex.getLocalizedMessage()).build();
        }

        return Response.status(200)
                .entity("File saved to " + UPLOAD_FOLDER + "and remotely accessible from " + PATH).build();
    }

    private String pathToDirectory(String path) {
        return UPLOAD_FOLDER + path.substring(PATH.length());
    }
    
    /**
     * Utility method to save InputStream data to target location/file returns
     * true if file is bigger than max size allowed
     *
     * @param inStream - InputStream to be saved
     * @param target - full path to destination file
     */
    private boolean saveToFile(InputStream inStream, String target)
            throws IOException {
        OutputStream out = null;
        long i = 0;
        int read = 0;
        byte[] bytes = new byte[1024];
        out = new FileOutputStream(new File(target));
        while ((read = inStream.read(bytes)) != -1 && (i <= MAX_FILE_SIZE)) {
            out.write(bytes, 0, read);
            i++;
        }
        out.flush();
        out.close();
        if (i > MAX_FILE_SIZE) {
            return true;
        }
        return false;
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
