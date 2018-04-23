package com.osi.ems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiSkils;
import com.osi.ems.repository.custom.IListOsiSkilsRepositoryCustom;

@Repository
public interface IListOsiSkilsRepository extends CrudRepository<OsiSkils, Integer>, IListOsiSkilsRepositoryCustom{
	
	
}

