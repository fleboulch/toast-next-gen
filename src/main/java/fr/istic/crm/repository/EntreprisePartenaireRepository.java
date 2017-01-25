package fr.istic.crm.repository;

import fr.istic.crm.domain.EntreprisePartenaire;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the EntreprisePartenaire entity.
 */
@SuppressWarnings("unused")
public interface EntreprisePartenaireRepository extends JpaRepository<EntreprisePartenaire,Long> {

    @Query("select distinct entreprisePartenaire from EntreprisePartenaire entreprisePartenaire left join fetch entreprisePartenaire.diplomes")
    List<EntreprisePartenaire> findAllWithEagerRelationships();

    @Query("select entreprisePartenaire from EntreprisePartenaire entreprisePartenaire left join fetch entreprisePartenaire.diplomes where entreprisePartenaire.id =:id")
    EntreprisePartenaire findOneWithEagerRelationships(@Param("id") Long id);

}
