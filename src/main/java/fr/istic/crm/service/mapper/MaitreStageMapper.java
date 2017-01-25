package fr.istic.crm.service.mapper;

import fr.istic.crm.domain.*;
import fr.istic.crm.service.dto.MaitreStageDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity MaitreStage and its DTO MaitreStageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MaitreStageMapper {

    MaitreStageDTO maitreStageToMaitreStageDTO(MaitreStage maitreStage);

    List<MaitreStageDTO> maitreStagesToMaitreStageDTOs(List<MaitreStage> maitreStages);

    @Mapping(target = "conventionStages", ignore = true)
    MaitreStage maitreStageDTOToMaitreStage(MaitreStageDTO maitreStageDTO);

    List<MaitreStage> maitreStageDTOsToMaitreStages(List<MaitreStageDTO> maitreStageDTOs);
}
