/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Utente
 */
@Embeddable
public class PostPerTagEntity implements Serializable {
  
  @ManyToOne
  @JoinColumn(name = "ID_POST")
  private PostEntity postEntity;
  
  @ManyToOne
  @JoinColumn(name = "ID_TAG")
  private TagEntity TagEntity;

  public PostPerTagEntity() {
  }

  public PostPerTagEntity(PostEntity postEntity, TagEntity TagEntity) {
    this.postEntity = postEntity;
    this.TagEntity = TagEntity;
  }

  public PostEntity getPostEntity() {
    return postEntity;
  }

  public void setPostEntity(PostEntity postEntity) {
    this.postEntity = postEntity;
  }

  public TagEntity getTagEntity() {
    return TagEntity;
  }

  public void setTagEntity(TagEntity TagEntity) {
    this.TagEntity = TagEntity;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 79 * hash + Objects.hashCode(this.postEntity);
    hash = 79 * hash + Objects.hashCode(this.TagEntity);
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
    final PostPerTagEntity other = (PostPerTagEntity) obj;
    if (!Objects.equals(this.postEntity, other.postEntity)) {
      return false;
    }
    if (!Objects.equals(this.TagEntity, other.TagEntity)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "PostPerTagEntity{" + "postEntity=" + postEntity + ", TagEntity=" + TagEntity + '}';
  }
  
}
