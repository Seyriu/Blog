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
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author Casper
 */
public class ImageUploadDAO {

    public Response imageUpload(InputStream uploadedInputStream,
            FormDataContentDisposition fileDetail, final String SERVER_PATH) {
        if (uploadedInputStream == null || fileDetail == null) {
            return Response.status(400).entity("Invalid form data").build();
        }

        try {
            createFolderIfNotExists(SERVER_PATH);
        } catch (SecurityException se) {
            return Response.status(500)
                    .entity("Can not create destination folder on server")
                    .build();
        }
        String uploadedFileLocation = SERVER_PATH + fileDetail.getFileName();
        try {
            saveToFile(uploadedInputStream, uploadedFileLocation);
        } catch (IOException e) {
            return Response.status(500).entity("Can not save file").build();
        }
        return Response.status(200)
                .entity("File saved to " + uploadedFileLocation).build();
    }

    /**
     * Utility method to save InputStream data to target location/file
     *
     * @param inStream - InputStream to be saved
     * @param target - full path to destination file
     */
    private void saveToFile(InputStream inStream, String target)
            throws IOException {
        OutputStream out = null;
        int read = 0;
        byte[] bytes = new byte[1024];
        out = new FileOutputStream(new File(target));
        while ((read = inStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
    }

    /**
     * Creates a folder to desired location if it not already exists
     *
     * @param dirName - full path to the folder
     * @throws SecurityException - in case you don't have permission to create
     * the folder
     */
    private void createFolderIfNotExists(String dirName)
            throws SecurityException {
        File theDir = new File(dirName);
        if (!theDir.exists()) {
            theDir.mkdir();
        }
    }
}
