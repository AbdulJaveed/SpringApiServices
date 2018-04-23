package com.osi.ems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiCostCenter;
import com.osi.ems.repository.custom.ICrudOsiCostCenterRepositoryCustom;

@Repository
public interface ICrudOsiCostCenterRepository extends CrudRepository<OsiCostCenter, Integer>, ICrudOsiCostCenterRepositoryCustom{
	
}