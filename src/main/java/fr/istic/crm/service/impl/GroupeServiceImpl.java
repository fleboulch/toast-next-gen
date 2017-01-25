package fr.istic.crm.service.impl;

import fr.istic.crm.service.GroupeService;
import fr.istic.crm.domain.Groupe;
import fr.istic.crm.repository.GroupeRepository;
import fr.istic.crm.repository.search.GroupeSearchRepository;
import fr.istic.crm.service.dto.GroupeDTO;
import fr.istic.crm.service.mapper.GroupeMapper;
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
 * Service Implementation for managing Groupe.
 */
@Service
@Transactional
public class GroupeServiceImpl implements GroupeService{

    private final Logger log = LoggerFactory.getLogger(GroupeServiceImpl.class);
    
    @Inject
    private GroupeRepository groupeRepository;

    @Inject
    private GroupeMapper groupeMapper;

    @Inject
    private GroupeSearchRepository groupeSearchRepository;

    /**
     * Save a groupe.
     *
     * @param groupeDTO the entity to save
     * @return the persisted entity
     */
    public GroupeDTO save(GroupeDTO groupeDTO) {
        log.debug("Request to save Groupe : {}", groupeDTO);
        Groupe groupe = groupeMapper.groupeDTOToGroupe(groupeDTO);
        groupe = groupeRepository.save(groupe);
        GroupeDTO result = groupeMapper.groupeToGroupeDTO(groupe);
        groupeSearchRepository.save(groupe);
        return result;
    }

    /**
     *  Get all the groupes.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<GroupeDTO> findAll() {
        log.debug("Request to get all Groupes");
        List<GroupeDTO> result = groupeRepository.findAll().stream()
            .map(groupeMapper::groupeToGroupeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one groupe by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public GroupeDTO findOne(Long id) {
        log.debug("Request to get Groupe : {}", id);
        Groupe groupe = groupeRepository.findOne(id);
        GroupeDTO groupeDTO = groupeMapper.groupeToGroupeDTO(groupe);
        return groupeDTO;
    }

    /**
     *  Delete the  groupe by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Groupe : {}", id);
        groupeRepository.delete(id);
        groupeSearchRepository.delete(id);
    }

    /**
     * Search for the groupe corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<GroupeDTO> search(String query) {
        log.debug("Request to search Groupes for query {}", query);
        return StreamSupport
            .stream(groupeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(groupeMapper::groupeToGroupeDTO)
            .collect(Collectors.toList());
    }
}
