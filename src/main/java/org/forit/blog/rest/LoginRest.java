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
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.forit.blog.dao.UtenteDAO;

/**
 *
 * @author Utente
 */
@Path("/login")
public class LoginRest {

  @Path("/")
  @GET
  @Consumes("application/json")
  @Produces("application/json")
  public List<String> login(@HeaderParam("path") String path, @HeaderParam("email") String email, @HeaderParam("password") String password) {
      UtenteDAO uDAO = new UtenteDAO();
      return uDAO.login("/login", email, password);
  }
}
