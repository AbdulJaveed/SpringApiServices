package com.osi.urm.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osi.urm.domain.OsiUserFuncExcl;

/**
 * Spring Data JPA repository for the OsiFunction entity.
 */
@Repository
public interface OsiFunctionExcluRepository extends JpaRepository<OsiUserFuncExcl,Integer> {

	List<OsiUserFuncExcl> findByEmployeeIdAndBusinessGroupId(Integer userId, Integer businessGroupId);

	void removeByEmployeeId(Integer id);

}
