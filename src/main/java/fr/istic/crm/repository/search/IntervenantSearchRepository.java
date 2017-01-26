package fr.istic.crm.repository.search;

import fr.istic.crm.domain.Intervenant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Intervenant entity.
 */
public interface IntervenantSearchRepository extends ElasticsearchRepository<Intervenant, Long> {
}
