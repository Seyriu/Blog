/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Utente
 */
public class PostDTO {
    private long id;
    private String titolo;
    private String descrizione;
    private LocalDate dataPost;
    private Boolean visibile;
    private int visite;
    private CategoriaDTO categoria;
    private UtenteDTO utente;
    private List<TagDTO> Tags;
    private List<CommentoDTO> commenti;

  public PostDTO() {
  }

  public PostDTO(long id, String titolo, String descrizione, LocalDate dataPost, Boolean visibile, int visite, CategoriaDTO categoria, UtenteDTO utente) {
    this.id = id;
    this.titolo = titolo;
    this.descrizione = descrizione;
    this.dataPost = dataPost;
    this.visibile = visibile;
    this.visite = visite;
    this.categoria = categoria;
    this.utente = utente;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitolo() {
    return titolo;
  }

  public void setTitolo(String titolo) {
    this.titolo = titolo;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public LocalDate getDataPost() {
    return dataPost;
  }

  public void setDataPost(LocalDate dataPost) {
    this.dataPost = dataPost;
  }

  public Boolean getVisibile() {
    return visibile;
  }

  public void setVisibile(Boolean visibile) {
    this.visibile = visibile;
  }

  public int getVisite() {
    return visite;
  }

  public void setVisite(int visite) {
    this.visite = visite;
  }

  public CategoriaDTO getCategoria() {
    return categoria;
  }

  public void setCategoria(CategoriaDTO categoria) {
    this.categoria = categoria;
  }

  public UtenteDTO getUtente() {
    return utente;
  }

  public void setUtente(UtenteDTO utente) {
    this.utente = utente;
  }

  public List<TagDTO> getTags() {
    return Tags;
  }

  public void setTags(List<TagDTO> Tags) {
    this.Tags = Tags;
  }

  public List<CommentoDTO> getCommenti() {
    return commenti;
  }

  public void setCommenti(List<CommentoDTO> commenti) {
    this.commenti = commenti;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
    hash = 29 * hash + Objects.hashCode(this.titolo);
    hash = 29 * hash + Objects.hashCode(this.descrizione);
    hash = 29 * hash + Objects.hashCode(this.dataPost);
    hash = 29 * hash + Objects.hashCode(this.visibile);
    hash = 29 * hash + this.visite;
    hash = 29 * hash + Objects.hashCode(this.categoria);
    hash = 29 * hash + Objects.hashCode(this.utente);
    hash = 29 * hash + Objects.hashCode(this.Tags);
    hash = 29 * hash + Objects.hashCode(this.commenti);
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
    final PostDTO other = (PostDTO) obj;
    if (this.id != other.id) {
      return false;
    }
    if (this.visite != other.visite) {
      return false;
    }
    if (!Objects.equals(this.titolo, other.titolo)) {
      return false;
    }
    if (!Objects.equals(this.descrizione, other.descrizione)) {
      return false;
    }
    if (!Objects.equals(this.dataPost, other.dataPost)) {
      return false;
    }
    if (!Objects.equals(this.visibile, other.visibile)) {
      return false;
    }
    if (!Objects.equals(this.categoria, other.categoria)) {
      return false;
    }
    if (!Objects.equals(this.utente, other.utente)) {
      return false;
    }
    if (!Objects.equals(this.Tags, other.Tags)) {
      return false;
    }
    if (!Objects.equals(this.commenti, other.commenti)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "PostDTO{" + "id=" + id + ", titolo=" + titolo + ", descrizione=" + descrizione + ", dataPost=" + dataPost + ", visibile=" + visibile + ", visite=" + visite + ", categoria=" + categoria + ", utente=" + utente + ", Tags=" + Tags + ", commenti=" + commenti + '}';
  }
    
}
