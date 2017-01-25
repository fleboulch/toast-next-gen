package fr.istic.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Etudiant.
 */
@Entity
@Table(name = "etudiant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "etudiant")
public class Etudiant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "mail")
    private String mail;

    @Column(name = "sexe")
    private String sexe;

    @NotNull
    @Column(name = "num_etudiant", nullable = false)
    private String numEtudiant;

    @OneToMany(mappedBy = "etudiant")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ConventionStage> conventionStages = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "etudiant_diplome",
               joinColumns = @JoinColumn(name="etudiants_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="diplomes_id", referencedColumnName="ID"))
    private Set<Diplome> diplomes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Etudiant nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Etudiant prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public Etudiant mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSexe() {
        return sexe;
    }

    public Etudiant sexe(String sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNumEtudiant() {
        return numEtudiant;
    }

    public Etudiant numEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
        return this;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public Set<ConventionStage> getConventionStages() {
        return conventionStages;
    }

    public Etudiant conventionStages(Set<ConventionStage> conventionStages) {
        this.conventionStages = conventionStages;
        return this;
    }

    public Etudiant addConventionStage(ConventionStage conventionStage) {
        conventionStages.add(conventionStage);
        conventionStage.setEtudiant(this);
        return this;
    }

    public Etudiant removeConventionStage(ConventionStage conventionStage) {
        conventionStages.remove(conventionStage);
        conventionStage.setEtudiant(null);
        return this;
    }

    public void setConventionStages(Set<ConventionStage> conventionStages) {
        this.conventionStages = conventionStages;
    }

    public Set<Diplome> getDiplomes() {
        return diplomes;
    }

    public Etudiant diplomes(Set<Diplome> diplomes) {
        this.diplomes = diplomes;
        return this;
    }

    public Etudiant addDiplome(Diplome diplome) {
        diplomes.add(diplome);
        diplome.getEtudiants().add(this);
        return this;
    }

    public Etudiant removeDiplome(Diplome diplome) {
        diplomes.remove(diplome);
        diplome.getEtudiants().remove(this);
        return this;
    }

    public void setDiplomes(Set<Diplome> diplomes) {
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
        Etudiant etudiant = (Etudiant) o;
        if (etudiant.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, etudiant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Etudiant{" +
            "id=" + id +
            ", nom='" + nom + "'" +
            ", prenom='" + prenom + "'" +
            ", mail='" + mail + "'" +
            ", sexe='" + sexe + "'" +
            ", numEtudiant='" + numEtudiant + "'" +
            '}';
    }
}
