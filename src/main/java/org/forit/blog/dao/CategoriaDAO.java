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
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.forit.blog.dto.CategoriaDTO;
import org.forit.blog.entity.CategoriaEntity;
import org.forit.blog.entity.UtenteEntity;

/**
 *
 * @author Casper
 */
public class CategoriaDAO {

  public CategoriaDTO CategoriaEntityToCategoriaDTO(CategoriaEntity cEntity) {
    return new CategoriaDTO(
            cEntity.getId(),
            cEntity.getNome(),
            cEntity.getDescrizione(),
            cEntity.getImmagine());
  }

  public List<CategoriaDTO> getListaCategorie() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
    EntityManager em = emf.createEntityManager();
    TypedQuery<CategoriaEntity> query = em.createNamedQuery("categoria.selectAll", CategoriaEntity.class);
    List<CategoriaEntity> list = query.getResultList();
    List<CategoriaDTO> cDTO = list.stream().map(cEnt -> {
      return this.CategoriaEntityToCategoriaDTO(cEnt);
    }).collect(Collectors.toList());
    em.close();
    emf.close();

    return cDTO;
  }

  public CategoriaDTO loadCategoria(long id) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
    EntityManager em = emf.createEntityManager();
    CategoriaEntity cEntity = em.find(CategoriaEntity.class, id);
    CategoriaDTO cDTO = CategoriaEntityToCategoriaDTO(cEntity);
    PostDAO pDAO = new PostDAO();
    cDTO.setPosts(cEntity.getPosts().stream().map(pEnt -> {
      return pDAO.postEntityToPostDTO(pEnt);
    }).collect(Collectors.toList()));
    em.close();
    emf.close();

    return cDTO;
  }
}
