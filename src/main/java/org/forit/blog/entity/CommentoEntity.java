/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Utente
 */
@Entity
@Table(name = "commento")
@NamedQueries({
  @NamedQuery(name = "commento.selectAll",
          query = "SELECT c from CommentoEntity c")
})
public class CommentoEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // id e' generato automaticamente. Identity = campo autoincrementante
  @Column(name = "ID", unique = true, nullable = false)
  private long id;

  @Column(name = "TESTO", unique = false, nullable = true)
  private String testo;

  @Column(name = "DATA_INSERIMENTO", unique = false, nullable = false)
  private LocalDate dataInserimento;

  @Column(name = "RISPOSTA", unique = false, nullable = true)
  private String risposta;

  @Column(name = "DATA_RISPOSTA", unique = false, nullable = false)
  private LocalDate dataRisposta;

  @Column(name = "VISIBILE", unique = false, nullable = false)
  private Boolean visibile;
  
  @OneToOne
  @JoinColumn(name="ID_POST")
  private PostEntity post;
  
  @OneToOne
  @JoinColumn(name="ID_UTENTE")
  private UtenteEntity utente;

  public CommentoEntity() {
  }

  public CommentoEntity(long id, String testo, LocalDate dataInserimento, String risposta, LocalDate dataRisposta, Boolean visibile, PostEntity post, UtenteEntity utente) {
    this.id = id;
    this.testo = testo;
    this.dataInserimento = dataInserimento;
    this.risposta = risposta;
    this.dataRisposta = dataRisposta;
    this.visibile = visibile;
    this.post = post;
    this.utente = utente;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTesto() {
    return testo;
  }

  public void setTesto(String testo) {
    this.testo = testo;
  }

  public LocalDate getDataInserimento() {
    return dataInserimento;
  }

  public void setDataInserimento(LocalDate dataInserimento) {
    this.dataInserimento = dataInserimento;
  }

  public String getRisposta() {
    return risposta;
  }

  public void setRisposta(String risposta) {
    this.risposta = risposta;
  }

  public LocalDate getDataRisposta() {
    return dataRisposta;
  }

  public void setDataRisposta(LocalDate dataRisposta) {
    this.dataRisposta = dataRisposta;
  }

  public Boolean getVisibile() {
    return visibile;
  }

  public void setVisibile(Boolean visibile) {
    this.visibile = visibile;
  }

  public PostEntity getPost() {
    return post;
  }

  public void setPost(PostEntity post) {
    this.post = post;
  }

  public UtenteEntity getUtente() {
    return utente;
  }

  public void setUtente(UtenteEntity utente) {
    this.utente = utente;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
    hash = 23 * hash + Objects.hashCode(this.testo);
    hash = 23 * hash + Objects.hashCode(this.dataInserimento);
    hash = 23 * hash + Objects.hashCode(this.risposta);
    hash = 23 * hash + Objects.hashCode(this.dataRisposta);
    hash = 23 * hash + Objects.hashCode(this.visibile);
    hash = 23 * hash + Objects.hashCode(this.post);
    hash = 23 * hash + Objects.hashCode(this.utente);
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
    final CommentoEntity other = (CommentoEntity) obj;
    if (this.id != other.id) {
      return false;
    }
    if (!Objects.equals(this.testo, other.testo)) {
      return false;
    }
    if (!Objects.equals(this.risposta, other.risposta)) {
      return false;
    }
    if (!Objects.equals(this.dataInserimento, other.dataInserimento)) {
      return false;
    }
    if (!Objects.equals(this.dataRisposta, other.dataRisposta)) {
      return false;
    }
    if (!Objects.equals(this.visibile, other.visibile)) {
      return false;
    }
    if (!Objects.equals(this.post, other.post)) {
      return false;
    }
    if (!Objects.equals(this.utente, other.utente)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "CommentoEntity{" + "id=" + id + ", testo=" + testo + ", dataInserimento=" + dataInserimento + ", risposta=" + risposta + ", dataRisposta=" + dataRisposta + ", visibile=" + visibile + ", post=" + post + ", utente=" + utente + '}';
  }
  
}
