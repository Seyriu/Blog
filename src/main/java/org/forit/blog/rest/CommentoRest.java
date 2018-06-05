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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.blog.authentication.Authentication;
import org.forit.blog.dao.CommentoDAO;
import org.forit.blog.dto.CommentoDTO;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Utente
 */
@Path("/commenti")
public class CommentoRest {

    @Path("/")
    @GET
    @Produces("application/json")
    public List<CommentoDTO> loadCommenti() {
        CommentoDAO cdao = new CommentoDAO();
        return cdao.getListaCommenti();
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public CommentoDTO loadCommento(@PathParam("id") Long id) {
        CommentoDAO cDAO = new CommentoDAO();
        return cDAO.loadCommento(id);
    }

    @Path("/{id}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public boolean deleteCommento(@PathParam("id") Long id) {
        try {
            CommentoDAO cDAO = new CommentoDAO();
            cDAO.deleteCommento(id);
            return true;
        } catch (BlogException ex) {
            System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
            return false;
        }
    }

    @Path("/")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public boolean insertCommento(CommentoDTO cDTO) {
        try {
            CommentoDAO cDAO = new CommentoDAO();
            cDAO.insertCommento(cDTO);
            return true;
        } catch (BlogException ex) {
            System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
            return false;
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public boolean updateVisibility(String visibility, @PathParam("id") long id,  @HeaderParam("jwt") String compactJwt) {
        try {
            Authentication auth = new Authentication();
            if (auth.checkJWSAdmin(compactJwt)) {
                CommentoDAO cDAO = new CommentoDAO();
                cDAO.updateVisibility(visibility, id);
                return true;
            } else {
                return false;
            }
        } catch (BlogException ex) {
            System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
            return false;
        }
    }

//    @Path("/{id}")
//    @PUT
//    @Consumes("application/json")
//    @Produces("application/json")
//    public boolean updateCommento(CommentoDTO cDTO, @PathParam("id") long id,  @HeaderParam("jwt") String compactJwt) {
//        try {
//            Authentication auth = new Authentication();
//            if (auth.checkJWSAdmin(compactJwt)) {
//                CommentoDAO cDAO = new CommentoDAO();
//                cDAO.updateCommento(cDTO, id);
//                return true;
//            } else {
//                return false;
//            }
//        } catch (BlogException ex) {
//            System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
//            return false;
//        }
//    }
}
