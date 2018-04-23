package com.osi.ems.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiCcGrades;

@Repository
public interface ICrudOsiCCGradesRepository extends CrudRepository<OsiCcGrades, Integer>/*, ICrudOsiCCGradesHistoryRepositoryCustom */{
	
	List<OsiCcGrades> findByCcId(Integer ccId);
	
	@Modifying
	@Transactional	
	void deleteByCcId(Integer ccId);
	
}
