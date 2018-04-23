package com.osi.urm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osi.urm.domain.OsiUserOperationExcl;
@Repository
public interface OsiUserOperationExclRepository extends JpaRepository<OsiUserOperationExcl, Integer> {

	List<OsiUserOperationExcl> findByOsiFunctionsId(Integer id);
	
	List<OsiUserOperationExcl> findByOsiFunctionsIdAndEmployeeId(Integer funcId, Integer userId);
	
	List<OsiUserOperationExcl> findByEmployeeId(Integer id);
	void removeByEmployeeId(int employeeId);
}
