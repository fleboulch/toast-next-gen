package fr.istic.crm.repository.search;

import fr.istic.crm.domain.MaitreStage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the MaitreStage entity.
 */
public interface MaitreStageSearchRepository extends ElasticsearchRepository<MaitreStage, Long> {
}
