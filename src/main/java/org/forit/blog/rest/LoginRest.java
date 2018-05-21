/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.rest;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.blog.authentication.Authentication;
import org.forit.blog.dao.UtenteDAO;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Utente
 */
@Path("/login")
public class LoginRest {

  @Path("/")
  @GET
  @Produces("application/json")
  public String login(
          @HeaderParam("path") String path,
          @HeaderParam("email") String email,
          @HeaderParam("password") String password
          ) {
    UtenteDAO uDAO = new UtenteDAO();
    return uDAO.login(path, email, password);
  }
}
