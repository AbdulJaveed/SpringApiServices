package com.osi.urm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.urm.domain.OsiRptGroups;
import com.osi.urm.repository.custom.OsiRptGroupsRepositoryCustom;

public interface OsiRptGroupsRepository extends JpaRepository<OsiRptGroups, Integer>, OsiRptGroupsRepositoryCustom{
	 	@Query("SELECT t FROM OsiRptGroups t where t.businessGroupId = :businessGroupId AND t.active=1 ORDER BY t.rptGrpName")
	    public List<OsiRptGroups> findAllActiveRptGroups(@Param("businessGroupId") Integer businessGroupId);
	    @Query("SELECT t FROM OsiRptGroups t where t.businessGroupId = :businessGroupId and t.rptGrpId=:rptGrpId ORDER BY t.rptGrpName")
	    public OsiRptGroups findRptGroup(@Param("businessGroupId")  Integer businessGroupId, @Param("rptGrpId") Integer rptGrpId);
}
