/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.forit.blog.authentication.Authentication;
import org.forit.blog.dto.RuoloDTO;
import org.forit.blog.dto.UtenteDTO;
import org.forit.blog.entity.RuoloEntity;
import org.forit.blog.entity.UtenteEntity;
import org.forit.blog.exceptions.BlogException;

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
                uEntity.getImage(),
                uEntity.getDateCreation(),
                uEntity.getDateLastAccess(),
                rDTO);

        return uDTO;
    }

    public UtenteEntity utenteDTOToUtenteEntity(UtenteDTO uDTO) {
        RuoloEntity rEntity = null;

        if (uDTO.getRuolo() != null) {
            rEntity = new RuoloEntity(
                    uDTO.getRuolo().getId(),
                    uDTO.getRuolo().getNome()
            );
        }

        UtenteEntity uEntity = new UtenteEntity(
                uDTO.getId(),
                uDTO.getEmail(),
                uDTO.getPassword(),
                uDTO.getIsActive(),
                uDTO.getFailedAccessAttempts(),
                uDTO.getIsBanned(),
                uDTO.getImage(),
                uDTO.getDateCreation(),
                uDTO.getDateLastAccess(),
                rEntity);

        return uEntity;
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

    public UtenteEntity loadUtente(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();
        UtenteEntity utenteEntity = em.find(UtenteEntity.class, id);
        em.close();
        emf.close();

        return utenteEntity;
    }

    public UtenteDTO loadUtenteDTO(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();
        UtenteEntity utenteEntity = em.find(UtenteEntity.class, id);
        UtenteDTO uDTO = this.UtenteEntityToUtenteDTO(utenteEntity);
        em.close();
        emf.close();

        return uDTO;
    }

    private UtenteEntity loadUtenteByEmail(String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();
        TypedQuery<UtenteEntity> query = em.createNamedQuery("utente.selectUserByEmail", UtenteEntity.class)
                .setParameter("email", email);
        UtenteEntity uEntity = query.getResultList().get(0);
        em.close();
        emf.close();

        return uEntity;
    }

    public UtenteDTO loadUtenteDTOByEmail(String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();
        TypedQuery<UtenteEntity> query = em.createNamedQuery("utente.selectUserByEmail", UtenteEntity.class)
                .setParameter("email", email);
        UtenteEntity uEntity = query.getResultList().get(0);
        em.close();
        emf.close();

        if (uEntity != null) {
            return this.UtenteEntityToUtenteDTO(uEntity);
        }
        return null; 
    }

    public void insertUtente(UtenteDTO uDTO) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            UtenteEntity uEntity = new UtenteEntity();
            uEntity.setEmail(uDTO.getEmail());
            uEntity.setPassword(uDTO.getPassword());
            uEntity.setDateCreation(uDTO.getDateCreation());
            uEntity.setDateLastAccess(uDTO.getDateLastAccess());
            uEntity.setFailed_access_attempts(0);
            uEntity.setIsActive(uDTO.getIsActive());
            uEntity.setIsBanned(uDTO.getIsBanned());
            if (uDTO.getRuolo() != null) {
                uEntity.setRuolo(new RuoloEntity(
                        uDTO.getRuolo().getId(),
                        uDTO.getRuolo().getNome()
                ));
            } else {
                uEntity.setRuolo(null);
            }

            em.persist(uEntity);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new BlogException(ex);
        } finally {
            em.close();
            emf.close();
        }
    }

    public void updateActivated(boolean isActive, long id) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            UtenteEntity utente = em.find(UtenteEntity.class, id);
            utente.setIsActive(isActive);
            em.merge(utente);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new BlogException(ex);
        } finally {
            em.close();
        }
    }

    public void updateBanned(boolean isBanned, long id) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            UtenteEntity utente = em.find(UtenteEntity.class, id);
            utente.setIsBanned(isBanned);
            em.merge(utente);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new BlogException(ex);
        } finally {
            em.close();
        }
    }

    public void updateImage(String imagePath, long id) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            UtenteEntity utente = loadUtente(id);
            utente.setImage(imagePath);
            em.merge(utente);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new BlogException(ex);
        } finally {
            em.close();
        }
    }

    public void increaseFailedAccessAttempts(String email) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu"); // nome dato in persistence.xml
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            UtenteEntity utente = this.loadUtenteByEmail(email);
            utente.setFailed_access_attempts(utente.getFailed_access_attempts() + 1);
            em.merge(utente);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new BlogException(ex);
        } finally {
            em.close();
        }
    }

    public List<String> login(String path, String email, String password) {
        try {
            List<String> jwt = new ArrayList<>();
            UtenteEntity uEntity = loadUtenteByEmail(email);
            UtenteDTO uDTO = this.UtenteEntityToUtenteDTO(uEntity);
            uDTO.setPassword(uEntity.getPassword());
            if (password.equals(uDTO.getPassword())) {
                Authentication auth = new Authentication();
                jwt.add(auth.getJWS(path, uDTO.getEmail(), uDTO.getRuolo().getNome()));
                jwt.add(Long.toString(uEntity.getId()));
                return jwt;
            }
            return null;
        } catch (BlogException ex) {
            return null;
        }
    }
}
