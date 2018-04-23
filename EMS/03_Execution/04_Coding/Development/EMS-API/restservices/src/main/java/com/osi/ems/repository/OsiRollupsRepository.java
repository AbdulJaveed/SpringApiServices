package com.osi.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osi.ems.domain.OsiRollUps;

@Repository
public interface OsiRollupsRepository extends JpaRepository<OsiRollUps, Integer>{
	
	OsiRollUps findBySegment1AndSegment2AndSegment3AndSegment4AndSegment5AndSegment6AndSegment7AndSegment8AndSegment9AndSegment10AndOrgId(String segment1, 
			String segment2, String segment3, String segment4, String segment5, String segment6, String segmen7, String segment8, String segment9, String segment10, Integer orgId);
}
