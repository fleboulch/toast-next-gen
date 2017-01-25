package fr.istic.crm.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A EntreprisePartenaire.
 */
@Entity
@Table(name = "entreprise_partenaire")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "entreprisepartenaire")
public class EntreprisePartenaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_debut")
    private ZonedDateTime dateDebut;

    @Column(name = "date_fin")
    private ZonedDateTime dateFin;

    @ManyToOne
    private Entreprise entreprise;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "entreprise_partenaire_diplome",
               joinColumns = @JoinColumn(name="entreprise_partenaires_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="diplomes_id", referencedColumnName="ID"))
    private Set<Diplome> diplomes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateDebut() {
        return dateDebut;
    }

    public EntreprisePartenaire dateDebut(ZonedDateTime dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(ZonedDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public ZonedDateTime getDateFin() {
        return dateFin;
    }

    public EntreprisePartenaire dateFin(ZonedDateTime dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(ZonedDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public EntreprisePartenaire entreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
        return this;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Set<Diplome> getDiplomes() {
        return diplomes;
    }

    public EntreprisePartenaire diplomes(Set<Diplome> diplomes) {
        this.diplomes = diplomes;
        return this;
    }

    public EntreprisePartenaire addDiplome(Diplome diplome) {
        diplomes.add(diplome);
        diplome.getEntreprisePartenaires().add(this);
        return this;
    }

    public EntreprisePartenaire removeDiplome(Diplome diplome) {
        diplomes.remove(diplome);
        diplome.getEntreprisePartenaires().remove(this);
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
        EntreprisePartenaire entreprisePartenaire = (EntreprisePartenaire) o;
        if (entreprisePartenaire.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, entreprisePartenaire.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EntreprisePartenaire{" +
            "id=" + id +
            ", dateDebut='" + dateDebut + "'" +
            ", dateFin='" + dateFin + "'" +
            '}';
    }
}
