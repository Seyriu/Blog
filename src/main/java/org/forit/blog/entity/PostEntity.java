/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Utente
 */
@Entity
@Table(name = "post")
@NamedQueries({
    @NamedQuery(name = "post.selectAll",
            query = "SELECT p from PostEntity p")
})
public class PostEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id e' generato automaticamente. Identity = campo autoincrementante
    @Column(name = "ID", unique = true, nullable = false)
    private long id = -1;

    @Column(name = "TITOLO", unique = false, nullable = true)
    private String titolo;

    @Column(name = "DESCRIZIONE", unique = false, nullable = true)
    private String descrizione;

    @Column(name = "DATA", unique = false, nullable = false)
    private LocalDateTime dataPost;

    @Column(name = "VISIBILE", unique = false, nullable = false)
    private Boolean visibile;

    @Column(name = "VISITE", unique = false, nullable = false)
    private Integer visite;

    @Column(name = "IMAGE", unique = false, nullable = true)
    private String image;

    @OneToOne
    @JoinColumn(name = "ID_CATEGORIA")
    private CategoriaEntity categoria;

    @OneToOne
    @JoinColumn(name = "ID_UTENTE")
    private UtenteEntity utente;

    @OneToMany(mappedBy = "post",
            fetch = FetchType.LAZY)
    private List<CommentoEntity> commenti;

    @ManyToMany()
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "id_post"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private List<TagEntity> tags = new ArrayList<>();

    public PostEntity() {
    }

    public PostEntity(long id, String titolo, String descrizione, LocalDateTime dataPost, Boolean visibile, Integer visite, String image, CategoriaEntity categoria, UtenteEntity utente) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataPost = dataPost;
        this.visibile = visibile;
        this.visite = visite;
        this.image = image;
        this.categoria = categoria;
        this.utente = utente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UtenteEntity getUtente() {
        return utente;
    }

    public void setUtente(UtenteEntity utente) {
        this.utente = utente;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tag) {
        this.tags = tag;
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

    public LocalDateTime getDataPost() {
        return dataPost;
    }

    public void setDataPost(LocalDateTime dataPost) {
        this.dataPost = dataPost;
    }

    public Boolean getVisibile() {
        return visibile;
    }

    public void setVisibile(Boolean visibile) {
        this.visibile = visibile;
    }

    public Integer getVisite() {
        return visite;
    }

    public void setVisite(Integer visite) {
        this.visite = visite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public List<CommentoEntity> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<CommentoEntity> commenti) {
        this.commenti = commenti;
    }
    
    public void addTag(TagEntity tag) {
        tags.add(tag);
        tag.getPosts().add(this);
    }
 
    public void removeTag(TagEntity tag) {
        tags.remove(tag);
        tag.getPosts().remove(this);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 61 * hash + Objects.hashCode(this.titolo);
        hash = 61 * hash + Objects.hashCode(this.descrizione);
        hash = 61 * hash + Objects.hashCode(this.dataPost);
        hash = 61 * hash + Objects.hashCode(this.visibile);
        hash = 61 * hash + Objects.hashCode(this.visite);
        hash = 61 * hash + Objects.hashCode(this.image);
        hash = 61 * hash + Objects.hashCode(this.categoria);
        hash = 61 * hash + Objects.hashCode(this.utente);
        hash = 61 * hash + Objects.hashCode(this.commenti);
        hash = 61 * hash + Objects.hashCode(this.tags);
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
        final PostEntity other = (PostEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.titolo, other.titolo)) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.dataPost, other.dataPost)) {
            return false;
        }
        if (!Objects.equals(this.visibile, other.visibile)) {
            return false;
        }
        if (!Objects.equals(this.visite, other.visite)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.utente, other.utente)) {
            return false;
        }
        if (!Objects.equals(this.commenti, other.commenti)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PostEntity{" + "id=" + id + ", titolo=" + titolo + ", descrizione=" + descrizione + ", dataPost=" + dataPost + ", visibile=" + visibile + ", visite=" + visite + ", image=" + image + ", categoria=" + categoria + ", utente=" + utente + ", commenti=" + commenti + ", tags=" + tags + '}';
    }

}
