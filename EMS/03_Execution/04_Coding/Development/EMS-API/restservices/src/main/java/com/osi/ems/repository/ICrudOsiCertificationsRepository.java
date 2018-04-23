package com.osi.ems.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiCertifications;
import com.osi.ems.repository.custom.ICrudOsiCertificationsRepositoryCustom;

@Repository
public interface ICrudOsiCertificationsRepository
		extends CrudRepository<OsiCertifications, Integer>, ICrudOsiCertificationsRepositoryCustom {

	public List<OsiCertifications> findByActive(Integer active);
	
}
