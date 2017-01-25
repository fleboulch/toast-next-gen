package fr.istic.crm.web.rest;

import fr.istic.crm.CrmisticApp;

import fr.istic.crm.domain.EntreprisePartenaire;
import fr.istic.crm.repository.EntreprisePartenaireRepository;
import fr.istic.crm.service.EntreprisePartenaireService;
import fr.istic.crm.repository.search.EntreprisePartenaireSearchRepository;
import fr.istic.crm.service.dto.EntreprisePartenaireDTO;
import fr.istic.crm.service.mapper.EntreprisePartenaireMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static fr.istic.crm.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EntreprisePartenaireResource REST controller.
 *
 * @see EntreprisePartenaireResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmisticApp.class)
public class EntreprisePartenaireResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE_DEBUT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_DEBUT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_FIN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_FIN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Inject
    private EntreprisePartenaireRepository entreprisePartenaireRepository;

    @Inject
    private EntreprisePartenaireMapper entreprisePartenaireMapper;

    @Inject
    private EntreprisePartenaireService entreprisePartenaireService;

    @Inject
    private EntreprisePartenaireSearchRepository entreprisePartenaireSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restEntreprisePartenaireMockMvc;

    private EntreprisePartenaire entreprisePartenaire;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EntreprisePartenaireResource entreprisePartenaireResource = new EntreprisePartenaireResource();
        ReflectionTestUtils.setField(entreprisePartenaireResource, "entreprisePartenaireService", entreprisePartenaireService);
        this.restEntreprisePartenaireMockMvc = MockMvcBuilders.standaloneSetup(entreprisePartenaireResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntreprisePartenaire createEntity(EntityManager em) {
        EntreprisePartenaire entreprisePartenaire = new EntreprisePartenaire()
                .dateDebut(DEFAULT_DATE_DEBUT)
                .dateFin(DEFAULT_DATE_FIN);
        return entreprisePartenaire;
    }

    @Before
    public void initTest() {
        entreprisePartenaireSearchRepository.deleteAll();
        entreprisePartenaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntreprisePartenaire() throws Exception {
        int databaseSizeBeforeCreate = entreprisePartenaireRepository.findAll().size();

        // Create the EntreprisePartenaire
        EntreprisePartenaireDTO entreprisePartenaireDTO = entreprisePartenaireMapper.entreprisePartenaireToEntreprisePartenaireDTO(entreprisePartenaire);

        restEntreprisePartenaireMockMvc.perform(post("/api/entreprise-partenaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entreprisePartenaireDTO)))
            .andExpect(status().isCreated());

        // Validate the EntreprisePartenaire in the database
        List<EntreprisePartenaire> entreprisePartenaireList = entreprisePartenaireRepository.findAll();
        assertThat(entreprisePartenaireList).hasSize(databaseSizeBeforeCreate + 1);
        EntreprisePartenaire testEntreprisePartenaire = entreprisePartenaireList.get(entreprisePartenaireList.size() - 1);
        assertThat(testEntreprisePartenaire.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testEntreprisePartenaire.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);

        // Validate the EntreprisePartenaire in ElasticSearch
        EntreprisePartenaire entreprisePartenaireEs = entreprisePartenaireSearchRepository.findOne(testEntreprisePartenaire.getId());
        assertThat(entreprisePartenaireEs).isEqualToComparingFieldByField(testEntreprisePartenaire);
    }

    @Test
    @Transactional
    public void createEntreprisePartenaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entreprisePartenaireRepository.findAll().size();

        // Create the EntreprisePartenaire with an existing ID
        EntreprisePartenaire existingEntreprisePartenaire = new EntreprisePartenaire();
        existingEntreprisePartenaire.setId(1L);
        EntreprisePartenaireDTO existingEntreprisePartenaireDTO = entreprisePartenaireMapper.entreprisePartenaireToEntreprisePartenaireDTO(existingEntreprisePartenaire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntreprisePartenaireMockMvc.perform(post("/api/entreprise-partenaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingEntreprisePartenaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<EntreprisePartenaire> entreprisePartenaireList = entreprisePartenaireRepository.findAll();
        assertThat(entreprisePartenaireList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEntreprisePartenaires() throws Exception {
        // Initialize the database
        entreprisePartenaireRepository.saveAndFlush(entreprisePartenaire);

        // Get all the entreprisePartenaireList
        restEntreprisePartenaireMockMvc.perform(get("/api/entreprise-partenaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entreprisePartenaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(sameInstant(DEFAULT_DATE_DEBUT))))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(sameInstant(DEFAULT_DATE_FIN))));
    }

    @Test
    @Transactional
    public void getEntreprisePartenaire() throws Exception {
        // Initialize the database
        entreprisePartenaireRepository.saveAndFlush(entreprisePartenaire);

        // Get the entreprisePartenaire
        restEntreprisePartenaireMockMvc.perform(get("/api/entreprise-partenaires/{id}", entreprisePartenaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entreprisePartenaire.getId().intValue()))
            .andExpect(jsonPath("$.dateDebut").value(sameInstant(DEFAULT_DATE_DEBUT)))
            .andExpect(jsonPath("$.dateFin").value(sameInstant(DEFAULT_DATE_FIN)));
    }

    @Test
    @Transactional
    public void getNonExistingEntreprisePartenaire() throws Exception {
        // Get the entreprisePartenaire
        restEntreprisePartenaireMockMvc.perform(get("/api/entreprise-partenaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntreprisePartenaire() throws Exception {
        // Initialize the database
        entreprisePartenaireRepository.saveAndFlush(entreprisePartenaire);
        entreprisePartenaireSearchRepository.save(entreprisePartenaire);
        int databaseSizeBeforeUpdate = entreprisePartenaireRepository.findAll().size();

        // Update the entreprisePartenaire
        EntreprisePartenaire updatedEntreprisePartenaire = entreprisePartenaireRepository.findOne(entreprisePartenaire.getId());
        updatedEntreprisePartenaire
                .dateDebut(UPDATED_DATE_DEBUT)
                .dateFin(UPDATED_DATE_FIN);
        EntreprisePartenaireDTO entreprisePartenaireDTO = entreprisePartenaireMapper.entreprisePartenaireToEntreprisePartenaireDTO(updatedEntreprisePartenaire);

        restEntreprisePartenaireMockMvc.perform(put("/api/entreprise-partenaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entreprisePartenaireDTO)))
            .andExpect(status().isOk());

        // Validate the EntreprisePartenaire in the database
        List<EntreprisePartenaire> entreprisePartenaireList = entreprisePartenaireRepository.findAll();
        assertThat(entreprisePartenaireList).hasSize(databaseSizeBeforeUpdate);
        EntreprisePartenaire testEntreprisePartenaire = entreprisePartenaireList.get(entreprisePartenaireList.size() - 1);
        assertThat(testEntreprisePartenaire.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testEntreprisePartenaire.getDateFin()).isEqualTo(UPDATED_DATE_FIN);

        // Validate the EntreprisePartenaire in ElasticSearch
        EntreprisePartenaire entreprisePartenaireEs = entreprisePartenaireSearchRepository.findOne(testEntreprisePartenaire.getId());
        assertThat(entreprisePartenaireEs).isEqualToComparingFieldByField(testEntreprisePartenaire);
    }

    @Test
    @Transactional
    public void updateNonExistingEntreprisePartenaire() throws Exception {
        int databaseSizeBeforeUpdate = entreprisePartenaireRepository.findAll().size();

        // Create the EntreprisePartenaire
        EntreprisePartenaireDTO entreprisePartenaireDTO = entreprisePartenaireMapper.entreprisePartenaireToEntreprisePartenaireDTO(entreprisePartenaire);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEntreprisePartenaireMockMvc.perform(put("/api/entreprise-partenaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entreprisePartenaireDTO)))
            .andExpect(status().isCreated());

        // Validate the EntreprisePartenaire in the database
        List<EntreprisePartenaire> entreprisePartenaireList = entreprisePartenaireRepository.findAll();
        assertThat(entreprisePartenaireList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEntreprisePartenaire() throws Exception {
        // Initialize the database
        entreprisePartenaireRepository.saveAndFlush(entreprisePartenaire);
        entreprisePartenaireSearchRepository.save(entreprisePartenaire);
        int databaseSizeBeforeDelete = entreprisePartenaireRepository.findAll().size();

        // Get the entreprisePartenaire
        restEntreprisePartenaireMockMvc.perform(delete("/api/entreprise-partenaires/{id}", entreprisePartenaire.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean entreprisePartenaireExistsInEs = entreprisePartenaireSearchRepository.exists(entreprisePartenaire.getId());
        assertThat(entreprisePartenaireExistsInEs).isFalse();

        // Validate the database is empty
        List<EntreprisePartenaire> entreprisePartenaireList = entreprisePartenaireRepository.findAll();
        assertThat(entreprisePartenaireList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchEntreprisePartenaire() throws Exception {
        // Initialize the database
        entreprisePartenaireRepository.saveAndFlush(entreprisePartenaire);
        entreprisePartenaireSearchRepository.save(entreprisePartenaire);

        // Search the entreprisePartenaire
        restEntreprisePartenaireMockMvc.perform(get("/api/_search/entreprise-partenaires?query=id:" + entreprisePartenaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entreprisePartenaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(sameInstant(DEFAULT_DATE_DEBUT))))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(sameInstant(DEFAULT_DATE_FIN))));
    }
}
