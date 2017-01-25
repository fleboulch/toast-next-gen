package fr.istic.crm.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the ConventionStage entity.
 */
public class ConventionStageDTO implements Serializable {

    private Long id;

    private String sujet;

    private String fonctions;

    private String competences;

    private ZonedDateTime dateDebut;

    private ZonedDateTime dateFin;


    private Long tuteurId;
    
    private Long etudiantId;
    
    private Long siteId;
    
    private Long siteId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }
    public String getFonctions() {
        return fonctions;
    }

    public void setFonctions(String fonctions) {
        this.fonctions = fonctions;
    }
    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }
    public ZonedDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(ZonedDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }
    public ZonedDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(ZonedDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Long getTuteurId() {
        return tuteurId;
    }

    public void setTuteurId(Long tuteurId) {
        this.tuteurId = tuteurId;
    }

    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConventionStageDTO conventionStageDTO = (ConventionStageDTO) o;

        if ( ! Objects.equals(id, conventionStageDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ConventionStageDTO{" +
            "id=" + id +
            ", sujet='" + sujet + "'" +
            ", fonctions='" + fonctions + "'" +
            ", competences='" + competences + "'" +
            ", dateDebut='" + dateDebut + "'" +
            ", dateFin='" + dateFin + "'" +
            '}';
    }
}
