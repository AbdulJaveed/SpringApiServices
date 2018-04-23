package com.osi.ems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiJobCodes;
import com.osi.ems.repository.custom.ICrudOsiJobCodesRepositoryCustom;

@Repository
public interface ICrudOsiJobCodesRepository extends CrudRepository<OsiJobCodes, Integer>, ICrudOsiJobCodesRepositoryCustom{
	
	
}
