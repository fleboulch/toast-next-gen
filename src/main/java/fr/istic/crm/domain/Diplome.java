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
 * A Diplome.
 */
@Entity
@Table(name = "diplome")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "diplome")
public class Diplome implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "debut_version")
    private Long debutVersion;

    @Column(name = "fin_version")
    private Long finVersion;

    @OneToMany(mappedBy = "diplome")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Filiere> filieres = new HashSet<>();

    @ManyToMany(mappedBy = "diplomes")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Partenariat> partenariats = new HashSet<>();

    @ManyToMany(mappedBy = "diplomes")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Etudiant> etudiants = new HashSet<>();

    @ManyToMany(mappedBy = "diplomes")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Intervenant> intervenants = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Diplome nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getDebutVersion() {
        return debutVersion;
    }

    public Diplome debutVersion(Long debutVersion) {
        this.debutVersion = debutVersion;
        return this;
    }

    public void setDebutVersion(Long debutVersion) {
        this.debutVersion = debutVersion;
    }

    public Long getFinVersion() {
        return finVersion;
    }

    public Diplome finVersion(Long finVersion) {
        this.finVersion = finVersion;
        return this;
    }

    public void setFinVersion(Long finVersion) {
        this.finVersion = finVersion;
    }

    public Set<Filiere> getFilieres() {
        return filieres;
    }

    public Diplome filieres(Set<Filiere> filieres) {
        this.filieres = filieres;
        return this;
    }

    public Diplome addFiliere(Filiere filiere) {
        filieres.add(filiere);
        filiere.setDiplome(this);
        return this;
    }

    public Diplome removeFiliere(Filiere filiere) {
        filieres.remove(filiere);
        filiere.setDiplome(null);
        return this;
    }

    public void setFilieres(Set<Filiere> filieres) {
        this.filieres = filieres;
    }

    public Set<Partenariat> getPartenariats() {
        return partenariats;
    }

    public Diplome partenariats(Set<Partenariat> partenariats) {
        this.partenariats = partenariats;
        return this;
    }

    public Diplome addPartenariat(Partenariat partenariat) {
        partenariats.add(partenariat);
        partenariat.getDiplomes().add(this);
        return this;
    }

    public Diplome removePartenariat(Partenariat partenariat) {
        partenariats.remove(partenariat);
        partenariat.getDiplomes().remove(this);
        return this;
    }

    public void setPartenariats(Set<Partenariat> partenariats) {
        this.partenariats = partenariats;
    }

    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    public Diplome etudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
        return this;
    }

    public Diplome addEtudiant(Etudiant etudiant) {
        etudiants.add(etudiant);
        etudiant.getDiplomes().add(this);
        return this;
    }

    public Diplome removeEtudiant(Etudiant etudiant) {
        etudiants.remove(etudiant);
        etudiant.getDiplomes().remove(this);
        return this;
    }

    public void setEtudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public Set<Intervenant> getIntervenants() {
        return intervenants;
    }

    public Diplome intervenants(Set<Intervenant> intervenants) {
        this.intervenants = intervenants;
        return this;
    }

    public Diplome addIntervenant(Intervenant intervenant) {
        intervenants.add(intervenant);
        intervenant.getDiplomes().add(this);
        return this;
    }

    public Diplome removeIntervenant(Intervenant intervenant) {
        intervenants.remove(intervenant);
        intervenant.getDiplomes().remove(this);
        return this;
    }

    public void setIntervenants(Set<Intervenant> intervenants) {
        this.intervenants = intervenants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Diplome diplome = (Diplome) o;
        if (diplome.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, diplome.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Diplome{" +
            "id=" + id +
            ", nom='" + nom + "'" +
            ", debutVersion='" + debutVersion + "'" +
            ", finVersion='" + finVersion + "'" +
            '}';
    }
}
