package com.osi.ems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiGrades;
import com.osi.ems.repository.custom.IListOsiGradesRepositoryCustom;

@Repository
public interface IListOsiGradesRepository extends CrudRepository<OsiGrades, Integer>, IListOsiGradesRepositoryCustom{
	
	
}

