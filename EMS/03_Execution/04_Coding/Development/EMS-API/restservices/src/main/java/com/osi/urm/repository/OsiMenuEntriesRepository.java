package com.osi.urm.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.osi.urm.domain.OsiMenuEntries;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.repository.custom.OsiMenuEntriesRepositoryCustom;

/**
 * Spring Data JPA repository for the OsiMenuEntries entity.
 */
@Repository
public interface OsiMenuEntriesRepository extends JpaRepository<OsiMenuEntries,Integer>, OsiMenuEntriesRepositoryCustom {
	
	
	@Query(" FROM OsiMenuEntries WHERE businessGroupId = :businessGroupId and id =:id ORDER BY updatedDate desc")
	OsiMenuEntries findOsiMenusEntryByBusinessGroupIdAndId(@Param("id") Integer id, @Param("businessGroupId") Integer businessGroupId) throws BusinessException;
	
	
	List<OsiMenuEntries> findByOsiMenusBySubMenuIdMenuNameOrderByUpdatedDateDesc(String menuName);
	
	@Query("FROM OsiMenuEntries me WHERE me.businessGroupId = :businessGroupId AND  me.osiMenusByMenuId IN(select id from OsiMenus) AND (me.osiMenusBySubMenuId IN(select id from OsiMenus) OR me.osiFunctions in(select id from OsiFunctions)) ORDER BY updatedDate desc")
    List<OsiMenuEntries> findOsiMenuEntriesByBusinessGroupId(@Param("businessGroupId") Integer businessGroupId,  Pageable pageObject) throws BusinessException;
	
	@Query("FROM OsiMenuEntries me WHERE me.businessGroupId = :businessGroupId AND  me.osiMenusByMenuId IN(select id from OsiMenus) AND (me.osiMenusBySubMenuId IN(select id from OsiMenus) OR me.osiFunctions in(select id from OsiFunctions)) ORDER BY updatedDate desc")
    List<OsiMenuEntries> findOsiMenuEntriesByBusinessGroupId(@Param("businessGroupId") Integer businessGroupId) throws BusinessException;
}
