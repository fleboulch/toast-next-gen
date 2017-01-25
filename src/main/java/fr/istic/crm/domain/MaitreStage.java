package fr.istic.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MaitreStage.
 */
@Entity
@Table(name = "maitre_stage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "maitrestage")
public class MaitreStage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "mail")
    private String mail;

    @Column(name = "fonction")
    private String fonction;

    @Column(name = "ancien_etudiant")
    private Boolean ancienEtudiant;

    @Column(name = "debut_version")
    private Long debutVersion;

    @Column(name = "fin_version")
    private Long finVersion;

    @OneToMany(mappedBy = "maitreStage")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ConventionStage> conventionStages = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public MaitreStage nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public MaitreStage prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public MaitreStage telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public MaitreStage mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFonction() {
        return fonction;
    }

    public MaitreStage fonction(String fonction) {
        this.fonction = fonction;
        return this;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Boolean isAncienEtudiant() {
        return ancienEtudiant;
    }

    public MaitreStage ancienEtudiant(Boolean ancienEtudiant) {
        this.ancienEtudiant = ancienEtudiant;
        return this;
    }

    public void setAncienEtudiant(Boolean ancienEtudiant) {
        this.ancienEtudiant = ancienEtudiant;
    }

    public Long getDebutVersion() {
        return debutVersion;
    }

    public MaitreStage debutVersion(Long debutVersion) {
        this.debutVersion = debutVersion;
        return this;
    }

    public void setDebutVersion(Long debutVersion) {
        this.debutVersion = debutVersion;
    }

    public Long getFinVersion() {
        return finVersion;
    }

    public MaitreStage finVersion(Long finVersion) {
        this.finVersion = finVersion;
        return this;
    }

    public void setFinVersion(Long finVersion) {
        this.finVersion = finVersion;
    }

    public Set<ConventionStage> getConventionStages() {
        return conventionStages;
    }

    public MaitreStage conventionStages(Set<ConventionStage> conventionStages) {
        this.conventionStages = conventionStages;
        return this;
    }

    public MaitreStage addConventionStage(ConventionStage conventionStage) {
        conventionStages.add(conventionStage);
        conventionStage.setMaitreStage(this);
        return this;
    }

    public MaitreStage removeConventionStage(ConventionStage conventionStage) {
        conventionStages.remove(conventionStage);
        conventionStage.setMaitreStage(null);
        return this;
    }

    public void setConventionStages(Set<ConventionStage> conventionStages) {
        this.conventionStages = conventionStages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MaitreStage maitreStage = (MaitreStage) o;
        if (maitreStage.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, maitreStage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MaitreStage{" +
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
