package fr.istic.crm.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.crm.service.EntreprisePartenaireService;
import fr.istic.crm.web.rest.util.HeaderUtil;
import fr.istic.crm.service.dto.EntreprisePartenaireDTO;

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
 * REST controller for managing EntreprisePartenaire.
 */
@RestController
@RequestMapping("/api")
public class EntreprisePartenaireResource {

    private final Logger log = LoggerFactory.getLogger(EntreprisePartenaireResource.class);
        
    @Inject
    private EntreprisePartenaireService entreprisePartenaireService;

    /**
     * POST  /entreprise-partenaires : Create a new entreprisePartenaire.
     *
     * @param entreprisePartenaireDTO the entreprisePartenaireDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entreprisePartenaireDTO, or with status 400 (Bad Request) if the entreprisePartenaire has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entreprise-partenaires")
    @Timed
    public ResponseEntity<EntreprisePartenaireDTO> createEntreprisePartenaire(@RequestBody EntreprisePartenaireDTO entreprisePartenaireDTO) throws URISyntaxException {
        log.debug("REST request to save EntreprisePartenaire : {}", entreprisePartenaireDTO);
        if (entreprisePartenaireDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("entreprisePartenaire", "idexists", "A new entreprisePartenaire cannot already have an ID")).body(null);
        }
        EntreprisePartenaireDTO result = entreprisePartenaireService.save(entreprisePartenaireDTO);
        return ResponseEntity.created(new URI("/api/entreprise-partenaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("entreprisePartenaire", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entreprise-partenaires : Updates an existing entreprisePartenaire.
     *
     * @param entreprisePartenaireDTO the entreprisePartenaireDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entreprisePartenaireDTO,
     * or with status 400 (Bad Request) if the entreprisePartenaireDTO is not valid,
     * or with status 500 (Internal Server Error) if the entreprisePartenaireDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entreprise-partenaires")
    @Timed
    public ResponseEntity<EntreprisePartenaireDTO> updateEntreprisePartenaire(@RequestBody EntreprisePartenaireDTO entreprisePartenaireDTO) throws URISyntaxException {
        log.debug("REST request to update EntreprisePartenaire : {}", entreprisePartenaireDTO);
        if (entreprisePartenaireDTO.getId() == null) {
            return createEntreprisePartenaire(entreprisePartenaireDTO);
        }
        EntreprisePartenaireDTO result = entreprisePartenaireService.save(entreprisePartenaireDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("entreprisePartenaire", entreprisePartenaireDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entreprise-partenaires : get all the entreprisePartenaires.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of entreprisePartenaires in body
     */
    @GetMapping("/entreprise-partenaires")
    @Timed
    public List<EntreprisePartenaireDTO> getAllEntreprisePartenaires() {
        log.debug("REST request to get all EntreprisePartenaires");
        return entreprisePartenaireService.findAll();
    }

    /**
     * GET  /entreprise-partenaires/:id : get the "id" entreprisePartenaire.
     *
     * @param id the id of the entreprisePartenaireDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entreprisePartenaireDTO, or with status 404 (Not Found)
     */
    @GetMapping("/entreprise-partenaires/{id}")
    @Timed
    public ResponseEntity<EntreprisePartenaireDTO> getEntreprisePartenaire(@PathVariable Long id) {
        log.debug("REST request to get EntreprisePartenaire : {}", id);
        EntreprisePartenaireDTO entreprisePartenaireDTO = entreprisePartenaireService.findOne(id);
        return Optional.ofNullable(entreprisePartenaireDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /entreprise-partenaires/:id : delete the "id" entreprisePartenaire.
     *
     * @param id the id of the entreprisePartenaireDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entreprise-partenaires/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntreprisePartenaire(@PathVariable Long id) {
        log.debug("REST request to delete EntreprisePartenaire : {}", id);
        entreprisePartenaireService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("entreprisePartenaire", id.toString())).build();
    }

    /**
     * SEARCH  /_search/entreprise-partenaires?query=:query : search for the entreprisePartenaire corresponding
     * to the query.
     *
     * @param query the query of the entreprisePartenaire search 
     * @return the result of the search
     */
    @GetMapping("/_search/entreprise-partenaires")
    @Timed
    public List<EntreprisePartenaireDTO> searchEntreprisePartenaires(@RequestParam String query) {
        log.debug("REST request to search EntreprisePartenaires for query {}", query);
        return entreprisePartenaireService.search(query);
    }


}
