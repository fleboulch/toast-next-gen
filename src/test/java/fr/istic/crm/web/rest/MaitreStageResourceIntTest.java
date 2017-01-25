package fr.istic.crm.web.rest;

import fr.istic.crm.CrmisticApp;

import fr.istic.crm.domain.MaitreStage;
import fr.istic.crm.repository.MaitreStageRepository;
import fr.istic.crm.service.MaitreStageService;
import fr.istic.crm.repository.search.MaitreStageSearchRepository;
import fr.istic.crm.service.dto.MaitreStageDTO;
import fr.istic.crm.service.mapper.MaitreStageMapper;

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
 * Test class for the MaitreStageResource REST controller.
 *
 * @see MaitreStageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmisticApp.class)
public class MaitreStageResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FONCTION = "AAAAAAAAAA";
    private static final String UPDATED_FONCTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ANCIEN_ETUDIANT = false;
    private static final Boolean UPDATED_ANCIEN_ETUDIANT = true;

    private static final Long DEFAULT_DEBUT_VERSION = 1L;
    private static final Long UPDATED_DEBUT_VERSION = 2L;

    private static final Long DEFAULT_FIN_VERSION = 1L;
    private static final Long UPDATED_FIN_VERSION = 2L;

    @Inject
    private MaitreStageRepository maitreStageRepository;

    @Inject
    private MaitreStageMapper maitreStageMapper;

    @Inject
    private MaitreStageService maitreStageService;

    @Inject
    private MaitreStageSearchRepository maitreStageSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restMaitreStageMockMvc;

    private MaitreStage maitreStage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MaitreStageResource maitreStageResource = new MaitreStageResource();
        ReflectionTestUtils.setField(maitreStageResource, "maitreStageService", maitreStageService);
        this.restMaitreStageMockMvc = MockMvcBuilders.standaloneSetup(maitreStageResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MaitreStage createEntity(EntityManager em) {
        MaitreStage maitreStage = new MaitreStage()
                .nom(DEFAULT_NOM)
                .prenom(DEFAULT_PRENOM)
                .telephone(DEFAULT_TELEPHONE)
                .mail(DEFAULT_MAIL)
                .fonction(DEFAULT_FONCTION)
                .ancienEtudiant(DEFAULT_ANCIEN_ETUDIANT)
                .debutVersion(DEFAULT_DEBUT_VERSION)
                .finVersion(DEFAULT_FIN_VERSION);
        return maitreStage;
    }

    @Before
    public void initTest() {
        maitreStageSearchRepository.deleteAll();
        maitreStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMaitreStage() throws Exception {
        int databaseSizeBeforeCreate = maitreStageRepository.findAll().size();

        // Create the MaitreStage
        MaitreStageDTO maitreStageDTO = maitreStageMapper.maitreStageToMaitreStageDTO(maitreStage);

        restMaitreStageMockMvc.perform(post("/api/maitre-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maitreStageDTO)))
            .andExpect(status().isCreated());

        // Validate the MaitreStage in the database
        List<MaitreStage> maitreStageList = maitreStageRepository.findAll();
        assertThat(maitreStageList).hasSize(databaseSizeBeforeCreate + 1);
        MaitreStage testMaitreStage = maitreStageList.get(maitreStageList.size() - 1);
        assertThat(testMaitreStage.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testMaitreStage.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testMaitreStage.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testMaitreStage.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testMaitreStage.getFonction()).isEqualTo(DEFAULT_FONCTION);
        assertThat(testMaitreStage.isAncienEtudiant()).isEqualTo(DEFAULT_ANCIEN_ETUDIANT);
        assertThat(testMaitreStage.getDebutVersion()).isEqualTo(DEFAULT_DEBUT_VERSION);
        assertThat(testMaitreStage.getFinVersion()).isEqualTo(DEFAULT_FIN_VERSION);

        // Validate the MaitreStage in ElasticSearch
        MaitreStage maitreStageEs = maitreStageSearchRepository.findOne(testMaitreStage.getId());
        assertThat(maitreStageEs).isEqualToComparingFieldByField(testMaitreStage);
    }

    @Test
    @Transactional
    public void createMaitreStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = maitreStageRepository.findAll().size();

        // Create the MaitreStage with an existing ID
        MaitreStage existingMaitreStage = new MaitreStage();
        existingMaitreStage.setId(1L);
        MaitreStageDTO existingMaitreStageDTO = maitreStageMapper.maitreStageToMaitreStageDTO(existingMaitreStage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaitreStageMockMvc.perform(post("/api/maitre-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingMaitreStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<MaitreStage> maitreStageList = maitreStageRepository.findAll();
        assertThat(maitreStageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMaitreStages() throws Exception {
        // Initialize the database
        maitreStageRepository.saveAndFlush(maitreStage);

        // Get all the maitreStageList
        restMaitreStageMockMvc.perform(get("/api/maitre-stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(maitreStage.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())))
            .andExpect(jsonPath("$.[*].fonction").value(hasItem(DEFAULT_FONCTION.toString())))
            .andExpect(jsonPath("$.[*].ancienEtudiant").value(hasItem(DEFAULT_ANCIEN_ETUDIANT.booleanValue())))
            .andExpect(jsonPath("$.[*].debutVersion").value(hasItem(DEFAULT_DEBUT_VERSION.intValue())))
            .andExpect(jsonPath("$.[*].finVersion").value(hasItem(DEFAULT_FIN_VERSION.intValue())));
    }

    @Test
    @Transactional
    public void getMaitreStage() throws Exception {
        // Initialize the database
        maitreStageRepository.saveAndFlush(maitreStage);

        // Get the maitreStage
        restMaitreStageMockMvc.perform(get("/api/maitre-stages/{id}", maitreStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(maitreStage.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE.toString()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL.toString()))
            .andExpect(jsonPath("$.fonction").value(DEFAULT_FONCTION.toString()))
            .andExpect(jsonPath("$.ancienEtudiant").value(DEFAULT_ANCIEN_ETUDIANT.booleanValue()))
            .andExpect(jsonPath("$.debutVersion").value(DEFAULT_DEBUT_VERSION.intValue()))
            .andExpect(jsonPath("$.finVersion").value(DEFAULT_FIN_VERSION.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMaitreStage() throws Exception {
        // Get the maitreStage
        restMaitreStageMockMvc.perform(get("/api/maitre-stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaitreStage() throws Exception {
        // Initialize the database
        maitreStageRepository.saveAndFlush(maitreStage);
        maitreStageSearchRepository.save(maitreStage);
        int databaseSizeBeforeUpdate = maitreStageRepository.findAll().size();

        // Update the maitreStage
        MaitreStage updatedMaitreStage = maitreStageRepository.findOne(maitreStage.getId());
        updatedMaitreStage
                .nom(UPDATED_NOM)
                .prenom(UPDATED_PRENOM)
                .telephone(UPDATED_TELEPHONE)
                .mail(UPDATED_MAIL)
                .fonction(UPDATED_FONCTION)
                .ancienEtudiant(UPDATED_ANCIEN_ETUDIANT)
                .debutVersion(UPDATED_DEBUT_VERSION)
                .finVersion(UPDATED_FIN_VERSION);
        MaitreStageDTO maitreStageDTO = maitreStageMapper.maitreStageToMaitreStageDTO(updatedMaitreStage);

        restMaitreStageMockMvc.perform(put("/api/maitre-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maitreStageDTO)))
            .andExpect(status().isOk());

        // Validate the MaitreStage in the database
        List<MaitreStage> maitreStageList = maitreStageRepository.findAll();
        assertThat(maitreStageList).hasSize(databaseSizeBeforeUpdate);
        MaitreStage testMaitreStage = maitreStageList.get(maitreStageList.size() - 1);
        assertThat(testMaitreStage.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testMaitreStage.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testMaitreStage.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testMaitreStage.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testMaitreStage.getFonction()).isEqualTo(UPDATED_FONCTION);
        assertThat(testMaitreStage.isAncienEtudiant()).isEqualTo(UPDATED_ANCIEN_ETUDIANT);
        assertThat(testMaitreStage.getDebutVersion()).isEqualTo(UPDATED_DEBUT_VERSION);
        assertThat(testMaitreStage.getFinVersion()).isEqualTo(UPDATED_FIN_VERSION);

        // Validate the MaitreStage in ElasticSearch
        MaitreStage maitreStageEs = maitreStageSearchRepository.findOne(testMaitreStage.getId());
        assertThat(maitreStageEs).isEqualToComparingFieldByField(testMaitreStage);
    }

    @Test
    @Transactional
    public void updateNonExistingMaitreStage() throws Exception {
        int databaseSizeBeforeUpdate = maitreStageRepository.findAll().size();

        // Create the MaitreStage
        MaitreStageDTO maitreStageDTO = maitreStageMapper.maitreStageToMaitreStageDTO(maitreStage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMaitreStageMockMvc.perform(put("/api/maitre-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maitreStageDTO)))
            .andExpect(status().isCreated());

        // Validate the MaitreStage in the database
        List<MaitreStage> maitreStageList = maitreStageRepository.findAll();
        assertThat(maitreStageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMaitreStage() throws Exception {
        // Initialize the database
        maitreStageRepository.saveAndFlush(maitreStage);
        maitreStageSearchRepository.save(maitreStage);
        int databaseSizeBeforeDelete = maitreStageRepository.findAll().size();

        // Get the maitreStage
        restMaitreStageMockMvc.perform(delete("/api/maitre-stages/{id}", maitreStage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean maitreStageExistsInEs = maitreStageSearchRepository.exists(maitreStage.getId());
        assertThat(maitreStageExistsInEs).isFalse();

        // Validate the database is empty
        List<MaitreStage> maitreStageList = maitreStageRepository.findAll();
        assertThat(maitreStageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchMaitreStage() throws Exception {
        // Initialize the database
        maitreStageRepository.saveAndFlush(maitreStage);
        maitreStageSearchRepository.save(maitreStage);

        // Search the maitreStage
        restMaitreStageMockMvc.perform(get("/api/_search/maitre-stages?query=id:" + maitreStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(maitreStage.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())))
            .andExpect(jsonPath("$.[*].fonction").value(hasItem(DEFAULT_FONCTION.toString())))
            .andExpect(jsonPath("$.[*].ancienEtudiant").value(hasItem(DEFAULT_ANCIEN_ETUDIANT.booleanValue())))
            .andExpect(jsonPath("$.[*].debutVersion").value(hasItem(DEFAULT_DEBUT_VERSION.intValue())))
            .andExpect(jsonPath("$.[*].finVersion").value(hasItem(DEFAULT_FIN_VERSION.intValue())));
    }
}
