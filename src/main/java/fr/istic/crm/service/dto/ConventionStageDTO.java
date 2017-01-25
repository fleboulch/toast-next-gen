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


    private Long siegeId;
    
    private Long lieuStageId;
    
    private Long tuteurId;
    
    private Long maitreStageId;
    
    private Long etudiantId;
    
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

    public Long getSiegeId() {
        return siegeId;
    }

    public void setSiegeId(Long siteId) {
        this.siegeId = siteId;
    }

    public Long getLieuStageId() {
        return lieuStageId;
    }

    public void setLieuStageId(Long siteId) {
        this.lieuStageId = siteId;
    }

    public Long getTuteurId() {
        return tuteurId;
    }

    public void setTuteurId(Long tuteurId) {
        this.tuteurId = tuteurId;
    }

    public Long getMaitreStageId() {
        return maitreStageId;
    }

    public void setMaitreStageId(Long maitreStageId) {
        this.maitreStageId = maitreStageId;
    }

    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
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
