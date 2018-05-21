/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.util.Objects;
import org.forit.netflix.serializer.LocalDateDeserializer;
import org.forit.netflix.serializer.LocalDateSerializer;

/**
 *
 * @author Utente
 */
public class UtenteDTO {

  private long id;
  private String email;
  private String password;
  private Boolean isActive;
  private int failedAccessAttempts;
  private Boolean isBanned;

  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate dateCreation;

  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate dateLastAccess;
  private RuoloDTO ruolo;

  public UtenteDTO() {
  }

  public UtenteDTO(long id, String email, Boolean isActive, int failedAccessAttempts, Boolean isBanned, LocalDate dateCreation, LocalDate dateLastAccess, RuoloDTO ruolo) {
    this.id = id;
    this.email = email;
    this.isActive = isActive;
    this.failedAccessAttempts = failedAccessAttempts;
    this.isBanned = isBanned;
    this.dateCreation = dateCreation;
    this.dateLastAccess = dateLastAccess;
    this.ruolo = ruolo;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public int getFailedAccessAttempts() {
    return failedAccessAttempts;
  }

  public void setFailedAccessAttempts(int failedAccessAttempts) {
    this.failedAccessAttempts = failedAccessAttempts;
  }

  public Boolean getIsBanned() {
    return isBanned;
  }

  public void setIsBanned(Boolean isBanned) {
    this.isBanned = isBanned;
  }

  public LocalDate getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(LocalDate dateCreation) {
    this.dateCreation = dateCreation;
  }

  public String getDateCreationAsString() {
    return dateCreation.toString();
  }

  public void setDateCreationAsString(String creationDate) {
    this.dateCreation = LocalDate.parse(creationDate);
  }

  public LocalDate getDateLastAccess() {
    return dateLastAccess;
  }

  public void setDateLastAccess(LocalDate dateLastAccess) {
    this.dateLastAccess = dateLastAccess;
  }

  public String getDateLastAccessAsString() {
    return dateLastAccess.toString();
  }

  public void setDateLastAccessAsString(String lastAccess) {
    this.dateLastAccess = LocalDate.parse(lastAccess);
  }

  public RuoloDTO getRuolo() {
    return ruolo;
  }

  public void setRuolo(RuoloDTO ruolo) {
    this.ruolo = ruolo;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 31 * hash + (int) (this.id ^ (this.id >>> 32));
    hash = 31 * hash + Objects.hashCode(this.email);
    hash = 31 * hash + Objects.hashCode(this.password);
    hash = 31 * hash + Objects.hashCode(this.isActive);
    hash = 31 * hash + this.failedAccessAttempts;
    hash = 31 * hash + Objects.hashCode(this.isBanned);
    hash = 31 * hash + Objects.hashCode(this.dateCreation);
    hash = 31 * hash + Objects.hashCode(this.dateLastAccess);
    hash = 31 * hash + Objects.hashCode(this.ruolo);
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
    final UtenteDTO other = (UtenteDTO) obj;
    if (this.id != other.id) {
      return false;
    }
    if (this.failedAccessAttempts != other.failedAccessAttempts) {
      return false;
    }
    if (!Objects.equals(this.email, other.email)) {
      return false;
    }
    if (!Objects.equals(this.password, other.password)) {
      return false;
    }
    if (!Objects.equals(this.isActive, other.isActive)) {
      return false;
    }
    if (!Objects.equals(this.isBanned, other.isBanned)) {
      return false;
    }
    if (!Objects.equals(this.dateCreation, other.dateCreation)) {
      return false;
    }
    if (!Objects.equals(this.dateLastAccess, other.dateLastAccess)) {
      return false;
    }
    if (!Objects.equals(this.ruolo, other.ruolo)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "UtenteDTO{" + "id=" + id + ", email=" + email + ", password=" + password + ", isActive=" + isActive + ", failedAccessAttempts=" + failedAccessAttempts + ", isBanned=" + isBanned + ", dateCreation=" + dateCreation + ", dateLastAccess=" + dateLastAccess + ", ruolo=" + ruolo + '}';
  }

}
