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
import org.forit.blog.dto.RuoloDTO;
import org.forit.blog.dto.UtenteDTO;
import org.forit.blog.entity.UtenteEntity;

/**
 *
 * @author Utente
 */
public class UtenteDAO {

  public UtenteDTO UtenteEntityToUtenteDTO(UtenteEntity uEntity) {
    RuoloDTO rDTO = null;

    if (uEntity.getRuolo() != null) {
      rDTO = new RuoloDTO(
              uEntity.getRuolo().getId(),
              uEntity.getRuolo().getNome()
      );
    }

    UtenteDTO uDTO = new UtenteDTO(
            uEntity.getId(),
            uEntity.getEmail(),
            uEntity.getIsActive(),
            uEntity.getFailed_access_attempts(),
            uEntity.getIsBanned(),
            uEntity.getDateCreation(),
            uEntity.getDateLastAccess(),
            rDTO);

    return uDTO;
  }
  
  public List<UtenteDTO> getListaUtenti() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
    EntityManager em = emf.createEntityManager();
    TypedQuery<UtenteEntity> query = em.createNamedQuery("utente.selectAll", UtenteEntity.class);
    List<UtenteEntity> list = query.getResultList();
    List<UtenteDTO> uDTO = list.stream().map(uEntity -> {
        UtenteDAO uDAO = new UtenteDAO();
        return uDAO.UtenteEntityToUtenteDTO(uEntity);
      }).collect(Collectors.toList());
    em.close();
    emf.close();

    return uDTO;
  }
}
