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
@Table(name = "categoria")
@NamedQueries({
  @NamedQuery(name = "categoria.selectAll",
          query = "SELECT c from CategoriaEntity c Order by c.nome")
})
public class CategoriaEntity implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // id e' generato automaticamente. Identity = campo autoincrementante
  @Column(name = "ID", unique = true, nullable = false)
  private long id = -1;

  @Column(name = "NOME", unique = false, nullable = true)
  private String nome;

  @Column(name = "DESCRIZIONE", unique = false, nullable = true)
  private String descrizione;

  @Column(name = "IMMAGINE", unique = false, nullable = true)
  private String immagine;

  public CategoriaEntity() {
  }

  public CategoriaEntity(long id, String nome, String descrizione, String immagine) {
    this.id = id;
    this.nome = nome;
    this.descrizione = descrizione;
    this.immagine = immagine;
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

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public String getImmagine() {
    return immagine;
  }

  public void setImmagine(String immagine) {
    this.immagine = immagine;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
    hash = 41 * hash + Objects.hashCode(this.nome);
    hash = 41 * hash + Objects.hashCode(this.descrizione);
    hash = 41 * hash + Objects.hashCode(this.immagine);
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
    final CategoriaEntity other = (CategoriaEntity) obj;
    if (this.id != other.id) {
      return false;
    }
    if (!Objects.equals(this.nome, other.nome)) {
      return false;
    }
    if (!Objects.equals(this.descrizione, other.descrizione)) {
      return false;
    }
    if (!Objects.equals(this.immagine, other.immagine)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "categoriaEntity{" + "id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", immagine=" + immagine + '}';
  }
}
