package fr.istic.crm.service.impl;

import fr.istic.crm.service.MaitreStageService;
import fr.istic.crm.domain.MaitreStage;
import fr.istic.crm.repository.MaitreStageRepository;
import fr.istic.crm.repository.search.MaitreStageSearchRepository;
import fr.istic.crm.service.dto.MaitreStageDTO;
import fr.istic.crm.service.mapper.MaitreStageMapper;
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
 * Service Implementation for managing MaitreStage.
 */
@Service
@Transactional
public class MaitreStageServiceImpl implements MaitreStageService{

    private final Logger log = LoggerFactory.getLogger(MaitreStageServiceImpl.class);
    
    @Inject
    private MaitreStageRepository maitreStageRepository;

    @Inject
    private MaitreStageMapper maitreStageMapper;

    @Inject
    private MaitreStageSearchRepository maitreStageSearchRepository;

    /**
     * Save a maitreStage.
     *
     * @param maitreStageDTO the entity to save
     * @return the persisted entity
     */
    public MaitreStageDTO save(MaitreStageDTO maitreStageDTO) {
        log.debug("Request to save MaitreStage : {}", maitreStageDTO);
        MaitreStage maitreStage = maitreStageMapper.maitreStageDTOToMaitreStage(maitreStageDTO);
        maitreStage = maitreStageRepository.save(maitreStage);
        MaitreStageDTO result = maitreStageMapper.maitreStageToMaitreStageDTO(maitreStage);
        maitreStageSearchRepository.save(maitreStage);
        return result;
    }

    /**
     *  Get all the maitreStages.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<MaitreStageDTO> findAll() {
        log.debug("Request to get all MaitreStages");
        List<MaitreStageDTO> result = maitreStageRepository.findAll().stream()
            .map(maitreStageMapper::maitreStageToMaitreStageDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one maitreStage by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public MaitreStageDTO findOne(Long id) {
        log.debug("Request to get MaitreStage : {}", id);
        MaitreStage maitreStage = maitreStageRepository.findOne(id);
        MaitreStageDTO maitreStageDTO = maitreStageMapper.maitreStageToMaitreStageDTO(maitreStage);
        return maitreStageDTO;
    }

    /**
     *  Delete the  maitreStage by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MaitreStage : {}", id);
        maitreStageRepository.delete(id);
        maitreStageSearchRepository.delete(id);
    }

    /**
     * Search for the maitreStage corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<MaitreStageDTO> search(String query) {
        log.debug("Request to search MaitreStages for query {}", query);
        return StreamSupport
            .stream(maitreStageSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(maitreStageMapper::maitreStageToMaitreStageDTO)
            .collect(Collectors.toList());
    }
}
