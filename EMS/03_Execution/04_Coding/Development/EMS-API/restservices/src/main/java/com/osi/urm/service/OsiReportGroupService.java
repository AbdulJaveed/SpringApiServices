package com.osi.urm.service;


import java.util.List;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.service.dto.OsiRptGroupsDTO;

public interface OsiReportGroupService {
	public List<OsiRptGroupsDTO> findAllActiveRptGroups(Integer businessGroupId) throws BusinessException; 
	public List<OsiRptGroupsDTO> findAllRptGroups(Integer businessGroupId, String rptGrpName) throws BusinessException;
	public Integer saveOrUpdate(OsiRptGroupsDTO osiRptGroups, Integer userId, Integer businessGroupId) throws BusinessException;
	public OsiRptGroupsDTO findOne(Integer rptGrpId, Integer businessGroupId) throws BusinessException;
}
