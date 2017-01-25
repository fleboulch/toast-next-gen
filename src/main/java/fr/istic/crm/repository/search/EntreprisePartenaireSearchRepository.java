package fr.istic.crm.repository.search;

import fr.istic.crm.domain.EntreprisePartenaire;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the EntreprisePartenaire entity.
 */
public interface EntreprisePartenaireSearchRepository extends ElasticsearchRepository<EntreprisePartenaire, Long> {
}
