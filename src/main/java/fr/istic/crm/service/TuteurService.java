package fr.istic.crm.service;

import fr.istic.crm.service.dto.TuteurDTO;
import java.util.List;

/**
 * Service Interface for managing Tuteur.
 */
public interface TuteurService {

    /**
     * Save a tuteur.
     *
     * @param tuteurDTO the entity to save
     * @return the persisted entity
     */
    TuteurDTO save(TuteurDTO tuteurDTO);

    /**
     *  Get all the tuteurs.
     *  
     *  @return the list of entities
     */
    List<TuteurDTO> findAll();

    /**
     *  Get the "id" tuteur.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TuteurDTO findOne(Long id);

    /**
     *  Delete the "id" tuteur.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the tuteur corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<TuteurDTO> search(String query);
}
