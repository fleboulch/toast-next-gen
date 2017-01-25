package fr.istic.crm.service;

import fr.istic.crm.service.dto.FiliereDTO;
import java.util.List;

/**
 * Service Interface for managing Filiere.
 */
public interface FiliereService {

    /**
     * Save a filiere.
     *
     * @param filiereDTO the entity to save
     * @return the persisted entity
     */
    FiliereDTO save(FiliereDTO filiereDTO);

    /**
     *  Get all the filieres.
     *  
     *  @return the list of entities
     */
    List<FiliereDTO> findAll();

    /**
     *  Get the "id" filiere.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    FiliereDTO findOne(Long id);

    /**
     *  Delete the "id" filiere.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the filiere corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<FiliereDTO> search(String query);
}
