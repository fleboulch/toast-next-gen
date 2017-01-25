package fr.istic.crm.service;

import fr.istic.crm.service.dto.EntreprisePartenaireDTO;
import java.util.List;

/**
 * Service Interface for managing EntreprisePartenaire.
 */
public interface EntreprisePartenaireService {

    /**
     * Save a entreprisePartenaire.
     *
     * @param entreprisePartenaireDTO the entity to save
     * @return the persisted entity
     */
    EntreprisePartenaireDTO save(EntreprisePartenaireDTO entreprisePartenaireDTO);

    /**
     *  Get all the entreprisePartenaires.
     *  
     *  @return the list of entities
     */
    List<EntreprisePartenaireDTO> findAll();

    /**
     *  Get the "id" entreprisePartenaire.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    EntreprisePartenaireDTO findOne(Long id);

    /**
     *  Delete the "id" entreprisePartenaire.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the entreprisePartenaire corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<EntreprisePartenaireDTO> search(String query);
}
