package com.osi.urm.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.osi.urm.domain.OsiMenus;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.custom.OsiMenuRepositoryCustom;

/**
 * Spring Data JPA repository for the OsiMenu entity.
 */
@Repository
public interface OsiMenuRepository extends JpaRepository<OsiMenus,Integer>, OsiMenuRepositoryCustom {
	
	@Query(" FROM OsiMenus WHERE businessGroupId = :businessGroupId and active=1 ORDER BY updatedDate desc")
    List<OsiMenus> findOsiMenusByBusinessGroupId(@Param("businessGroupId") Integer businessGroupId, Pageable pageObject) throws BusinessException;
	
	@Query(" FROM OsiMenus WHERE businessGroupId = :businessGroupId and active=1 ORDER BY updatedDate desc")
    List<OsiMenus> findOsiMenusByBusinessGroupId(@Param("businessGroupId") Integer businessGroupId) throws BusinessException;
	
	List<OsiMenus> findAllOsiMenusByBusinessGroupIdOrderByUpdatedDate(Integer businessGroupId) throws DataAccessException;
	
	/*@Query(" FROM OsiMenus WHERE businessGroupId = :businessGroupId ORDER BY updatedDate desc")
    List<OsiMenus> findAllOsiMenusByBusinessGroupId(@Param("businessGroupId") Integer businessGroupId) throws BusinessException;*/
	
	@Query(" FROM OsiMenus WHERE businessGroupId = :businessGroupId and id =:id ORDER BY updatedDate desc ")
    OsiMenus findOsiMenusByBusinessGroupIdAndId(@Param("id") Integer id, @Param("businessGroupId") Integer businessGroupId) throws BusinessException;

	List<OsiMenus> findOsiMenusByMenuNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByUpdatedDateDesc(String menuName,String menuDescription);

	List<OsiMenus> findOsiMenusByMenuNameContainingIgnoreCaseOrderByUpdatedDateDesc(String menuName);

	List<OsiMenus> findOsiMenusByDescriptionContainingIgnoreCaseOrderByUpdatedDateDesc(String menuDescription);
}
