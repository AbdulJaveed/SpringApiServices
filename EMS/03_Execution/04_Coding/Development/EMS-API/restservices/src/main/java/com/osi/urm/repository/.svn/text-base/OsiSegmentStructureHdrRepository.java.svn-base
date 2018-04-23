package com.osi.urm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.urm.domain.OsiKeyFlex;
import com.osi.urm.domain.OsiSegmentStructureHdr;
import com.osi.urm.repository.custom.OsiSegmentStructureHdrRepositoryCustom;

/**
 * Spring Data JPA repository for the OsiSegmentStructureHdr entity.
 */
@SuppressWarnings("unused")
public interface OsiSegmentStructureHdrRepository
		extends JpaRepository<OsiSegmentStructureHdr, Long>, OsiSegmentStructureHdrRepositoryCustom {
	
	@Query("SELECT ossh.segmentStructureHdrId FROM OsiSegmentStructureHdr ossh where ossh.segmentStructureHdrDesc=:segmentStructureHdrDesc")
	public Integer findOneSegmentStructureHdrId(
			@Param("segmentStructureHdrDesc") String segmentStructureHdrDesc);

	/*@Query("SELECT ossh FROM OsiSegmentStructureHdr ossh where ossh.active = 1 AND ossh.businessGroupId = :businessGroupId ORDER BY createdDate asc")
	public List<OsiSegmentStructureHdr> findAllOsiSegmentStructureHdr(
			@Param("businessGroupId") Integer businessGroupId);*/

	@Query("SELECT ossh FROM OsiSegmentStructureHdr ossh,  OsiSegmentStructureDetails ssd where ossh.active=1 AND ossh.segmentStructureHdrId = :segmentStructureHdrId and ossh.segmentStructureHdrId=ssd.osiSegmentStructureHdr.segmentStructureHdrId order by ssd.segmentStructureDetailsSeq")
	public OsiSegmentStructureHdr findOneSegmentStructureHdr(
			@Param("segmentStructureHdrId") Integer segmentStructureHdrId);

	@Query("SELECT ossh.id FROM OsiSegmentStructureHdr ossh where ossh.active = 1 AND UPPER(ossh.segmentStructureHdrDesc)=:segmentStructureHdrDesc")
	public Integer validateUniqueSegmentStructureHdrDesc(@Param("segmentStructureHdrDesc") String segmentStructureHdrDesc);
	
	@Query("SELECT kf FROM OsiKeyFlex kf where kf.value not in( select segmentStructureHdrDesc from OsiSegmentStructureHdr where 1=1 and segmentStructureHdrId!=:segmentStructureHdrId) order by kf.name")
	public List<OsiKeyFlex> getRemainingKeyFlexFields(@Param("segmentStructureHdrId") Integer segmentStructureHdrId);
	
	@Query("SELECT kf FROM OsiKeyFlex kf where kf.businessGroupId =:businessGroupId")
	public OsiKeyFlex getKeyFlexInfo(@Param("businessGroupId") Integer businessGroupId);
	
	@Query("SELECT ossh.segmentStructureHdrId FROM OsiSegmentStructureHdr ossh where ossh.segmentStructureHdrDesc=:segmentStructureHdrDesc and ossh.active =1")
	public Integer findOneActiveSegmentStructureHdrId(
			@Param("segmentStructureHdrDesc") String segmentStructureHdrDesc);
	//public OsiSegmentStructureHdr findBySegmentStructureHdrDesc(Integer segmentStructureHdrId);

}
