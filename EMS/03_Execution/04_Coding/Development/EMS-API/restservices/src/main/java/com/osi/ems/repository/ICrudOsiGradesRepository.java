package com.osi.ems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiGrades;
import com.osi.ems.repository.custom.ICrudOsiGradesRepositoryCustom;

@Repository
public interface ICrudOsiGradesRepository extends CrudRepository<OsiGrades, Integer>, ICrudOsiGradesRepositoryCustom{
	
	
}
