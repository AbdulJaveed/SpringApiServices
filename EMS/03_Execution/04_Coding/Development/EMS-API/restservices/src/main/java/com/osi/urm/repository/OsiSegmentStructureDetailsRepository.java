package com.osi.urm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.urm.domain.OsiSegmentStructureDetails;

public interface OsiSegmentStructureDetailsRepository extends JpaRepository<OsiSegmentStructureDetails, Long> {

	@Query("SELECT ossd FROM OsiSegmentStructureDetails ossd where ossd.segmentStructureDetailsId=:segmentStructureDetailsId")
	public OsiSegmentStructureDetails findOneSegmentStructureDetId(@Param("segmentStructureDetailsId") Integer segmentStructureDetailsId );
}
