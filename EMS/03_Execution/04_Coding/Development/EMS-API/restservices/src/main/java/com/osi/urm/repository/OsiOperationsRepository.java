package com.osi.urm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osi.urm.domain.OsiOperations;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiOperationsRepositoryCustom;


/**
 * Spring Data JPA repository for the OsiOperataions entity.
 */
@Repository
public interface OsiOperationsRepository extends JpaRepository<OsiOperations,Integer>, OsiOperationsRepositoryCustom {

	List<OsiOperations> findByBusinessGroupIdAndActive(Integer businessGroupId,	int i) throws DataAccessException;

	


}
