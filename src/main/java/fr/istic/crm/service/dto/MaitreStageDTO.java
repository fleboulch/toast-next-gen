package fr.istic.crm.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the MaitreStage entity.
 */
public class MaitreStageDTO implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String telephone;

    private String mail;

    private String fonction;

    private Boolean ancienEtudiant;

    private Long debutVersion;

    private Long finVersion;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    public Boolean getAncienEtudiant() {
        return ancienEtudiant;
    }

    public void setAncienEtudiant(Boolean ancienEtudiant) {
        this.ancienEtudiant = ancienEtudiant;
    }
    public Long getDebutVersion() {
        return debutVersion;
    }

    public void setDebutVersion(Long debutVersion) {
        this.debutVersion = debutVersion;
    }
    public Long getFinVersion() {
        return finVersion;
    }

    public void setFinVersion(Long finVersion) {
        this.finVersion = finVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MaitreStageDTO maitreStageDTO = (MaitreStageDTO) o;

        if ( ! Objects.equals(id, maitreStageDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MaitreStageDTO{" +
            "id=" + id +
            ", nom='" + nom + "'" +
            ", prenom='" + prenom + "'" +
            ", telephone='" + telephone + "'" +
            ", mail='" + mail + "'" +
            ", fonction='" + fonction + "'" +
            ", ancienEtudiant='" + ancienEtudiant + "'" +
            ", debutVersion='" + debutVersion + "'" +
            ", finVersion='" + finVersion + "'" +
            '}';
    }
}
