/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogtest;

import org.forit.blog.dao.TagDAO;

/**
 *
 * @author Utente
 */
public class BlogJunitTest {
  
  public BlogJunitTest() {
    TagDAO tDAO = new TagDAO();
    System.out.println(tDAO.loadListaTag());
  }
  
}
