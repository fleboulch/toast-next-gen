package fr.istic.crm.service.mapper;

import fr.istic.crm.domain.*;
import fr.istic.crm.service.dto.IntervenantDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Intervenant and its DTO IntervenantDTO.
 */
@Mapper(componentModel = "spring", uses = {DiplomeMapper.class, })
public interface IntervenantMapper {

    @Mapping(source = "entreprise.id", target = "entrepriseId")
    IntervenantDTO intervenantToIntervenantDTO(Intervenant intervenant);

    List<IntervenantDTO> intervenantsToIntervenantDTOs(List<Intervenant> intervenants);

    @Mapping(source = "entrepriseId", target = "entreprise")
    Intervenant intervenantDTOToIntervenant(IntervenantDTO intervenantDTO);

    List<Intervenant> intervenantDTOsToIntervenants(List<IntervenantDTO> intervenantDTOs);

    default Diplome diplomeFromId(Long id) {
        if (id == null) {
            return null;
        }
        Diplome diplome = new Diplome();
        diplome.setId(id);
        return diplome;
    }

    default Entreprise entrepriseFromId(Long id) {
        if (id == null) {
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(id);
        return entreprise;
    }
}
