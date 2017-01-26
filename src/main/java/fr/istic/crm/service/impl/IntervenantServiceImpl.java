package fr.istic.crm.service.impl;

import fr.istic.crm.service.IntervenantService;
import fr.istic.crm.domain.Intervenant;
import fr.istic.crm.repository.IntervenantRepository;
import fr.istic.crm.repository.search.IntervenantSearchRepository;
import fr.istic.crm.service.dto.IntervenantDTO;
import fr.istic.crm.service.mapper.IntervenantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Intervenant.
 */
@Service
@Transactional
public class IntervenantServiceImpl implements IntervenantService{

    private final Logger log = LoggerFactory.getLogger(IntervenantServiceImpl.class);
    
    @Inject
    private IntervenantRepository intervenantRepository;

    @Inject
    private IntervenantMapper intervenantMapper;

    @Inject
    private IntervenantSearchRepository intervenantSearchRepository;

    /**
     * Save a intervenant.
     *
     * @param intervenantDTO the entity to save
     * @return the persisted entity
     */
    public IntervenantDTO save(IntervenantDTO intervenantDTO) {
        log.debug("Request to save Intervenant : {}", intervenantDTO);
        Intervenant intervenant = intervenantMapper.intervenantDTOToIntervenant(intervenantDTO);
        intervenant = intervenantRepository.save(intervenant);
        IntervenantDTO result = intervenantMapper.intervenantToIntervenantDTO(intervenant);
        intervenantSearchRepository.save(intervenant);
        return result;
    }

    /**
     *  Get all the intervenants.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<IntervenantDTO> findAll() {
        log.debug("Request to get all Intervenants");
        List<IntervenantDTO> result = intervenantRepository.findAllWithEagerRelationships().stream()
            .map(intervenantMapper::intervenantToIntervenantDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one intervenant by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public IntervenantDTO findOne(Long id) {
        log.debug("Request to get Intervenant : {}", id);
        Intervenant intervenant = intervenantRepository.findOneWithEagerRelationships(id);
        IntervenantDTO intervenantDTO = intervenantMapper.intervenantToIntervenantDTO(intervenant);
        return intervenantDTO;
    }

    /**
     *  Delete the  intervenant by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Intervenant : {}", id);
        intervenantRepository.delete(id);
        intervenantSearchRepository.delete(id);
    }

    /**
     * Search for the intervenant corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<IntervenantDTO> search(String query) {
        log.debug("Request to search Intervenants for query {}", query);
        return StreamSupport
            .stream(intervenantSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(intervenantMapper::intervenantToIntervenantDTO)
            .collect(Collectors.toList());
    }
}
