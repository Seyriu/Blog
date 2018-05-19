/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Utente
 */
@Entity
@Table(name = "ruolo")
@NamedQueries({
  @NamedQuery(name = "ruolo.selectAll",
          query = "SELECT r from RuoloEntity r Order by r.nome")
})
public class RuoloEntity implements Serializable{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // id e' generato automaticamente. Identity = campo autoincrementante
  @Column(name = "ID", unique = true, nullable = false)
  private long id;

  @Column(name = "NOME", unique = false, nullable = true)
  private String nome;

  public RuoloEntity() {
  }

  public RuoloEntity(long id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
    hash = 79 * hash + Objects.hashCode(this.nome);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final RuoloEntity other = (RuoloEntity) obj;
    if (this.id != other.id) {
      return false;
    }
    if (!Objects.equals(this.nome, other.nome)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "RuoloEntity{" + "id=" + id + ", nome=" + nome + '}';
  }
    
}
