package fr.istic.crm.service.mapper;

import fr.istic.crm.domain.*;
import fr.istic.crm.service.dto.EntrepriseDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Entreprise and its DTO EntrepriseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntrepriseMapper {

    @Mapping(source = "groupe.id", target = "groupeId")
    EntrepriseDTO entrepriseToEntrepriseDTO(Entreprise entreprise);

    List<EntrepriseDTO> entreprisesToEntrepriseDTOs(List<Entreprise> entreprises);

    @Mapping(target = "partenariats", ignore = true)
    @Mapping(target = "sites", ignore = true)
    @Mapping(target = "intervenants", ignore = true)
    @Mapping(source = "groupeId", target = "groupe")
    Entreprise entrepriseDTOToEntreprise(EntrepriseDTO entrepriseDTO);

    List<Entreprise> entrepriseDTOsToEntreprises(List<EntrepriseDTO> entrepriseDTOs);

    default Groupe groupeFromId(Long id) {
        if (id == null) {
            return null;
        }
        Groupe groupe = new Groupe();
        groupe.setId(id);
        return groupe;
    }
}
