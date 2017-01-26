package fr.istic.crm.service;

import fr.istic.crm.service.dto.IntervenantDTO;
import java.util.List;

/**
 * Service Interface for managing Intervenant.
 */
public interface IntervenantService {

    /**
     * Save a intervenant.
     *
     * @param intervenantDTO the entity to save
     * @return the persisted entity
     */
    IntervenantDTO save(IntervenantDTO intervenantDTO);

    /**
     *  Get all the intervenants.
     *  
     *  @return the list of entities
     */
    List<IntervenantDTO> findAll();

    /**
     *  Get the "id" intervenant.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    IntervenantDTO findOne(Long id);

    /**
     *  Delete the "id" intervenant.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the intervenant corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<IntervenantDTO> search(String query);
}
