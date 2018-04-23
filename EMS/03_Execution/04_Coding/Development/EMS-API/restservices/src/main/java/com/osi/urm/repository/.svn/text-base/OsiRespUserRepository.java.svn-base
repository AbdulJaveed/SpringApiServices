package com.osi.urm.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.osi.urm.domain.OsiRespUser;

/**
 * Spring Data JPA repository for the OsiUser entity.
 */
@RepositoryRestResource
public interface OsiRespUserRepository extends JpaRepository<OsiRespUser,Integer>{
	
	/*@Query(value = "SELECT default_resp FROM osi_resp_user WHERE user_id = ?1", nativeQuery = true)*/
	List<OsiRespUser> findByEmployeeId(Integer employeeId);
	List<OsiRespUser> findByEmployeeIdAndBusinessGroupId(Integer employeeId,Integer businessId);
}
