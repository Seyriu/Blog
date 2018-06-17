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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    private static final String PROFILE_PIC = "profile-picture";
    private static final String POST_PIC = "post-picture";

    /**
     * Returns either a http error or null if image is uploaded correctly
     *
     * @param uploadedInputStream
     * @param fileDetail
     * @param body
     * @param id
     * @param compactJwt
     * @return error response in case of missing parameters an internal
     * exception or success response if file has been stored successfully
     */
    @Path("/profile-image/{userId}")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadProfilePic(
            @FormDataParam("profilePicture") InputStream uploadedInputStream,
            @FormDataParam("profilePicture") FormDataContentDisposition fileDetail,
            @FormDataParam("profilePicture") final FormDataBodyPart body,
            @PathParam("userId") Long id,
            @HeaderParam("jwt") String compactJwt) {
        return uploadImage(uploadedInputStream, fileDetail, body, id, compactJwt, PROFILE_PIC);
    }

    /**
     * Returns either a http error or null if image is uploaded correctly
     *
     * @param uploadedInputStream
     * @param fileDetail
     * @param body
     * @param id
     * @param compactJwt
     * @return error response in case of missing parameters an internal
     * exception or success response if file has been stored successfully
     */
    @Path("/post-image/{postId}")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadPostPic(
            @FormDataParam("profilePicture") InputStream uploadedInputStream,
            @FormDataParam("profilePicture") FormDataContentDisposition fileDetail,
            @FormDataParam("profilePicture") final FormDataBodyPart body,
            @PathParam("postId") Long id,
            @HeaderParam("jwt") String compactJwt) {
        return uploadImage(uploadedInputStream, fileDetail, body, id, compactJwt, POST_PIC);
    }

    private Response uploadImage(InputStream uploadedInputStream,
            FormDataContentDisposition fileDetail,
            FormDataBodyPart body,
            long id,
            String compactJwt,
            String sender
    ) {
        try {
            Authentication auth = new Authentication();
            if (auth.checkJWSUtenteOrAdmin(compactJwt) && uploadedInputStream != null) {
                if (body.getMediaType().toString().equals("image/jpeg")
                        || body.getMediaType().toString().equals("image/png")) {
                    ImageUploadDAO iuDAO = new ImageUploadDAO();
                    if (sender.equals(PROFILE_PIC)) {
                        return iuDAO.imageUpload(uploadedInputStream, fileDetail, body, id, PROFILE_PIC);
                    } else if (sender.equals(POST_PIC)) {
                        return iuDAO.imageUpload(uploadedInputStream, fileDetail, body, id, POST_PIC);
                    }
                } else {
                    return Response.status(500).entity("Not an acceptable file format!").build();
                }
            }
            return null;
        } catch (BlogException ex) {
            return Response.status(500).entity("Authentication failed: " + ex).build();
        }
    }
}
