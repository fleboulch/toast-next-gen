package fr.istic.crm.service.impl;

import fr.istic.crm.service.EntreprisePartenaireService;
import fr.istic.crm.domain.EntreprisePartenaire;
import fr.istic.crm.repository.EntreprisePartenaireRepository;
import fr.istic.crm.repository.search.EntreprisePartenaireSearchRepository;
import fr.istic.crm.service.dto.EntreprisePartenaireDTO;
import fr.istic.crm.service.mapper.EntreprisePartenaireMapper;
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
 * Service Implementation for managing EntreprisePartenaire.
 */
@Service
@Transactional
public class EntreprisePartenaireServiceImpl implements EntreprisePartenaireService{

    private final Logger log = LoggerFactory.getLogger(EntreprisePartenaireServiceImpl.class);
    
    @Inject
    private EntreprisePartenaireRepository entreprisePartenaireRepository;

    @Inject
    private EntreprisePartenaireMapper entreprisePartenaireMapper;

    @Inject
    private EntreprisePartenaireSearchRepository entreprisePartenaireSearchRepository;

    /**
     * Save a entreprisePartenaire.
     *
     * @param entreprisePartenaireDTO the entity to save
     * @return the persisted entity
     */
    public EntreprisePartenaireDTO save(EntreprisePartenaireDTO entreprisePartenaireDTO) {
        log.debug("Request to save EntreprisePartenaire : {}", entreprisePartenaireDTO);
        EntreprisePartenaire entreprisePartenaire = entreprisePartenaireMapper.entreprisePartenaireDTOToEntreprisePartenaire(entreprisePartenaireDTO);
        entreprisePartenaire = entreprisePartenaireRepository.save(entreprisePartenaire);
        EntreprisePartenaireDTO result = entreprisePartenaireMapper.entreprisePartenaireToEntreprisePartenaireDTO(entreprisePartenaire);
        entreprisePartenaireSearchRepository.save(entreprisePartenaire);
        return result;
    }

    /**
     *  Get all the entreprisePartenaires.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<EntreprisePartenaireDTO> findAll() {
        log.debug("Request to get all EntreprisePartenaires");
        List<EntreprisePartenaireDTO> result = entreprisePartenaireRepository.findAllWithEagerRelationships().stream()
            .map(entreprisePartenaireMapper::entreprisePartenaireToEntreprisePartenaireDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one entreprisePartenaire by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public EntreprisePartenaireDTO findOne(Long id) {
        log.debug("Request to get EntreprisePartenaire : {}", id);
        EntreprisePartenaire entreprisePartenaire = entreprisePartenaireRepository.findOneWithEagerRelationships(id);
        EntreprisePartenaireDTO entreprisePartenaireDTO = entreprisePartenaireMapper.entreprisePartenaireToEntreprisePartenaireDTO(entreprisePartenaire);
        return entreprisePartenaireDTO;
    }

    /**
     *  Delete the  entreprisePartenaire by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete EntreprisePartenaire : {}", id);
        entreprisePartenaireRepository.delete(id);
        entreprisePartenaireSearchRepository.delete(id);
    }

    /**
     * Search for the entreprisePartenaire corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EntreprisePartenaireDTO> search(String query) {
        log.debug("Request to search EntreprisePartenaires for query {}", query);
        return StreamSupport
            .stream(entreprisePartenaireSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(entreprisePartenaireMapper::entreprisePartenaireToEntreprisePartenaireDTO)
            .collect(Collectors.toList());
    }
}
