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
import org.forit.blog.serializer.LocalDateDeserializer;
import org.forit.blog.serializer.LocalDateSerializer;

/**
 *
 * @author Utente
 */
public class CommentoDTO {

    private long id;
    private String testo;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataInserimento;
    private String risposta;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataRisposta;
    private String visibile;
    private PostDTO post;
    private UtenteDTO utente;

    public CommentoDTO() {
    }

    public CommentoDTO(long id, String testo, LocalDate dataInserimento, String visibile, PostDTO post, UtenteDTO utente) {
        this.id = id;
        this.testo = testo;
        this.dataInserimento = dataInserimento;
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

    public String getDataInserimentoAsString() {
        return dataInserimento.toString();
    }

    public void setDataInserimentoAsString(String lastAccess) {
        this.dataInserimento = LocalDate.parse(lastAccess);
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

    public String getDataRispostaAsString() {
        if (dataRisposta != null) {
            return dataRisposta.toString();
        }
        return "";
    }

    public void setDataRispostaAsString(String lastAccess) {
        if (lastAccess != null && !lastAccess.equals("")) {
            this.dataRisposta = LocalDate.parse(lastAccess);
        }
    }

    public String getVisibile() {
        return visibile;
    }

    public void setVisibile(String visibile) {
        this.visibile = visibile;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public UtenteDTO getUtente() {
        return utente;
    }

    public void setUtente(UtenteDTO utente) {
        this.utente = utente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + Objects.hashCode(this.testo);
        hash = 37 * hash + Objects.hashCode(this.dataInserimento);
        hash = 37 * hash + Objects.hashCode(this.risposta);
        hash = 37 * hash + Objects.hashCode(this.dataRisposta);
        hash = 37 * hash + Objects.hashCode(this.visibile);
        hash = 37 * hash + Objects.hashCode(this.post);
        hash = 37 * hash + Objects.hashCode(this.utente);
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
        final CommentoDTO other = (CommentoDTO) obj;
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
        return "CommentoDTO{" + "id=" + id + ", testo=" + testo + ", dataInserimento=" + dataInserimento + ", risposta=" + risposta + ", dataRisposta=" + dataRisposta + ", visibile=" + visibile + ", post=" + post + ", utente=" + utente + '}';
    }

}
