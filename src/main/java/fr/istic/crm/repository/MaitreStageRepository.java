package fr.istic.crm.repository;

import fr.istic.crm.domain.MaitreStage;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MaitreStage entity.
 */
@SuppressWarnings("unused")
public interface MaitreStageRepository extends JpaRepository<MaitreStage,Long> {

}
