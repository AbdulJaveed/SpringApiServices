package com.osi.ems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiCostCenter;
import com.osi.ems.repository.custom.IListOsiCostCenterRepositoryCustom;

@Repository
public interface IListOsiCostCenterRepository extends CrudRepository<OsiCostCenter, Integer>, IListOsiCostCenterRepositoryCustom{
	
	
}

