package fr.istic.crm.service;

import fr.istic.crm.service.dto.PartenariatDTO;
import java.util.List;

/**
 * Service Interface for managing Partenariat.
 */
public interface PartenariatService {

    /**
     * Save a partenariat.
     *
     * @param partenariatDTO the entity to save
     * @return the persisted entity
     */
    PartenariatDTO save(PartenariatDTO partenariatDTO);

    /**
     *  Get all the partenariats.
     *  
     *  @return the list of entities
     */
    List<PartenariatDTO> findAll();

    /**
     *  Get the "id" partenariat.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PartenariatDTO findOne(Long id);

    /**
     *  Delete the "id" partenariat.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the partenariat corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<PartenariatDTO> search(String query);
}
