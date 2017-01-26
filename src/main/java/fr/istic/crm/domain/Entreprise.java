package fr.istic.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
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
 * not an ignored comment
 */
@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "entreprise")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "entreprise")
public class Entreprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "pays")
    private String pays;

    @NotNull
    @Column(name = "num_siret", nullable = false)
    private String numSiret;

    @NotNull
    @Column(name = "num_siren", nullable = false)
    private String numSiren;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "debut_version")
    private Long debutVersion;

    @Column(name = "fin_version")
    private Long finVersion;

    @OneToMany(mappedBy = "entreprise")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Partenariat> partenariats = new HashSet<>();

    @OneToMany(mappedBy = "entreprise")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Site> sites = new HashSet<>();

    @OneToMany(mappedBy = "entreprise")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Intervenant> intervenants = new HashSet<>();

    @ManyToOne
    private Groupe groupe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Entreprise nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public Entreprise pays(String pays) {
        this.pays = pays;
        return this;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getNumSiret() {
        return numSiret;
    }

    public Entreprise numSiret(String numSiret) {
        this.numSiret = numSiret;
        return this;
    }

    public void setNumSiret(String numSiret) {
        this.numSiret = numSiret;
    }

    public String getNumSiren() {
        return numSiren;
    }

    public Entreprise numSiren(String numSiren) {
        this.numSiren = numSiren;
        return this;
    }

    public void setNumSiren(String numSiren) {
        this.numSiren = numSiren;
    }

    public String getTelephone() {
        return telephone;
    }

    public Entreprise telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getDebutVersion() {
        return debutVersion;
    }

    public Entreprise debutVersion(Long debutVersion) {
        this.debutVersion = debutVersion;
        return this;
    }

    public void setDebutVersion(Long debutVersion) {
        this.debutVersion = debutVersion;
    }

    public Long getFinVersion() {
        return finVersion;
    }

    public Entreprise finVersion(Long finVersion) {
        this.finVersion = finVersion;
        return this;
    }

    public void setFinVersion(Long finVersion) {
        this.finVersion = finVersion;
    }

    public Set<Partenariat> getPartenariats() {
        return partenariats;
    }

    public Entreprise partenariats(Set<Partenariat> partenariats) {
        this.partenariats = partenariats;
        return this;
    }

    public Entreprise addPartenariat(Partenariat partenariat) {
        partenariats.add(partenariat);
        partenariat.setEntreprise(this);
        return this;
    }

    public Entreprise removePartenariat(Partenariat partenariat) {
        partenariats.remove(partenariat);
        partenariat.setEntreprise(null);
        return this;
    }

    public void setPartenariats(Set<Partenariat> partenariats) {
        this.partenariats = partenariats;
    }

    public Set<Site> getSites() {
        return sites;
    }

    public Entreprise sites(Set<Site> sites) {
        this.sites = sites;
        return this;
    }

    public Entreprise addSite(Site site) {
        sites.add(site);
        site.setEntreprise(this);
        return this;
    }

    public Entreprise removeSite(Site site) {
        sites.remove(site);
        site.setEntreprise(null);
        return this;
    }

    public void setSites(Set<Site> sites) {
        this.sites = sites;
    }

    public Set<Intervenant> getIntervenants() {
        return intervenants;
    }

    public Entreprise intervenants(Set<Intervenant> intervenants) {
        this.intervenants = intervenants;
        return this;
    }

    public Entreprise addIntervenant(Intervenant intervenant) {
        intervenants.add(intervenant);
        intervenant.setEntreprise(this);
        return this;
    }

    public Entreprise removeIntervenant(Intervenant intervenant) {
        intervenants.remove(intervenant);
        intervenant.setEntreprise(null);
        return this;
    }

    public void setIntervenants(Set<Intervenant> intervenants) {
        this.intervenants = intervenants;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public Entreprise groupe(Groupe groupe) {
        this.groupe = groupe;
        return this;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entreprise entreprise = (Entreprise) o;
        if (entreprise.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, entreprise.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Entreprise{" +
            "id=" + id +
            ", nom='" + nom + "'" +
            ", pays='" + pays + "'" +
            ", numSiret='" + numSiret + "'" +
            ", numSiren='" + numSiren + "'" +
            ", telephone='" + telephone + "'" +
            ", debutVersion='" + debutVersion + "'" +
            ", finVersion='" + finVersion + "'" +
            '}';
    }
}
