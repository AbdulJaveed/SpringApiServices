package com.osi.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.ems.domain.OsiSubPracticeGrades;

public interface OsiSubPracticeGradesRepository extends JpaRepository<OsiSubPracticeGrades, Integer> {

	public void deleteBySubPracticeId(Integer subPracticeId);
	
	public List<OsiSubPracticeGrades> findBySubPracticeId(Integer subPracticeId);
	
}
