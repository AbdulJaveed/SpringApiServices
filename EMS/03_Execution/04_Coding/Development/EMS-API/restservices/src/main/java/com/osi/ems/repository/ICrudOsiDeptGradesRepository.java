package com.osi.ems.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiDeptGrades;

@Repository
public interface ICrudOsiDeptGradesRepository extends CrudRepository<OsiDeptGrades, Integer> {
	
	List<OsiDeptGrades> findByDeptId(Integer deptId);
	
	@Modifying
	@Transactional	
	void deleteByDeptId(Integer deptId);
	
}
