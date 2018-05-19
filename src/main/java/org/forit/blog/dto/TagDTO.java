/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Utente
 */
public class TagDTO {
    private long id;
    private String nome;
    private List<PostDTO> posts;

  public TagDTO() {
  }

  public TagDTO(long id, String nome) {
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

  public List<PostDTO> getPosts() {
    return posts;
  }

  public void setPosts(List<PostDTO> posts) {
    this.posts = posts;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
    hash = 41 * hash + Objects.hashCode(this.nome);
    hash = 41 * hash + Objects.hashCode(this.posts);
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
    final TagDTO other = (TagDTO) obj;
    if (this.id != other.id) {
      return false;
    }
    if (!Objects.equals(this.nome, other.nome)) {
      return false;
    }
    if (!Objects.equals(this.posts, other.posts)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "TagDTO{" + "id=" + id + ", nome=" + nome + ", posts=" + posts + '}';
  }
    
}
