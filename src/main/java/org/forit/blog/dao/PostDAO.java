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
import org.forit.blog.dto.CommentoDTO;
import org.forit.blog.dto.PostDTO;
import org.forit.blog.dto.RuoloDTO;
import org.forit.blog.dto.UtenteDTO;
import org.forit.blog.entity.PostEntity;

/**
 *
 * @author Utente
 */
public class PostDAO {

  public PostDTO postEntityToPostDTO(PostEntity pEntity) {

    CategoriaDTO cDTO = null;
    UtenteDTO uDTO = null;

    if (pEntity.getCategoria() != null) {
      cDTO = new CategoriaDTO(
              pEntity.getCategoria().getId(),
              pEntity.getCategoria().getNome(),
              pEntity.getCategoria().getDescrizione(),
              pEntity.getCategoria().getImmagine());
    }

    if (pEntity.getUtente() != null) {

      RuoloDTO rDTO = null;

      if (pEntity.getUtente().getRuolo() != null) {
        rDTO = new RuoloDTO(
                pEntity.getUtente().getRuolo().getId(),
                pEntity.getUtente().getRuolo().getNome()
        );
      }

      uDTO = new UtenteDTO(
              pEntity.getUtente().getId(),
              pEntity.getUtente().getEmail(),
              pEntity.getUtente().getIsActive(),
              pEntity.getUtente().getFailed_access_attempts(),
              pEntity.getUtente().getIsBanned(),
              pEntity.getUtente().getDateCreation(),
              pEntity.getUtente().getDateLastAccess(),
              rDTO);
    }

    PostDTO pDTO = new PostDTO(
            pEntity.getId(),
            pEntity.getTitolo(),
            pEntity.getDescrizione(),
            pEntity.getDataPost(),
            pEntity.getVisibile(),
            pEntity.getVisite(),
            cDTO,
            uDTO);

    return pDTO;
  }

  public List<PostDTO> getListaPost() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
    EntityManager em = emf.createEntityManager();
    TypedQuery<PostEntity> query = em.createNamedQuery("post.selectAll", PostEntity.class);
    List<PostEntity> list = query.getResultList();
    List<PostDTO> pDTO = list.stream().map(pEnt -> {
      PostDAO pDAO = new PostDAO();
      PostDTO postDTO = pDAO.postEntityToPostDTO(pEnt);
      CommentoDAO cDAO = new CommentoDAO();

      List<CommentoDTO> cDTO = pEnt.getCommenti().stream().map(cEnt -> {
        return cDAO.CommentoEntityToCommentoDTO(cEnt);
      }).collect(Collectors.toList());
      postDTO.setCommenti(cDTO);
      return postDTO;
    }).collect(Collectors.toList());
    em.close();
    emf.close();

    return pDTO;
  }
}
