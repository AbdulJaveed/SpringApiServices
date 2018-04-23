package com.osi.urm.repository.custom;

import java.util.List;

import com.osi.urm.domain.OsiRptGroups;
import com.osi.urm.exception.DataAccessException;

public interface OsiRptGroupsRepositoryCustom {
	public Integer saveOrUpdateRptGroup(OsiRptGroups osiRptGroups) throws DataAccessException;
	public Integer validateRptGroupById(Integer businessGroupId, String reptGrpName, Integer rptGrpId) throws DataAccessException;
    public List<OsiRptGroups> findAllRptGroups(Integer businessGroupId, String rptGrpName) throws DataAccessException;
    public Integer isRptGrpInactivable(Integer id, Integer businessGroupId)  throws DataAccessException;
	public Integer validateRptGroup(Integer businessGroupId, String reptGrpName) throws DataAccessException;
}
