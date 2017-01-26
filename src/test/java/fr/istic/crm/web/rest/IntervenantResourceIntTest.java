package fr.istic.crm.web.rest;

import fr.istic.crm.CrmisticApp;

import fr.istic.crm.domain.Intervenant;
import fr.istic.crm.repository.IntervenantRepository;
import fr.istic.crm.service.IntervenantService;
import fr.istic.crm.repository.search.IntervenantSearchRepository;
import fr.istic.crm.service.dto.IntervenantDTO;
import fr.istic.crm.service.mapper.IntervenantMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the IntervenantResource REST controller.
 *
 * @see IntervenantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmisticApp.class)
public class IntervenantResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    @Inject
    private IntervenantRepository intervenantRepository;

    @Inject
    private IntervenantMapper intervenantMapper;

    @Inject
    private IntervenantService intervenantService;

    @Inject
    private IntervenantSearchRepository intervenantSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restIntervenantMockMvc;

    private Intervenant intervenant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        IntervenantResource intervenantResource = new IntervenantResource();
        ReflectionTestUtils.setField(intervenantResource, "intervenantService", intervenantService);
        this.restIntervenantMockMvc = MockMvcBuilders.standaloneSetup(intervenantResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Intervenant createEntity(EntityManager em) {
        Intervenant intervenant = new Intervenant()
                .nom(DEFAULT_NOM)
                .prenom(DEFAULT_PRENOM)
                .telephone(DEFAULT_TELEPHONE)
                .mail(DEFAULT_MAIL);
        return intervenant;
    }

    @Before
    public void initTest() {
        intervenantSearchRepository.deleteAll();
        intervenant = createEntity(em);
    }

    @Test
    @Transactional
    public void createIntervenant() throws Exception {
        int databaseSizeBeforeCreate = intervenantRepository.findAll().size();

        // Create the Intervenant
        IntervenantDTO intervenantDTO = intervenantMapper.intervenantToIntervenantDTO(intervenant);

        restIntervenantMockMvc.perform(post("/api/intervenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(intervenantDTO)))
            .andExpect(status().isCreated());

        // Validate the Intervenant in the database
        List<Intervenant> intervenantList = intervenantRepository.findAll();
        assertThat(intervenantList).hasSize(databaseSizeBeforeCreate + 1);
        Intervenant testIntervenant = intervenantList.get(intervenantList.size() - 1);
        assertThat(testIntervenant.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testIntervenant.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testIntervenant.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testIntervenant.getMail()).isEqualTo(DEFAULT_MAIL);

        // Validate the Intervenant in ElasticSearch
        Intervenant intervenantEs = intervenantSearchRepository.findOne(testIntervenant.getId());
        assertThat(intervenantEs).isEqualToComparingFieldByField(testIntervenant);
    }

    @Test
    @Transactional
    public void createIntervenantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = intervenantRepository.findAll().size();

        // Create the Intervenant with an existing ID
        Intervenant existingIntervenant = new Intervenant();
        existingIntervenant.setId(1L);
        IntervenantDTO existingIntervenantDTO = intervenantMapper.intervenantToIntervenantDTO(existingIntervenant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIntervenantMockMvc.perform(post("/api/intervenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingIntervenantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Intervenant> intervenantList = intervenantRepository.findAll();
        assertThat(intervenantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllIntervenants() throws Exception {
        // Initialize the database
        intervenantRepository.saveAndFlush(intervenant);

        // Get all the intervenantList
        restIntervenantMockMvc.perform(get("/api/intervenants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(intervenant.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())));
    }

    @Test
    @Transactional
    public void getIntervenant() throws Exception {
        // Initialize the database
        intervenantRepository.saveAndFlush(intervenant);

        // Get the intervenant
        restIntervenantMockMvc.perform(get("/api/intervenants/{id}", intervenant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(intervenant.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE.toString()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIntervenant() throws Exception {
        // Get the intervenant
        restIntervenantMockMvc.perform(get("/api/intervenants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIntervenant() throws Exception {
        // Initialize the database
        intervenantRepository.saveAndFlush(intervenant);
        intervenantSearchRepository.save(intervenant);
        int databaseSizeBeforeUpdate = intervenantRepository.findAll().size();

        // Update the intervenant
        Intervenant updatedIntervenant = intervenantRepository.findOne(intervenant.getId());
        updatedIntervenant
                .nom(UPDATED_NOM)
                .prenom(UPDATED_PRENOM)
                .telephone(UPDATED_TELEPHONE)
                .mail(UPDATED_MAIL);
        IntervenantDTO intervenantDTO = intervenantMapper.intervenantToIntervenantDTO(updatedIntervenant);

        restIntervenantMockMvc.perform(put("/api/intervenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(intervenantDTO)))
            .andExpect(status().isOk());

        // Validate the Intervenant in the database
        List<Intervenant> intervenantList = intervenantRepository.findAll();
        assertThat(intervenantList).hasSize(databaseSizeBeforeUpdate);
        Intervenant testIntervenant = intervenantList.get(intervenantList.size() - 1);
        assertThat(testIntervenant.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testIntervenant.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testIntervenant.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testIntervenant.getMail()).isEqualTo(UPDATED_MAIL);

        // Validate the Intervenant in ElasticSearch
        Intervenant intervenantEs = intervenantSearchRepository.findOne(testIntervenant.getId());
        assertThat(intervenantEs).isEqualToComparingFieldByField(testIntervenant);
    }

    @Test
    @Transactional
    public void updateNonExistingIntervenant() throws Exception {
        int databaseSizeBeforeUpdate = intervenantRepository.findAll().size();

        // Create the Intervenant
        IntervenantDTO intervenantDTO = intervenantMapper.intervenantToIntervenantDTO(intervenant);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIntervenantMockMvc.perform(put("/api/intervenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(intervenantDTO)))
            .andExpect(status().isCreated());

        // Validate the Intervenant in the database
        List<Intervenant> intervenantList = intervenantRepository.findAll();
        assertThat(intervenantList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteIntervenant() throws Exception {
        // Initialize the database
        intervenantRepository.saveAndFlush(intervenant);
        intervenantSearchRepository.save(intervenant);
        int databaseSizeBeforeDelete = intervenantRepository.findAll().size();

        // Get the intervenant
        restIntervenantMockMvc.perform(delete("/api/intervenants/{id}", intervenant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean intervenantExistsInEs = intervenantSearchRepository.exists(intervenant.getId());
        assertThat(intervenantExistsInEs).isFalse();

        // Validate the database is empty
        List<Intervenant> intervenantList = intervenantRepository.findAll();
        assertThat(intervenantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchIntervenant() throws Exception {
        // Initialize the database
        intervenantRepository.saveAndFlush(intervenant);
        intervenantSearchRepository.save(intervenant);

        // Search the intervenant
        restIntervenantMockMvc.perform(get("/api/_search/intervenants?query=id:" + intervenant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(intervenant.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())));
    }
}
