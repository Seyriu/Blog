/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.rest;

import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.forit.blog.authentication.Authentication;
import org.forit.blog.dao.ImageUploadDAO;
import org.forit.blog.exceptions.BlogException;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author Casper
 */
@Path("/upload")
public class ImageUploadRest {

    private static final String UPLOAD_FOLDER = "H:\\Users\\Seyri\\Documents\\NetBeansProjects\\blog\\assets\\";

    @Context
    private UriInfo context;

    /**
     * Returns text response to caller containing uploaded file location
     *
     * @param uploadedInputStream
     * @param fileDetail
     * @param body
     * @param compactJwt
     * @return error response in case of missing parameters an internal
     * exception or success response if file has been stored successfully
     */
    @Path("/profile-image")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("profilePicture") InputStream uploadedInputStream,
            @FormDataParam("profilePicture") FormDataContentDisposition fileDetail,
            @FormDataParam("profilePicture") final FormDataBodyPart body,
            @HeaderParam("jwt") String compactJwt) {
        try {
            Authentication auth = new Authentication();
            if (auth.checkJWSAdmin(compactJwt)) {
                String email = auth.getEmailFromJWS(compactJwt);
                if (body.getMediaType().toString().equals("image/jpeg")
                        || body.getMediaType().toString().equals("image/png")) {
                    ImageUploadDAO iuDAO = new ImageUploadDAO();
                    String serverPath = UPLOAD_FOLDER + email + "\\profilepicture\\";
                    return iuDAO.imageUpload(uploadedInputStream, fileDetail, serverPath);
                } else {
                    return Response.status(500).entity("Not an acceptable file format!").build();
                }
            }
            return null;
        } catch (BlogException ex) {
            return null;
        }
    }
}
