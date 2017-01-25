package fr.istic.crm.service;

import fr.istic.crm.service.dto.DiplomeDTO;
import java.util.List;

/**
 * Service Interface for managing Diplome.
 */
public interface DiplomeService {

    /**
     * Save a diplome.
     *
     * @param diplomeDTO the entity to save
     * @return the persisted entity
     */
    DiplomeDTO save(DiplomeDTO diplomeDTO);

    /**
     *  Get all the diplomes.
     *  
     *  @return the list of entities
     */
    List<DiplomeDTO> findAll();

    /**
     *  Get the "id" diplome.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DiplomeDTO findOne(Long id);

    /**
     *  Delete the "id" diplome.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the diplome corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<DiplomeDTO> search(String query);
}
