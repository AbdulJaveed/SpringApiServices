package com.osi.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.ems.domain.OsiStates;

/**
 * Iterface for storing the osi states details to database.
 * @author jkolla
 *
 */
public interface OsiStatesRepository extends JpaRepository<OsiStates, Integer> {
	
	/**
	 * Method for getting the countries list from state id.
	 * @param countryId
	 * @return List of states.
	 */
	public List<OsiStates> findByCountryCountryId(Integer countryId);
}
