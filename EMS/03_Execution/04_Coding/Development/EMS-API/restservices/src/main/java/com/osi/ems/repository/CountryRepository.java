package com.osi.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.ems.domain.OsiCountries;

/**
 * Interface for storing the country details.
 * @author jkolla
 *
 */
public interface CountryRepository extends JpaRepository<OsiCountries, Integer> {
	
	

}
