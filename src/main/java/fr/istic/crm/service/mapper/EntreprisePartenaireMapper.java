package fr.istic.crm.service.mapper;

import fr.istic.crm.domain.*;
import fr.istic.crm.service.dto.EntreprisePartenaireDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity EntreprisePartenaire and its DTO EntreprisePartenaireDTO.
 */
@Mapper(componentModel = "spring", uses = {DiplomeMapper.class, })
public interface EntreprisePartenaireMapper {

    @Mapping(source = "entreprise.id", target = "entrepriseId")
    EntreprisePartenaireDTO entreprisePartenaireToEntreprisePartenaireDTO(EntreprisePartenaire entreprisePartenaire);

    List<EntreprisePartenaireDTO> entreprisePartenairesToEntreprisePartenaireDTOs(List<EntreprisePartenaire> entreprisePartenaires);

    @Mapping(source = "entrepriseId", target = "entreprise")
    EntreprisePartenaire entreprisePartenaireDTOToEntreprisePartenaire(EntreprisePartenaireDTO entreprisePartenaireDTO);

    List<EntreprisePartenaire> entreprisePartenaireDTOsToEntreprisePartenaires(List<EntreprisePartenaireDTO> entreprisePartenaireDTOs);

    default Entreprise entrepriseFromId(Long id) {
        if (id == null) {
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(id);
        return entreprise;
    }

    default Diplome diplomeFromId(Long id) {
        if (id == null) {
            return null;
        }
        Diplome diplome = new Diplome();
        diplome.setId(id);
        return diplome;
    }
}
