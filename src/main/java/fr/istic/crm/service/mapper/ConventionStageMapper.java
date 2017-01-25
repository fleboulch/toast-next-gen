package fr.istic.crm.service.mapper;

import fr.istic.crm.domain.*;
import fr.istic.crm.service.dto.ConventionStageDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ConventionStage and its DTO ConventionStageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConventionStageMapper {

    @Mapping(source = "tuteur.id", target = "tuteurId")
    @Mapping(source = "etudiant.id", target = "etudiantId")
    @Mapping(source = "site.id", target = "siteId")
    @Mapping(source = "site.id", target = "siteId")
    ConventionStageDTO conventionStageToConventionStageDTO(ConventionStage conventionStage);

    List<ConventionStageDTO> conventionStagesToConventionStageDTOs(List<ConventionStage> conventionStages);

    @Mapping(source = "tuteurId", target = "tuteur")
    @Mapping(source = "etudiantId", target = "etudiant")
    @Mapping(source = "siteId", target = "site")
    @Mapping(source = "siteId", target = "site")
    ConventionStage conventionStageDTOToConventionStage(ConventionStageDTO conventionStageDTO);

    List<ConventionStage> conventionStageDTOsToConventionStages(List<ConventionStageDTO> conventionStageDTOs);

    default Tuteur tuteurFromId(Long id) {
        if (id == null) {
            return null;
        }
        Tuteur tuteur = new Tuteur();
        tuteur.setId(id);
        return tuteur;
    }

    default Etudiant etudiantFromId(Long id) {
        if (id == null) {
            return null;
        }
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        return etudiant;
    }

    default Site siteFromId(Long id) {
        if (id == null) {
            return null;
        }
        Site site = new Site();
        site.setId(id);
        return site;
    }
}
