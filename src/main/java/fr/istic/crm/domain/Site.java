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
 * A Site.
 */
@Entity
@Table(name = "site")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "site")
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "ville")
    private String ville;

    @Column(name = "pays")
    private String pays;

    @Column(name = "siege")
    private Boolean siege;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "debut_version")
    private Long debutVersion;

    @Column(name = "fin_version")
    private Long finVersion;

    @ManyToOne
    private Entreprise entreprise;

    @OneToMany(mappedBy = "site")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ConventionStage> sieges = new HashSet<>();

    @OneToMany(mappedBy = "site")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ConventionStage> lieuStages = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public Site adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public Site codePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public Site ville(String ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public Site pays(String pays) {
        this.pays = pays;
        return this;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Boolean isSiege() {
        return siege;
    }

    public Site siege(Boolean siege) {
        this.siege = siege;
        return this;
    }

    public void setSiege(Boolean siege) {
        this.siege = siege;
    }

    public String getTelephone() {
        return telephone;
    }

    public Site telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getDebutVersion() {
        return debutVersion;
    }

    public Site debutVersion(Long debutVersion) {
        this.debutVersion = debutVersion;
        return this;
    }

    public void setDebutVersion(Long debutVersion) {
        this.debutVersion = debutVersion;
    }

    public Long getFinVersion() {
        return finVersion;
    }

    public Site finVersion(Long finVersion) {
        this.finVersion = finVersion;
        return this;
    }

    public void setFinVersion(Long finVersion) {
        this.finVersion = finVersion;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public Site entreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
        return this;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Set<ConventionStage> getSieges() {
        return sieges;
    }

    public Site sieges(Set<ConventionStage> conventionStages) {
        this.sieges = conventionStages;
        return this;
    }

    public Site addSiege(ConventionStage conventionStage) {
        sieges.add(conventionStage);
        conventionStage.setSite(this);
        return this;
    }

    public Site removeSiege(ConventionStage conventionStage) {
        sieges.remove(conventionStage);
        conventionStage.setSite(null);
        return this;
    }

    public void setSieges(Set<ConventionStage> conventionStages) {
        this.sieges = conventionStages;
    }

    public Set<ConventionStage> getLieuStages() {
        return lieuStages;
    }

    public Site lieuStages(Set<ConventionStage> conventionStages) {
        this.lieuStages = conventionStages;
        return this;
    }

    public Site addLieuStage(ConventionStage conventionStage) {
        lieuStages.add(conventionStage);
        conventionStage.setSite(this);
        return this;
    }

    public Site removeLieuStage(ConventionStage conventionStage) {
        lieuStages.remove(conventionStage);
        conventionStage.setSite(null);
        return this;
    }

    public void setLieuStages(Set<ConventionStage> conventionStages) {
        this.lieuStages = conventionStages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Site site = (Site) o;
        if (site.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, site.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Site{" +
            "id=" + id +
            ", adresse='" + adresse + "'" +
            ", codePostal='" + codePostal + "'" +
            ", ville='" + ville + "'" +
            ", pays='" + pays + "'" +
            ", siege='" + siege + "'" +
            ", telephone='" + telephone + "'" +
            ", debutVersion='" + debutVersion + "'" +
            ", finVersion='" + finVersion + "'" +
            '}';
    }
}
