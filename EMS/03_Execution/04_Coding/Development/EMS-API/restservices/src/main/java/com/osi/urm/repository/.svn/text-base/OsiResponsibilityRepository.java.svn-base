package com.osi.urm.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.osi.urm.domain.OsiResponsibilities;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiResponsibilityRepositoryCustom;

/**
 * Spring Data JPA repository for the OsiResponsibility entity.
 */
@Repository
public interface OsiResponsibilityRepository extends JpaRepository<OsiResponsibilities,Integer>, OsiResponsibilityRepositoryCustom {

	@Query(" FROM OsiResponsibilities WHERE businessGroupId = :businessGroupId and active=1 ORDER BY updatedDate desc")
    List<OsiResponsibilities> findOsiResponsibilitiesBybusinessGroupId( @Param("businessGroupId") Integer businessGroupId) throws DataAccessException;

	//@Query(" FROM OsiResponsibilities WHERE businessGroupId = :businessGroupId and active=1 ORDER BY updatedDate desc")
    List<OsiResponsibilities> findOsiResponsibilitiesBybusinessGroupIdOrderByUpdatedDateDesc(Integer businessGroupId) throws DataAccessException;
    
    @Query(" FROM OsiResponsibilities WHERE businessGroupId = :businessGroupId and active=1 ORDER BY updatedDate desc")
    List<OsiResponsibilities> findOsiResponsibilitiesBybusinessGroupId( @Param("businessGroupId") Integer businessGroupId, Pageable pageObject) throws DataAccessException;
	
	@Query(" FROM OsiResponsibilities WHERE businessGroupId = :businessGroupId and startDate<= :currentDate and endDate>= :currentDate ORDER BY updatedDate desc")
    List<OsiResponsibilities> findOsiResponsibilitiesBybusinessGroupIdList( @Param("businessGroupId") Integer businessGroupId, @Param("currentDate") Date currentDate) throws DataAccessException;

	List<OsiResponsibilities> findOsiResponsibilitiesByRespNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndActiveOrderByUpdatedDateDesc(String respName, String respDescription, Integer active) throws BusinessException;

	List<OsiResponsibilities> findOsiResponsibilitiesByRespNameContainingIgnoreCaseAndActiveOrderByUpdatedDateDesc(String respName, Integer active)throws BusinessException;

	List<OsiResponsibilities> findOsiResponsibilitiesByDescriptionContainingIgnoreCaseAndActiveOrderByUpdatedDateDesc(String respDescription, Integer active) throws BusinessException;
}
