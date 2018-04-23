package com.osi.ems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiDepartment;
import com.osi.ems.repository.custom.IListOsiDepratmentRepositoryCustom;

@Repository
public interface IListOsiDepratmentRepository extends CrudRepository<OsiDepartment, Integer>, IListOsiDepratmentRepositoryCustom{
	
	
}

