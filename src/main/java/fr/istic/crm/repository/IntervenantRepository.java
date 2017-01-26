package fr.istic.crm.repository;

import fr.istic.crm.domain.Intervenant;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Intervenant entity.
 */
@SuppressWarnings("unused")
public interface IntervenantRepository extends JpaRepository<Intervenant,Long> {

    @Query("select distinct intervenant from Intervenant intervenant left join fetch intervenant.diplomes")
    List<Intervenant> findAllWithEagerRelationships();

    @Query("select intervenant from Intervenant intervenant left join fetch intervenant.diplomes where intervenant.id =:id")
    Intervenant findOneWithEagerRelationships(@Param("id") Long id);

}
