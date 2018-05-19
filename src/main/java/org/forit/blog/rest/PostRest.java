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
import org.forit.blog.dao.PostDAO;
import org.forit.blog.dto.PostDTO;

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
}
