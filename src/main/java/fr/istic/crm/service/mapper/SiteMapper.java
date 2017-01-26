package fr.istic.crm.service.mapper;

import fr.istic.crm.domain.*;
import fr.istic.crm.service.dto.SiteDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Site and its DTO SiteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SiteMapper {

    @Mapping(source = "entreprise.id", target = "entrepriseId")
    SiteDTO siteToSiteDTO(Site site);

    List<SiteDTO> sitesToSiteDTOs(List<Site> sites);

    @Mapping(source = "entrepriseId", target = "entreprise")
    @Mapping(target = "sieges", ignore = true)
    @Mapping(target = "lieuStages", ignore = true)
    Site siteDTOToSite(SiteDTO siteDTO);

    List<Site> siteDTOsToSites(List<SiteDTO> siteDTOs);

    default Entreprise entrepriseFromId(Long id) {
        if (id == null) {
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(id);
        return entreprise;
    }
}
