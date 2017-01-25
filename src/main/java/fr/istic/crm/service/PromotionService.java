package fr.istic.crm.service;

import fr.istic.crm.service.dto.PromotionDTO;
import java.util.List;

/**
 * Service Interface for managing Promotion.
 */
public interface PromotionService {

    /**
     * Save a promotion.
     *
     * @param promotionDTO the entity to save
     * @return the persisted entity
     */
    PromotionDTO save(PromotionDTO promotionDTO);

    /**
     *  Get all the promotions.
     *  
     *  @return the list of entities
     */
    List<PromotionDTO> findAll();

    /**
     *  Get the "id" promotion.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PromotionDTO findOne(Long id);

    /**
     *  Delete the "id" promotion.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the promotion corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<PromotionDTO> search(String query);
}
