/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Utente
 */
@Entity
@Table(name = "tag")
@NamedQueries({
    @NamedQuery(name = "tag.selectAll",
            query = "SELECT t from TagEntity t Order by t.nome"),
    @NamedQuery(name = "tag.selectTagByName",
            query = "SELECT t from TagEntity t where t.nome LIKE :nome")
})
public class TagEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id e' generato automaticamente. Identity = campo autoincrementante
    @Column(name = "ID", unique = true, nullable = false)
    private long id = -1;

    @Column(name = "NOME", unique = false, nullable = true)
    private String nome;
    
    @ManyToMany(mappedBy = "tags")
    private List<PostEntity> posts = new ArrayList<>();

    public TagEntity() {
    }

    public TagEntity(long id, String nome) {
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

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> post) {
        this.posts = post;
    }

    public void addPost(PostEntity post) {
        posts.add(post);
        post.getTags().add(this);
    }

    public void removeTag(PostEntity post) {
        posts.remove(post);
        post.getTags().remove(this);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.posts);
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
        final TagEntity other = (TagEntity) obj;
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
        return "TagEntity{" + "id=" + id + ", nome=" + nome + ", post=" + posts + '}';
    }

}
