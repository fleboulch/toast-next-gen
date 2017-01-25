package fr.istic.crm.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.crm.service.MaitreStageService;
import fr.istic.crm.web.rest.util.HeaderUtil;
import fr.istic.crm.service.dto.MaitreStageDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing MaitreStage.
 */
@RestController
@RequestMapping("/api")
public class MaitreStageResource {

    private final Logger log = LoggerFactory.getLogger(MaitreStageResource.class);
        
    @Inject
    private MaitreStageService maitreStageService;

    /**
     * POST  /maitre-stages : Create a new maitreStage.
     *
     * @param maitreStageDTO the maitreStageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new maitreStageDTO, or with status 400 (Bad Request) if the maitreStage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/maitre-stages")
    @Timed
    public ResponseEntity<MaitreStageDTO> createMaitreStage(@RequestBody MaitreStageDTO maitreStageDTO) throws URISyntaxException {
        log.debug("REST request to save MaitreStage : {}", maitreStageDTO);
        if (maitreStageDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("maitreStage", "idexists", "A new maitreStage cannot already have an ID")).body(null);
        }
        MaitreStageDTO result = maitreStageService.save(maitreStageDTO);
        return ResponseEntity.created(new URI("/api/maitre-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("maitreStage", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /maitre-stages : Updates an existing maitreStage.
     *
     * @param maitreStageDTO the maitreStageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated maitreStageDTO,
     * or with status 400 (Bad Request) if the maitreStageDTO is not valid,
     * or with status 500 (Internal Server Error) if the maitreStageDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/maitre-stages")
    @Timed
    public ResponseEntity<MaitreStageDTO> updateMaitreStage(@RequestBody MaitreStageDTO maitreStageDTO) throws URISyntaxException {
        log.debug("REST request to update MaitreStage : {}", maitreStageDTO);
        if (maitreStageDTO.getId() == null) {
            return createMaitreStage(maitreStageDTO);
        }
        MaitreStageDTO result = maitreStageService.save(maitreStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("maitreStage", maitreStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /maitre-stages : get all the maitreStages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of maitreStages in body
     */
    @GetMapping("/maitre-stages")
    @Timed
    public List<MaitreStageDTO> getAllMaitreStages() {
        log.debug("REST request to get all MaitreStages");
        return maitreStageService.findAll();
    }

    /**
     * GET  /maitre-stages/:id : get the "id" maitreStage.
     *
     * @param id the id of the maitreStageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the maitreStageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/maitre-stages/{id}")
    @Timed
    public ResponseEntity<MaitreStageDTO> getMaitreStage(@PathVariable Long id) {
        log.debug("REST request to get MaitreStage : {}", id);
        MaitreStageDTO maitreStageDTO = maitreStageService.findOne(id);
        return Optional.ofNullable(maitreStageDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /maitre-stages/:id : delete the "id" maitreStage.
     *
     * @param id the id of the maitreStageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/maitre-stages/{id}")
    @Timed
    public ResponseEntity<Void> deleteMaitreStage(@PathVariable Long id) {
        log.debug("REST request to delete MaitreStage : {}", id);
        maitreStageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("maitreStage", id.toString())).build();
    }

    /**
     * SEARCH  /_search/maitre-stages?query=:query : search for the maitreStage corresponding
     * to the query.
     *
     * @param query the query of the maitreStage search 
     * @return the result of the search
     */
    @GetMapping("/_search/maitre-stages")
    @Timed
    public List<MaitreStageDTO> searchMaitreStages(@RequestParam String query) {
        log.debug("REST request to search MaitreStages for query {}", query);
        return maitreStageService.search(query);
    }


}
