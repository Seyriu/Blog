/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.blog.authentication.Authentication;
import org.forit.blog.dao.TagDAO;
import org.forit.blog.dto.TagDTO;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Utente
 */
@Path("/tags")
public class TagRest {

    @Path("/names/{name}")
    @GET()
    @Produces("application/json")
    public TagDTO getTagByName(@PathParam("name") String name) {
        TagDAO tagDAO = new TagDAO();
        return tagDAO.loadTagByName(name);
    }

    @Path("/")
    @GET()
    @Produces("application/json")
    public List<TagDTO> getTags() {
        TagDAO tagDAO = new TagDAO();
        return tagDAO.loadListaTag();
    }

    @Path("/")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public boolean insertTag(TagDTO tDTO) {
        try {
            TagDAO tDAO = new TagDAO();
            tDAO.insertTag(tDTO);
            return true;
        } catch (BlogException ex) {
            System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
            return false;
        }
    }

    @Path("/{id}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public boolean deleteTag(@PathParam("id") Long id, @HeaderParam("jwt") String compactJwt) {
        try {
            Authentication auth = new Authentication();
            if (auth.checkJWSAdmin(compactJwt)) {
                TagDAO mdao = new TagDAO();
                mdao.deleteTag(id);
                return true;
            } else {
                return false;
            }
        } catch (BlogException ex) {
            System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
            return false;
        }
    }

    @Path("/{id}")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public TagDTO loadTag(@PathParam("id") Long id) {
        TagDAO mdao = new TagDAO();
        return mdao.loadTag(id);
    }
}
