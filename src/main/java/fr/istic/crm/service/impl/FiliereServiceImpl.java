package fr.istic.crm.service.impl;

import fr.istic.crm.service.FiliereService;
import fr.istic.crm.domain.Filiere;
import fr.istic.crm.repository.FiliereRepository;
import fr.istic.crm.repository.search.FiliereSearchRepository;
import fr.istic.crm.service.dto.FiliereDTO;
import fr.istic.crm.service.mapper.FiliereMapper;
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
 * Service Implementation for managing Filiere.
 */
@Service
@Transactional
public class FiliereServiceImpl implements FiliereService{

    private final Logger log = LoggerFactory.getLogger(FiliereServiceImpl.class);
    
    @Inject
    private FiliereRepository filiereRepository;

    @Inject
    private FiliereMapper filiereMapper;

    @Inject
    private FiliereSearchRepository filiereSearchRepository;

    /**
     * Save a filiere.
     *
     * @param filiereDTO the entity to save
     * @return the persisted entity
     */
    public FiliereDTO save(FiliereDTO filiereDTO) {
        log.debug("Request to save Filiere : {}", filiereDTO);
        Filiere filiere = filiereMapper.filiereDTOToFiliere(filiereDTO);
        filiere = filiereRepository.save(filiere);
        FiliereDTO result = filiereMapper.filiereToFiliereDTO(filiere);
        filiereSearchRepository.save(filiere);
        return result;
    }

    /**
     *  Get all the filieres.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<FiliereDTO> findAll() {
        log.debug("Request to get all Filieres");
        List<FiliereDTO> result = filiereRepository.findAll().stream()
            .map(filiereMapper::filiereToFiliereDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one filiere by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public FiliereDTO findOne(Long id) {
        log.debug("Request to get Filiere : {}", id);
        Filiere filiere = filiereRepository.findOne(id);
        FiliereDTO filiereDTO = filiereMapper.filiereToFiliereDTO(filiere);
        return filiereDTO;
    }

    /**
     *  Delete the  filiere by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Filiere : {}", id);
        filiereRepository.delete(id);
        filiereSearchRepository.delete(id);
    }

    /**
     * Search for the filiere corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FiliereDTO> search(String query) {
        log.debug("Request to search Filieres for query {}", query);
        return StreamSupport
            .stream(filiereSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(filiereMapper::filiereToFiliereDTO)
            .collect(Collectors.toList());
    }
}
