package fr.istic.crm.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.crm.service.IntervenantService;
import fr.istic.crm.web.rest.util.HeaderUtil;
import fr.istic.crm.service.dto.IntervenantDTO;

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
 * REST controller for managing Intervenant.
 */
@RestController
@RequestMapping("/api")
public class IntervenantResource {

    private final Logger log = LoggerFactory.getLogger(IntervenantResource.class);
        
    @Inject
    private IntervenantService intervenantService;

    /**
     * POST  /intervenants : Create a new intervenant.
     *
     * @param intervenantDTO the intervenantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new intervenantDTO, or with status 400 (Bad Request) if the intervenant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/intervenants")
    @Timed
    public ResponseEntity<IntervenantDTO> createIntervenant(@RequestBody IntervenantDTO intervenantDTO) throws URISyntaxException {
        log.debug("REST request to save Intervenant : {}", intervenantDTO);
        if (intervenantDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("intervenant", "idexists", "A new intervenant cannot already have an ID")).body(null);
        }
        IntervenantDTO result = intervenantService.save(intervenantDTO);
        return ResponseEntity.created(new URI("/api/intervenants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("intervenant", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /intervenants : Updates an existing intervenant.
     *
     * @param intervenantDTO the intervenantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated intervenantDTO,
     * or with status 400 (Bad Request) if the intervenantDTO is not valid,
     * or with status 500 (Internal Server Error) if the intervenantDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/intervenants")
    @Timed
    public ResponseEntity<IntervenantDTO> updateIntervenant(@RequestBody IntervenantDTO intervenantDTO) throws URISyntaxException {
        log.debug("REST request to update Intervenant : {}", intervenantDTO);
        if (intervenantDTO.getId() == null) {
            return createIntervenant(intervenantDTO);
        }
        IntervenantDTO result = intervenantService.save(intervenantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("intervenant", intervenantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /intervenants : get all the intervenants.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of intervenants in body
     */
    @GetMapping("/intervenants")
    @Timed
    public List<IntervenantDTO> getAllIntervenants() {
        log.debug("REST request to get all Intervenants");
        return intervenantService.findAll();
    }

    /**
     * GET  /intervenants/:id : get the "id" intervenant.
     *
     * @param id the id of the intervenantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the intervenantDTO, or with status 404 (Not Found)
     */
    @GetMapping("/intervenants/{id}")
    @Timed
    public ResponseEntity<IntervenantDTO> getIntervenant(@PathVariable Long id) {
        log.debug("REST request to get Intervenant : {}", id);
        IntervenantDTO intervenantDTO = intervenantService.findOne(id);
        return Optional.ofNullable(intervenantDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /intervenants/:id : delete the "id" intervenant.
     *
     * @param id the id of the intervenantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/intervenants/{id}")
    @Timed
    public ResponseEntity<Void> deleteIntervenant(@PathVariable Long id) {
        log.debug("REST request to delete Intervenant : {}", id);
        intervenantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("intervenant", id.toString())).build();
    }

    /**
     * SEARCH  /_search/intervenants?query=:query : search for the intervenant corresponding
     * to the query.
     *
     * @param query the query of the intervenant search 
     * @return the result of the search
     */
    @GetMapping("/_search/intervenants")
    @Timed
    public List<IntervenantDTO> searchIntervenants(@RequestParam String query) {
        log.debug("REST request to search Intervenants for query {}", query);
        return intervenantService.search(query);
    }


}
