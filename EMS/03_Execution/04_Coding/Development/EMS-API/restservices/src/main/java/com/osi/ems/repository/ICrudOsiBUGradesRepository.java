package com.osi.ems.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiBuGrades;

@Repository
public interface ICrudOsiBUGradesRepository extends CrudRepository<OsiBuGrades, Integer> {
	
	List<OsiBuGrades> findByBuId(Integer ccId);
	
	@Modifying
	@Transactional	
	void deleteByBuId(Integer ccId);
	
}
