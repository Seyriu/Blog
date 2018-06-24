/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.forit.blog.serializer.LocalDateTimeDeserializer;
import org.forit.blog.serializer.LocalDateTimeSerializer;

/**
 *
 * @author Utente
 */
public class PostDTO {

    private long id;
    private String titolo;
    private String descrizione;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dataPost;
    private Boolean visibile;
    private int visite;
    private String image;
    private CategoriaDTO categoria;
    private UtenteDTO utente;
    private List<CommentoDTO> commenti;
    private List<TagDTO> Tags;

    public PostDTO() {
    }

    public PostDTO(long id, String titolo, String descrizione, LocalDateTime dataPost, Boolean visibile, int visite, String image, CategoriaDTO categoria, UtenteDTO utente) {
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

    public String getDataPostAsString() {
        return dataPost.toString();
    }

    public void setDataPostAsString(String lastAccess) {
        this.dataPost = LocalDateTime.parse(lastAccess);
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + Objects.hashCode(this.titolo);
        hash = 89 * hash + Objects.hashCode(this.descrizione);
        hash = 89 * hash + Objects.hashCode(this.dataPost);
        hash = 89 * hash + Objects.hashCode(this.visibile);
        hash = 89 * hash + this.visite;
        hash = 89 * hash + Objects.hashCode(this.image);
        hash = 89 * hash + Objects.hashCode(this.categoria);
        hash = 89 * hash + Objects.hashCode(this.utente);
        hash = 89 * hash + Objects.hashCode(this.Tags);
        hash = 89 * hash + Objects.hashCode(this.commenti);
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
        if (!Objects.equals(this.image, other.image)) {
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
        return "PostDTO{" + "id=" + id + ", titolo=" + titolo + ", descrizione=" + descrizione + ", dataPost=" + dataPost + ", visibile=" + visibile + ", visite=" + visite + ", image=" + image + ", categoria=" + categoria + ", utente=" + utente + ", Tags=" + Tags + ", commenti=" + commenti + '}';
    }
    
}
