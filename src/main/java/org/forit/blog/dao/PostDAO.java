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
import org.forit.blog.dto.CategoriaDTO;
import org.forit.blog.dto.CommentoDTO;
import org.forit.blog.dto.PostDTO;
import org.forit.blog.dto.RuoloDTO;
import org.forit.blog.dto.TagDTO;
import org.forit.blog.dto.UtenteDTO;
import org.forit.blog.entity.CategoriaEntity;
import org.forit.blog.entity.PostEntity;
import org.forit.blog.entity.PostPerTagEntity;
import org.forit.blog.entity.RuoloEntity;
import org.forit.blog.entity.TagEntity;
import org.forit.blog.entity.UtenteEntity;
import org.forit.blog.exceptions.BlogException;

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

    public PostEntity postDTOToPostEntity(PostDTO pDTO) {

        CategoriaEntity cEntity = null;
        UtenteEntity uEntity = null;

        if (pDTO.getCategoria() != null) {
            CategoriaDAO cDAO = new CategoriaDAO();
            cEntity = cDAO.CategoriaDtoToCategoriaEntity(pDTO.getCategoria());
        }

        if (pDTO.getUtente() != null) {

            RuoloEntity rEntity = null;

            if (pDTO.getUtente().getRuolo() != null) {
                rEntity = new RuoloEntity(
                        pDTO.getUtente().getRuolo().getId(),
                        pDTO.getUtente().getRuolo().getNome()
                );
            }

            uEntity = new UtenteEntity(
                    pDTO.getUtente().getId(),
                    pDTO.getUtente().getEmail(),
                    pDTO.getUtente().getPassword(),
                    pDTO.getUtente().getIsActive(),
                    pDTO.getUtente().getFailedAccessAttempts(),
                    pDTO.getUtente().getIsBanned(),
                    pDTO.getUtente().getDateCreation(),
                    pDTO.getUtente().getDateLastAccess(),
                    rEntity);
        }

        PostEntity postEntity = new PostEntity(
                pDTO.getId(),
                pDTO.getTitolo(),
                pDTO.getDescrizione(),
                pDTO.getDataPost(),
                pDTO.getVisibile(),
                pDTO.getVisite(),
                cEntity,
                uEntity);

        return postEntity;
    }

    public List<PostDTO> getListaPost() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();
        TypedQuery<PostEntity> query = em.createNamedQuery("post.selectAll", PostEntity.class);
        List<PostEntity> list = query.getResultList();
        List<PostDTO> pDTO = list.stream().map(pEnt -> {
            PostDAO pDAO = new PostDAO();
            PostDTO postDTO = pDAO.postEntityToPostDTO(pEnt);
//            CommentoDAO cDAO = new CommentoDAO();
//
//            List<CommentoDTO> cDTO = pEnt.getCommenti().stream().map(cEnt -> {
//                return cDAO.CommentoEntityToCommentoDTO(cEnt);
//            }).collect(Collectors.toList());
//            postDTO.setCommenti(cDTO);
            return postDTO;
        }).collect(Collectors.toList());
        em.close();
        emf.close();

        return pDTO;
    }

    public PostDTO loadPost(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();
        PostEntity post = em.find(PostEntity.class, id);
        PostDTO pDTO = this.postEntityToPostDTO(post);
        CommentoDAO cDAO = new CommentoDAO();
        List<CommentoDTO> cDTO = post.getCommenti().stream().map(cEnt -> {
            return cDAO.CommentoEntityToCommentoDTO(cEnt);
        }).collect(Collectors.toList());
        pDTO.setCommenti(cDTO);

        TagDAO tDAO = new TagDAO();
        List<TagDTO> tDTO = post.getTags().stream().map(tEnt -> {
            return tDAO.TagEntityToTagDTO(tEnt);
        }).collect(Collectors.toList());
        pDTO.setTags(tDTO);
        em.close();
        emf.close();

        return pDTO;
    }

    public void deletePost(long id) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            PostEntity post = em.find(PostEntity.class, id);
            em.remove(post);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new BlogException(ex);
        } finally {
            em.close();
            emf.close();
        }
    }

    public void insertPost(PostDTO pDTO) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            CategoriaDAO cDAO = new CategoriaDAO();
            UtenteDAO uDAO = new UtenteDAO();
            PostEntity pEntity = new PostEntity();
            pEntity.setCategoria(cDAO.CategoriaDtoToCategoriaEntity(pDTO.getCategoria()));
            pEntity.setCommenti(null);
            pEntity.setVisite(pDTO.getVisite());
            pEntity.setVisibile(pDTO.getVisibile());
            pEntity.setDataPost(pDTO.getDataPost());
            pEntity.setDescrizione(pDTO.getDescrizione());
            pEntity.setTitolo(pDTO.getTitolo());
            pEntity.setUtente(uDAO.utenteDTOToUtenteEntity(pDTO.getUtente()));

//      pEntity.setTag(null);
//      pEntity.setTags(pDTO.getTags().stream().map(tagDTO -> {
//        return new TagEntity(tagDTO.getId(), tagDTO.getNome());
//      }).collect(Collectors.toList())
//      );
            pDTO.getTags().stream().forEach(tDTO -> {
                pEntity.addTag(new TagEntity(tDTO.getId(), tDTO.getNome()));
            });

            em.persist(pEntity);
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
