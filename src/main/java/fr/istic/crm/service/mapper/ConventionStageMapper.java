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

    @Mapping(source = "siege.id", target = "siegeId")
    @Mapping(source = "lieuStage.id", target = "lieuStageId")
    @Mapping(source = "tuteur.id", target = "tuteurId")
    @Mapping(source = "maitreStage.id", target = "maitreStageId")
    @Mapping(source = "etudiant.id", target = "etudiantId")
    ConventionStageDTO conventionStageToConventionStageDTO(ConventionStage conventionStage);

    List<ConventionStageDTO> conventionStagesToConventionStageDTOs(List<ConventionStage> conventionStages);

    @Mapping(source = "siegeId", target = "siege")
    @Mapping(source = "lieuStageId", target = "lieuStage")
    @Mapping(source = "tuteurId", target = "tuteur")
    @Mapping(source = "maitreStageId", target = "maitreStage")
    @Mapping(source = "etudiantId", target = "etudiant")
    ConventionStage conventionStageDTOToConventionStage(ConventionStageDTO conventionStageDTO);

    List<ConventionStage> conventionStageDTOsToConventionStages(List<ConventionStageDTO> conventionStageDTOs);

    default Site siteFromId(Long id) {
        if (id == null) {
            return null;
        }
        Site site = new Site();
        site.setId(id);
        return site;
    }

    default Tuteur tuteurFromId(Long id) {
        if (id == null) {
            return null;
        }
        Tuteur tuteur = new Tuteur();
        tuteur.setId(id);
        return tuteur;
    }

    default MaitreStage maitreStageFromId(Long id) {
        if (id == null) {
            return null;
        }
        MaitreStage maitreStage = new MaitreStage();
        maitreStage.setId(id);
        return maitreStage;
    }

    default Etudiant etudiantFromId(Long id) {
        if (id == null) {
            return null;
        }
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        return etudiant;
    }
}
