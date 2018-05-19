/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.dao;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.forit.blog.dto.PostDTO;
import org.forit.blog.dto.TagDTO;
import org.forit.blog.entity.TagEntity;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Utente
 */
public class TagDAO {

  public List<TagDTO> loadListaTag() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
    EntityManager em = emf.createEntityManager();

    TypedQuery<TagEntity> query = em.createNamedQuery("tag.selectAll", TagEntity.class);
    List<TagEntity> list = query.getResultList();

    List<TagDTO> tDTO = list.stream().map(entity -> {
      TagDTO tagDTO = new TagDTO(
              entity.getId(),
              entity.getNome()
      );
      List<PostDTO> posts = entity.getPost().stream().map(pxtEntity -> {
        PostDAO pDAO = new PostDAO();
        return pDAO.postEntityToPostDTO(pxtEntity.getPostEntity());
      }).collect(Collectors.toList());
      
      tagDTO.setPosts(posts);
      
      return tagDTO;
    }).collect(Collectors.toList());

    em.close();
    emf.close();
    return tDTO;
  }
  
  public void insertTag(TagDTO tDTO) throws BlogException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
    EntityManager em = emf.createEntityManager();

    EntityTransaction transaction = em.getTransaction();
    try {
      transaction.begin();

      TagEntity tEntity = new TagEntity();
      tEntity.setNome(tDTO.getNome());

      em.persist(tEntity);
      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw new BlogException(ex);
    } finally {
      em.close();
      emf.close();
    }
  }
  
  public void deleteTag(long id) throws BlogException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
    EntityManager em = emf.createEntityManager();

    EntityTransaction transaction = em.getTransaction();
    try {
      transaction.begin();
      
      TagEntity tEntity = em.find(TagEntity.class, id);
      em.remove(tEntity);

      transaction.commit();
    } catch (Exception ex) {
      transaction.rollback();
      throw new BlogException(ex);
    } finally {
      em.close();
      emf.close();
    }
  }
}
