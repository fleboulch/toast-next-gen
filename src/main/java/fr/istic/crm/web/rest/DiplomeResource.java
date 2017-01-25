package fr.istic.crm.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.crm.service.DiplomeService;
import fr.istic.crm.web.rest.util.HeaderUtil;
import fr.istic.crm.service.dto.DiplomeDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Diplome.
 */
@RestController
@RequestMapping("/api")
public class DiplomeResource {

    private final Logger log = LoggerFactory.getLogger(DiplomeResource.class);
        
    @Inject
    private DiplomeService diplomeService;

    /**
     * POST  /diplomes : Create a new diplome.
     *
     * @param diplomeDTO the diplomeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new diplomeDTO, or with status 400 (Bad Request) if the diplome has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/diplomes")
    @Timed
    public ResponseEntity<DiplomeDTO> createDiplome(@Valid @RequestBody DiplomeDTO diplomeDTO) throws URISyntaxException {
        log.debug("REST request to save Diplome : {}", diplomeDTO);
        if (diplomeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("diplome", "idexists", "A new diplome cannot already have an ID")).body(null);
        }
        DiplomeDTO result = diplomeService.save(diplomeDTO);
        return ResponseEntity.created(new URI("/api/diplomes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("diplome", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /diplomes : Updates an existing diplome.
     *
     * @param diplomeDTO the diplomeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated diplomeDTO,
     * or with status 400 (Bad Request) if the diplomeDTO is not valid,
     * or with status 500 (Internal Server Error) if the diplomeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/diplomes")
    @Timed
    public ResponseEntity<DiplomeDTO> updateDiplome(@Valid @RequestBody DiplomeDTO diplomeDTO) throws URISyntaxException {
        log.debug("REST request to update Diplome : {}", diplomeDTO);
        if (diplomeDTO.getId() == null) {
            return createDiplome(diplomeDTO);
        }
        DiplomeDTO result = diplomeService.save(diplomeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("diplome", diplomeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /diplomes : get all the diplomes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of diplomes in body
     */
    @GetMapping("/diplomes")
    @Timed
    public List<DiplomeDTO> getAllDiplomes() {
        log.debug("REST request to get all Diplomes");
        return diplomeService.findAll();
    }

    /**
     * GET  /diplomes/:id : get the "id" diplome.
     *
     * @param id the id of the diplomeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the diplomeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/diplomes/{id}")
    @Timed
    public ResponseEntity<DiplomeDTO> getDiplome(@PathVariable Long id) {
        log.debug("REST request to get Diplome : {}", id);
        DiplomeDTO diplomeDTO = diplomeService.findOne(id);
        return Optional.ofNullable(diplomeDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /diplomes/:id : delete the "id" diplome.
     *
     * @param id the id of the diplomeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/diplomes/{id}")
    @Timed
    public ResponseEntity<Void> deleteDiplome(@PathVariable Long id) {
        log.debug("REST request to delete Diplome : {}", id);
        diplomeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("diplome", id.toString())).build();
    }

    /**
     * SEARCH  /_search/diplomes?query=:query : search for the diplome corresponding
     * to the query.
     *
     * @param query the query of the diplome search 
     * @return the result of the search
     */
    @GetMapping("/_search/diplomes")
    @Timed
    public List<DiplomeDTO> searchDiplomes(@RequestParam String query) {
        log.debug("REST request to search Diplomes for query {}", query);
        return diplomeService.search(query);
    }


}
