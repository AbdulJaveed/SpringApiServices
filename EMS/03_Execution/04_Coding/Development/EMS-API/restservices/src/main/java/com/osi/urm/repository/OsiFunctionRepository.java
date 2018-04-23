package com.osi.urm.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.urm.domain.OsiFunctions;
import com.osi.urm.exception.DataAccessException;

/**
 * Spring Data JPA repository for the OsiFunction entity.
 */

public interface OsiFunctionRepository extends JpaRepository<OsiFunctions,Integer>{
	
	List<OsiFunctions> findByBusinessGroupIdAndActiveOrderByUpdatedDateDesc(Integer businessGroupId, Integer active) throws DataAccessException;
	
	List<OsiFunctions> findByBusinessGroupIdOrderByUpdatedDateDesc(Integer businessGroupId,Pageable pageObject) throws DataAccessException;
	
	List<OsiFunctions> findByBusinessGroupIdOrderByUpdatedDateDesc(Integer businessGroupId) throws DataAccessException;

	List<OsiFunctions> findOsiFunctionsByFuncNameContainingIgnoreCaseAndFuncValueContainingIgnoreCaseOrderByUpdatedDateDesc(String funcName,
			String funcValue);
	
	List<OsiFunctions> findOsiFUnctionByFuncNameContainingIgnoreCaseOrderByUpdatedDateDesc(String funcName)throws DataAccessException;
	List<OsiFunctions> findOsiFUnctionByFuncValueContainingIgnoreCaseOrderByUpdatedDateDesc(String funcValue) throws DataAccessException;
	
	@Query(value = "Select * from OSI_FUNCTIONS ofs where ofs.id in (select distinct func_id from osi_menu_entries where menu_id in (select menu_id from osi_menu_resp where resp_id in (?1) UNION select sub_menu_id from osi_menu_entries where menu_id in (select menu_id from osi_menu_resp where resp_id in (?1)) and sub_menu_id is not null) and func_id is not null)", nativeQuery=true)
	List<OsiFunctions> findTotalFuncName(@Param("userRespIds") List<Integer> userRespIds) throws DataAccessException;
	

}
