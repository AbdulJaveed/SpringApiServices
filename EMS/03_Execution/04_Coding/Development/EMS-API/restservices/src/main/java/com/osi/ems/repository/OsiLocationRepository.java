package com.osi.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiLocations;

@Repository
public interface OsiLocationRepository extends JpaRepository<OsiLocations,Integer> {
	
}
