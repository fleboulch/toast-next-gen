package fr.istic.crm.service.impl;

import fr.istic.crm.service.TuteurService;
import fr.istic.crm.domain.Tuteur;
import fr.istic.crm.repository.TuteurRepository;
import fr.istic.crm.repository.search.TuteurSearchRepository;
import fr.istic.crm.service.dto.TuteurDTO;
import fr.istic.crm.service.mapper.TuteurMapper;
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
 * Service Implementation for managing Tuteur.
 */
@Service
@Transactional
public class TuteurServiceImpl implements TuteurService{

    private final Logger log = LoggerFactory.getLogger(TuteurServiceImpl.class);
    
    @Inject
    private TuteurRepository tuteurRepository;

    @Inject
    private TuteurMapper tuteurMapper;

    @Inject
    private TuteurSearchRepository tuteurSearchRepository;

    /**
     * Save a tuteur.
     *
     * @param tuteurDTO the entity to save
     * @return the persisted entity
     */
    public TuteurDTO save(TuteurDTO tuteurDTO) {
        log.debug("Request to save Tuteur : {}", tuteurDTO);
        Tuteur tuteur = tuteurMapper.tuteurDTOToTuteur(tuteurDTO);
        tuteur = tuteurRepository.save(tuteur);
        TuteurDTO result = tuteurMapper.tuteurToTuteurDTO(tuteur);
        tuteurSearchRepository.save(tuteur);
        return result;
    }

    /**
     *  Get all the tuteurs.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TuteurDTO> findAll() {
        log.debug("Request to get all Tuteurs");
        List<TuteurDTO> result = tuteurRepository.findAll().stream()
            .map(tuteurMapper::tuteurToTuteurDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one tuteur by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public TuteurDTO findOne(Long id) {
        log.debug("Request to get Tuteur : {}", id);
        Tuteur tuteur = tuteurRepository.findOne(id);
        TuteurDTO tuteurDTO = tuteurMapper.tuteurToTuteurDTO(tuteur);
        return tuteurDTO;
    }

    /**
     *  Delete the  tuteur by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Tuteur : {}", id);
        tuteurRepository.delete(id);
        tuteurSearchRepository.delete(id);
    }

    /**
     * Search for the tuteur corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TuteurDTO> search(String query) {
        log.debug("Request to search Tuteurs for query {}", query);
        return StreamSupport
            .stream(tuteurSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(tuteurMapper::tuteurToTuteurDTO)
            .collect(Collectors.toList());
    }
}
