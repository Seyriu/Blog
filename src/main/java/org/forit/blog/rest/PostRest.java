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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.forit.blog.dao.PostDAO;
import org.forit.blog.dto.PostDTO;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Utente
 */
@Path("/posts")
public class PostRest {
  
  @Path("/")
  @GET
  @Produces("application/json")
  public List<PostDTO> loadPosts() {
    PostDAO mdao = new PostDAO();
    return mdao.getListaPost();
  }
  
  @Path("/{id}")
  @DELETE
  @Consumes("application/json")
  @Produces("application/json")
  public boolean deletePost(@PathParam("id") Long id) {
    try {
      PostDAO pDAO = new PostDAO();
      pDAO.deletePost(id);
      return true;
    } catch (BlogException ex) {
      System.out.println("Si e' verificato un errore: " + ex.getLocalizedMessage());
      return false;
    }
  }
}
