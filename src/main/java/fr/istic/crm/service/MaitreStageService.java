package fr.istic.crm.service;

import fr.istic.crm.service.dto.MaitreStageDTO;
import java.util.List;

/**
 * Service Interface for managing MaitreStage.
 */
public interface MaitreStageService {

    /**
     * Save a maitreStage.
     *
     * @param maitreStageDTO the entity to save
     * @return the persisted entity
     */
    MaitreStageDTO save(MaitreStageDTO maitreStageDTO);

    /**
     *  Get all the maitreStages.
     *  
     *  @return the list of entities
     */
    List<MaitreStageDTO> findAll();

    /**
     *  Get the "id" maitreStage.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MaitreStageDTO findOne(Long id);

    /**
     *  Delete the "id" maitreStage.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the maitreStage corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<MaitreStageDTO> search(String query);
}
