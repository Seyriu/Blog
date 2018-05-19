/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.blog.dao.CommentoDAO;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Utente
 */
@Path("/commenti")
public class CommentoRest {
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
}
