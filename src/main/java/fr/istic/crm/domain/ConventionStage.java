package fr.istic.crm.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ConventionStage.
 */
@Entity
@Table(name = "convention_stage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "conventionstage")
public class ConventionStage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sujet")
    private String sujet;

    @Column(name = "fonctions")
    private String fonctions;

    @Column(name = "competences")
    private String competences;

    @Column(name = "date_debut")
    private Long dateDebut;

    @Column(name = "date_fin")
    private Long dateFin;

    @ManyToOne
    private Site siege;

    @ManyToOne
    private Site lieuStage;

    @ManyToOne
    private Tuteur tuteur;

    @ManyToOne
    private MaitreStage maitreStage;

    @ManyToOne
    private Etudiant etudiant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public ConventionStage sujet(String sujet) {
        this.sujet = sujet;
        return this;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getFonctions() {
        return fonctions;
    }

    public ConventionStage fonctions(String fonctions) {
        this.fonctions = fonctions;
        return this;
    }

    public void setFonctions(String fonctions) {
        this.fonctions = fonctions;
    }

    public String getCompetences() {
        return competences;
    }

    public ConventionStage competences(String competences) {
        this.competences = competences;
        return this;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public Long getDateDebut() {
        return dateDebut;
    }

    public ConventionStage dateDebut(Long dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(Long dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Long getDateFin() {
        return dateFin;
    }

    public ConventionStage dateFin(Long dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(Long dateFin) {
        this.dateFin = dateFin;
    }

    public Site getSiege() {
        return siege;
    }

    public ConventionStage siege(Site site) {
        this.siege = site;
        return this;
    }

    public void setSiege(Site site) {
        this.siege = site;
    }

    public Site getLieuStage() {
        return lieuStage;
    }

    public ConventionStage lieuStage(Site site) {
        this.lieuStage = site;
        return this;
    }

    public void setLieuStage(Site site) {
        this.lieuStage = site;
    }

    public Tuteur getTuteur() {
        return tuteur;
    }

    public ConventionStage tuteur(Tuteur tuteur) {
        this.tuteur = tuteur;
        return this;
    }

    public void setTuteur(Tuteur tuteur) {
        this.tuteur = tuteur;
    }

    public MaitreStage getMaitreStage() {
        return maitreStage;
    }

    public ConventionStage maitreStage(MaitreStage maitreStage) {
        this.maitreStage = maitreStage;
        return this;
    }

    public void setMaitreStage(MaitreStage maitreStage) {
        this.maitreStage = maitreStage;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public ConventionStage etudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
        return this;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConventionStage conventionStage = (ConventionStage) o;
        if (conventionStage.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, conventionStage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ConventionStage{" +
            "id=" + id +
            ", sujet='" + sujet + "'" +
            ", fonctions='" + fonctions + "'" +
            ", competences='" + competences + "'" +
            ", dateDebut='" + dateDebut + "'" +
            ", dateFin='" + dateFin + "'" +
            '}';
    }
}
