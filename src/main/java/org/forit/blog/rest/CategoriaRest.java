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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.blog.authentication.Authentication;
import org.forit.blog.dao.CategoriaDAO;
import org.forit.blog.dao.TagDAO;
import org.forit.blog.dto.CategoriaDTO;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Casper
 */
@Path("/categorie")
public class CategoriaRest {

    @Path("/")
    @GET
    @Produces("application/json")
    public List<CategoriaDTO> loadCategorie() {
        CategoriaDAO cDAO = new CategoriaDAO();
        return cDAO.getListaCategorie();
    }

    @Path("/{id}")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public CategoriaDTO loadCategoria(@PathParam("id") Long id) {
        CategoriaDAO cDAO = new CategoriaDAO();
        return cDAO.loadCategoria(id);
    }

    @Path("/{id}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public boolean deleteTag(@PathParam("id") Long id, @HeaderParam("jwt") String compactJwt) {
        try {
            Authentication auth = new Authentication();
            if (auth.checkJWSAdmin(compactJwt)) {
                CategoriaDAO cDAO = new CategoriaDAO();
                cDAO.deleteCategoria(id);
                return true;
            } else {
                return false;
            }
        } catch (BlogException ex) {
            System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
            return false;
        }
    }

}
