/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.rest;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.forit.blog.dao.UtenteDAO;
import org.forit.blog.dto.UtenteDTO;

/**
 *
 * @author Utente
 */
@Path("/utenti")
public class UtenteRest {
  
  @Path("/")
  @GET
  @Produces("application/json")
  public List<UtenteDTO> loadUtenti() {
    UtenteDAO uDAO = new UtenteDAO();
    return uDAO.getListaUtenti();
  }
}
