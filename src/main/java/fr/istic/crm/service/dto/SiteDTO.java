package fr.istic.crm.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Site entity.
 */
public class SiteDTO implements Serializable {

    private Long id;

    private String adresse;

    private String codePostal;

    private String ville;

    private String pays;

    private Boolean siege;

    private String telephone;

    private Long debutVersion;

    private Long finVersion;


    private Long entrepriseId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
    public Boolean getSiege() {
        return siege;
    }

    public void setSiege(Boolean siege) {
        this.siege = siege;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public Long getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(Long entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SiteDTO siteDTO = (SiteDTO) o;

        if ( ! Objects.equals(id, siteDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SiteDTO{" +
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
