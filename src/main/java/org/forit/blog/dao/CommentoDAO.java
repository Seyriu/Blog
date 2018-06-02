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
import org.forit.blog.dto.CommentoDTO;
import org.forit.blog.dto.PostDTO;
import org.forit.blog.dto.UtenteDTO;
import org.forit.blog.entity.CommentoEntity;
import org.forit.blog.entity.PostEntity;
import org.forit.blog.entity.UtenteEntity;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Utente
 */
public class CommentoDAO {

    public CommentoDTO CommentoEntityToCommentoDTO(CommentoEntity cEntity) {
        PostDTO pDTO = null;
        PostDAO pDAO = new PostDAO();
        UtenteDTO uDTO = null;
        UtenteDAO uDAO = new UtenteDAO();

        if (cEntity.getPost() != null) {
            pDTO = pDAO.postEntityToPostDTO(cEntity.getPost());
        }

        if (cEntity.getUtente() != null) {
            uDTO = uDAO.UtenteEntityToUtenteDTO(cEntity.getUtente());
        }

        CommentoDTO cDTO = new CommentoDTO(
                cEntity.getId(),
                cEntity.getTesto(),
                cEntity.getDataInserimento(),
                cEntity.getVisibile(),
                pDTO,
                uDTO
        );
        
        if(cEntity.getDataRisposta() != null) {
            cDTO.setDataRisposta(cEntity.getDataRisposta());
            cDTO.setRisposta(cEntity.getRisposta());   
        }

        return cDTO;
    }

    public CommentoEntity CommentoDTOToCommentoEntity(CommentoDTO cDTO) {
        PostEntity pEntity = null;
        PostDAO pDAO = new PostDAO();
        UtenteEntity uEntity = null;
        UtenteDAO uDAO = new UtenteDAO();

        if (cDTO.getPost() != null) {
            pEntity = pDAO.postDTOToPostEntity(cDTO.getPost());
        }

        if (cDTO.getUtente() != null) {
            uEntity = uDAO.utenteDTOToUtenteEntity(cDTO.getUtente());
        }

        CommentoEntity commentoEntity = new CommentoEntity(
                cDTO.getId(),
                cDTO.getTesto(),
                cDTO.getDataInserimento(),
                cDTO.getRisposta(),
                cDTO.getDataRisposta(),
                cDTO.getVisibile(),
                pEntity,
                uEntity
        );

        return commentoEntity;
    }

    public List<CommentoDTO> getListaCommenti() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();
        TypedQuery<CommentoEntity> query = em.createNamedQuery("commento.selectAll", CommentoEntity.class);
        List<CommentoEntity> list = query.getResultList();
        List<CommentoDTO> cDTO = list.stream().map(cEnt -> {
            CommentoDAO cDAO = new CommentoDAO();
            CommentoDTO commentoDTO = cDAO.CommentoEntityToCommentoDTO(cEnt);

            PostDAO pDAO = new PostDAO();
            commentoDTO.setPost(pDAO.postEntityToPostDTO(cEnt.getPost()));

            UtenteDAO uDAO = new UtenteDAO();
            commentoDTO.setUtente(uDAO.UtenteEntityToUtenteDTO(cEnt.getUtente()));

            return commentoDTO;
        }
        ).collect(Collectors.toList());
        em.close();

        emf.close();

        return cDTO;
    }

    public CommentoDTO loadCommento(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();
        CommentoEntity cEnt = em.find(CommentoEntity.class, id);
        CommentoDAO cDAO = new CommentoDAO();
        CommentoDTO commentoDTO = cDAO.CommentoEntityToCommentoDTO(cEnt);

        PostDAO pDAO = new PostDAO();
        commentoDTO.setPost(pDAO.postEntityToPostDTO(cEnt.getPost()));

        UtenteDAO uDAO = new UtenteDAO();
        commentoDTO.setUtente(uDAO.UtenteEntityToUtenteDTO(cEnt.getUtente()));
        
        em.close();
        emf.close();
        
        return commentoDTO;
    }

    public void deleteCommento(long id) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            CommentoEntity commento = em.find(CommentoEntity.class,
                    id);
            em.remove(commento);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new BlogException(ex);
        } finally {
            em.close();
            emf.close();
        }
    }

    public void insertCommento(CommentoDTO cDTO) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            UtenteDAO uDAO = new UtenteDAO();
            PostDAO pDAO = new PostDAO();
            CommentoEntity cEntity = new CommentoEntity();
            cEntity.setTesto(cDTO.getTesto());
            cEntity.setDataInserimento(cDTO.getDataInserimento());
            cEntity.setRisposta(cDTO.getRisposta());
            cEntity.setDataRisposta(cDTO.getDataRisposta());
            cEntity.setVisibile(cDTO.getVisibile());
            cEntity.setDataRisposta(cDTO.getDataRisposta());
            cEntity.setPost(pDAO.postDTOToPostEntity(cDTO.getPost()));
            cEntity.setUtente(uDAO.utenteDTOToUtenteEntity(cDTO.getUtente()));

            em.persist(cEntity);
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
