package fr.istic.crm.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.crm.service.FiliereService;
import fr.istic.crm.web.rest.util.HeaderUtil;
import fr.istic.crm.service.dto.FiliereDTO;

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
 * REST controller for managing Filiere.
 */
@RestController
@RequestMapping("/api")
public class FiliereResource {

    private final Logger log = LoggerFactory.getLogger(FiliereResource.class);
        
    @Inject
    private FiliereService filiereService;

    /**
     * POST  /filieres : Create a new filiere.
     *
     * @param filiereDTO the filiereDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new filiereDTO, or with status 400 (Bad Request) if the filiere has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/filieres")
    @Timed
    public ResponseEntity<FiliereDTO> createFiliere(@Valid @RequestBody FiliereDTO filiereDTO) throws URISyntaxException {
        log.debug("REST request to save Filiere : {}", filiereDTO);
        if (filiereDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("filiere", "idexists", "A new filiere cannot already have an ID")).body(null);
        }
        FiliereDTO result = filiereService.save(filiereDTO);
        return ResponseEntity.created(new URI("/api/filieres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("filiere", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /filieres : Updates an existing filiere.
     *
     * @param filiereDTO the filiereDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated filiereDTO,
     * or with status 400 (Bad Request) if the filiereDTO is not valid,
     * or with status 500 (Internal Server Error) if the filiereDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/filieres")
    @Timed
    public ResponseEntity<FiliereDTO> updateFiliere(@Valid @RequestBody FiliereDTO filiereDTO) throws URISyntaxException {
        log.debug("REST request to update Filiere : {}", filiereDTO);
        if (filiereDTO.getId() == null) {
            return createFiliere(filiereDTO);
        }
        FiliereDTO result = filiereService.save(filiereDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("filiere", filiereDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /filieres : get all the filieres.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of filieres in body
     */
    @GetMapping("/filieres")
    @Timed
    public List<FiliereDTO> getAllFilieres() {
        log.debug("REST request to get all Filieres");
        return filiereService.findAll();
    }

    /**
     * GET  /filieres/:id : get the "id" filiere.
     *
     * @param id the id of the filiereDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the filiereDTO, or with status 404 (Not Found)
     */
    @GetMapping("/filieres/{id}")
    @Timed
    public ResponseEntity<FiliereDTO> getFiliere(@PathVariable Long id) {
        log.debug("REST request to get Filiere : {}", id);
        FiliereDTO filiereDTO = filiereService.findOne(id);
        return Optional.ofNullable(filiereDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /filieres/:id : delete the "id" filiere.
     *
     * @param id the id of the filiereDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/filieres/{id}")
    @Timed
    public ResponseEntity<Void> deleteFiliere(@PathVariable Long id) {
        log.debug("REST request to delete Filiere : {}", id);
        filiereService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("filiere", id.toString())).build();
    }

    /**
     * SEARCH  /_search/filieres?query=:query : search for the filiere corresponding
     * to the query.
     *
     * @param query the query of the filiere search 
     * @return the result of the search
     */
    @GetMapping("/_search/filieres")
    @Timed
    public List<FiliereDTO> searchFilieres(@RequestParam String query) {
        log.debug("REST request to search Filieres for query {}", query);
        return filiereService.search(query);
    }


}
