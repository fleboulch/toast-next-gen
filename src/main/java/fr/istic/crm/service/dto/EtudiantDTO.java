package fr.istic.crm.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Etudiant entity.
 */
public class EtudiantDTO implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String mail;

    private String sexe;

    @NotNull
    private String numEtudiant;


    private Set<DiplomeDTO> diplomes = new HashSet<>();

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
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public String getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public Set<DiplomeDTO> getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(Set<DiplomeDTO> diplomes) {
        this.diplomes = diplomes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtudiantDTO etudiantDTO = (EtudiantDTO) o;

        if ( ! Objects.equals(id, etudiantDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EtudiantDTO{" +
            "id=" + id +
            ", nom='" + nom + "'" +
            ", prenom='" + prenom + "'" +
            ", mail='" + mail + "'" +
            ", sexe='" + sexe + "'" +
            ", numEtudiant='" + numEtudiant + "'" +
            '}';
    }
}
