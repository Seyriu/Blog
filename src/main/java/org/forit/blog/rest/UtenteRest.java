/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.blog.authentication.Authentication;
import org.forit.blog.dao.UtenteDAO;
import org.forit.blog.dto.UtenteDTO;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Utente
 */
@Path("/utenti")
public class UtenteRest {

    @Path("/")
    @GET
    @Produces("application/json")
    public List<UtenteDTO> loadUtenti(@HeaderParam("jwt") String compactJwt) {
        try {
            Authentication auth = new Authentication();
            if (auth.checkJWSAdmin(compactJwt)) {
                UtenteDAO uDAO = new UtenteDAO();
                return uDAO.getListaUtenti();
            }
            return null;
        } catch (BlogException ex) {
            return null;
        }
    }

    @Path("/{id}")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public UtenteDTO loadUtente(@PathParam("id") Long id,
            @HeaderParam("jwt") String compactJwt
    ) {
        try {
            Authentication auth = new Authentication();
            if (auth.checkJWSUtenteOrAdmin(compactJwt)) {
                UtenteDAO uDAO = new UtenteDAO();
                return uDAO.loadUtente(id);
            } else {
                return null;
            }
        } catch (BlogException ex) {
            System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
            return null;
        }
    }

    @Path("/")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public boolean insertUtente(UtenteDTO uDTO
    ) {
        try {
            UtenteDAO uDAO = new UtenteDAO();
            uDAO.insertUtente(uDTO);
            return true;
        } catch (BlogException ex) {
            System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
            return false;
        }
    }

    @Path("/activated/{id}")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public boolean updateActivated(boolean isActive,
            @PathParam("id") long id,
            @HeaderParam("jwt") String compactJwt
    ) {
        try {
            Authentication auth = new Authentication();
            if (auth.checkJWSAdmin(compactJwt)) {
                UtenteDAO uDAO = new UtenteDAO();
                uDAO.updateActivated(isActive, id);
                return true;
            } else {
                return false;
            }
        } catch (BlogException ex) {
            System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
            return false;
        }
    }

    @Path("/banned/{id}")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public boolean updateBanned(boolean isBanned,
            @PathParam("id") long id,
            @HeaderParam("jwt") String compactJwt
    ) {
        try {
            Authentication auth = new Authentication();
            if (auth.checkJWSAdmin(compactJwt)) {
                UtenteDAO uDAO = new UtenteDAO();
                uDAO.updateBanned(isBanned, id);
                return true;
            } else {
                return false;
            }
        } catch (BlogException ex) {
            System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
            return false;
        }
    }

    @Path("/failed-accesses")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public boolean increaseFailedAccessAttempts(String email
    ) {
        try {
            UtenteDAO uDAO = new UtenteDAO();
            uDAO.increaseFailedAccessAttempts(email);
            return true;
        } catch (BlogException ex) {
            System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
