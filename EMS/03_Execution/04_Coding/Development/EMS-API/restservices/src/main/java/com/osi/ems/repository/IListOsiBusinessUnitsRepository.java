package com.osi.ems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiBusinessUnits;
import com.osi.ems.repository.custom.IListOsiBusinessUnitsRepositoryCustom;

@Repository
public interface IListOsiBusinessUnitsRepository extends CrudRepository<OsiBusinessUnits, Integer>, IListOsiBusinessUnitsRepositoryCustom{
	
	
}

