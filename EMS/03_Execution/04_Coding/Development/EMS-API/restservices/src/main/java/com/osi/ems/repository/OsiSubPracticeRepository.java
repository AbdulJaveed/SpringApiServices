package com.osi.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.ems.domain.OsiSubPractice;

public interface OsiSubPracticeRepository extends JpaRepository<OsiSubPractice, Integer> {

	public List<OsiSubPractice> findByActive(Integer active);	
	
	public List<OsiSubPractice> findBySubPracticeShortNameContainingIgnoreCase(String shortName);
	
	public List<OsiSubPractice> findBySubPracticeShortNameContainingIgnoreCaseAndSubPractceLongNameContainingIgnoreCase(String shortName,String longName);
	
	public List<OsiSubPractice> findBySubPractceLongNameContainingIgnoreCase(String longName);
	
}
