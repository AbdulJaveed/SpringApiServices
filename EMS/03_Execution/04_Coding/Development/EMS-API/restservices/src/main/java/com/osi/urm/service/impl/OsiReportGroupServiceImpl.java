package com.osi.urm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.urm.domain.OsiReports;
import com.osi.urm.domain.OsiRptGroups;
import com.osi.urm.domain.OsiRptGrpRpts;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.repository.OsiRptGroupsRepository;
import com.osi.urm.service.OsiReportGroupService;
import com.osi.urm.service.dto.OsiReportsDTO;
import com.osi.urm.service.dto.OsiRptGroupsDTO;
import com.osi.urm.service.dto.OsiRptGrpRptsDTO;

/**
 * Service Implementation for managing OsiReportGroup.
 */
@Service
@Transactional
public class OsiReportGroupServiceImpl implements OsiReportGroupService {

	private final Logger log = LoggerFactory.getLogger(OsiReportGroupServiceImpl.class);
    @Autowired
    private OsiRptGroupsRepository osiRptGroupsRepository;
	@Override
	public List<OsiRptGroupsDTO> findAllActiveRptGroups(Integer businessGroupId) throws BusinessException {
		List<OsiRptGroupsDTO> osiRptGroupsDTOList = null;
		OsiRptGroupsDTO osiRptGroupsDTO = null;
    	try {
			List<OsiRptGroups> osiRptGroupsList  = osiRptGroupsRepository.findAllActiveRptGroups(businessGroupId);
			if(osiRptGroupsList==null || (osiRptGroupsList!=null && osiRptGroupsList.size()==0)){
				throw new DataAccessException("ERR_1002", null);
			}
			osiRptGroupsDTOList = new ArrayList<OsiRptGroupsDTO>();
			for (OsiRptGroups osiRptGroups : osiRptGroupsList) {
				osiRptGroupsDTO = new OsiRptGroupsDTO();
				osiRptGroupsDTO.setRptGrpId(osiRptGroups.getRptGrpId());
				osiRptGroupsDTO.setRptGrpName(osiRptGroups.getRptGrpName());
				osiRptGroupsDTO.setActive(osiRptGroups.getActive());
				osiRptGroupsDTOList.add(osiRptGroupsDTO);
			}
		}  catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
    	return osiRptGroupsDTOList;
	}
	@Override
	public List<OsiRptGroupsDTO> findAllRptGroups(Integer businessGroupId, String rptGrpName) throws BusinessException {
		List<OsiRptGroupsDTO> osiRptGroupsDTOList = null;
		OsiRptGroupsDTO osiRptGroupsDTO = null;
    	try {
			List<OsiRptGroups> osiRptGroupsList  = osiRptGroupsRepository.findAllRptGroups(businessGroupId, rptGrpName);
			if(osiRptGroupsList==null || (osiRptGroupsList!=null && osiRptGroupsList.size()==0)){
				throw new DataAccessException("ERR_1002", null);
			}
			osiRptGroupsDTOList = new ArrayList<OsiRptGroupsDTO>();
			for (OsiRptGroups osiRptGroups : osiRptGroupsList) {
				osiRptGroupsDTO = new OsiRptGroupsDTO();
				osiRptGroupsDTO.setRptGrpId(osiRptGroups.getRptGrpId());
				osiRptGroupsDTO.setRptGrpName(osiRptGroups.getRptGrpName());
				osiRptGroupsDTO.setActive(osiRptGroups.getActive());
				osiRptGroupsDTOList.add(osiRptGroupsDTO);
			}
		}  catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
    	return osiRptGroupsDTOList;
	}
	@Override
	public Integer saveOrUpdate(OsiRptGroupsDTO osiRptGroupsDTO, Integer userId, Integer businessGroupId) throws BusinessException {
		OsiRptGroups osiRptGroups = null;
        Integer count = 0;
        try {
        	osiRptGroupsDTO.setBusinessGroupId(businessGroupId);
        	osiRptGroups = new OsiRptGroups();
			if(osiRptGroupsDTO.getRptGrpId()!=null){
				if(osiRptGroupsRepository.validateRptGroupById(businessGroupId, osiRptGroupsDTO.getRptGrpName(), osiRptGroupsDTO.getRptGrpId())>0){
					throw new BusinessException("ERR_1070", null); 
				}else if(osiRptGroupsDTO.getActive()==0){
					if(osiRptGroupsRepository.isRptGrpInactivable(osiRptGroupsDTO.getRptGrpId(), businessGroupId)>0)
						throw new BusinessException("ERR_1071", null); 
				}
				osiRptGroups.setLastUpdatedBy(userId);
				osiRptGroups.setLastUpdateDate(new Date());
			}else{
				if(osiRptGroupsRepository.validateRptGroup(businessGroupId, osiRptGroupsDTO.getRptGrpName())>0){
					throw new BusinessException("ERR_1070", null); 
				}
				osiRptGroups.setCreatedBy(userId);
				osiRptGroups.setCreationDate(new Date());
			}
			osiRptGroups.setActive(osiRptGroupsDTO.getActive());
			osiRptGroups.setBusinessGroupId(businessGroupId);
			osiRptGroups.setRptGrpId(osiRptGroupsDTO.getRptGrpId());
			osiRptGroups.setRptGrpName(osiRptGroupsDTO.getRptGrpName());
			Set<OsiRptGrpRpts> osiRptGrpRptsSet = new HashSet<OsiRptGrpRpts>();
			for (OsiRptGrpRptsDTO osiRptGrpRptsDTO : osiRptGroupsDTO.getOsiRptGrpRpts()) {
				OsiRptGrpRpts osiRptGrpRpts = new OsiRptGrpRpts();
				OsiReports osiReports = new OsiReports();
				osiReports.setReportId(osiRptGrpRptsDTO.getOsiReports().getReportId());
				osiRptGrpRpts.setOsiReports(osiReports);
				osiRptGrpRpts.setOsiRptGroups(osiRptGroups);
				osiRptGrpRpts.setBusinessGroupId(businessGroupId);
				osiRptGrpRpts.setRptGrpRptsId(osiRptGrpRptsDTO.getRptGrpRptsId());
				osiRptGrpRptsSet.add(osiRptGrpRpts);
			}
			osiRptGroups.setOsiRptGrpRpts(osiRptGrpRptsSet);
			count = osiRptGroupsRepository.saveOrUpdateRptGroup(osiRptGroups);
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
        return count;
	}
	@Override
	public OsiRptGroupsDTO findOne(Integer rptGrpId, Integer businessGroupId) throws BusinessException {
		OsiRptGroupsDTO osiRptGroupsDTO = null;
        try {
        	OsiRptGroups osiRptGroups = osiRptGroupsRepository.findRptGroup(businessGroupId, rptGrpId);
			if(osiRptGroups==null){
				throw new DataAccessException("ERR_1002", null);
			}
			osiRptGroupsDTO = new OsiRptGroupsDTO();
			osiRptGroupsDTO.setActive(osiRptGroups.getActive());
			osiRptGroupsDTO.setBusinessGroupId(osiRptGroups.getBusinessGroupId());
			osiRptGroupsDTO.setRptGrpId(osiRptGroups.getRptGrpId());
			osiRptGroupsDTO.setRptGrpName(osiRptGroups.getRptGrpName());
			Set<OsiRptGrpRptsDTO> osiRptGrpRptsDTOSet = new HashSet<OsiRptGrpRptsDTO>();
			for (OsiRptGrpRpts osiRptGrpRpts : osiRptGroups.getOsiRptGrpRpts()) {
				OsiRptGrpRptsDTO osiRptGrpRptsDTO = new OsiRptGrpRptsDTO();
				OsiReportsDTO osiReportsDTO = new OsiReportsDTO();
				osiReportsDTO.setReportId(osiRptGrpRpts.getOsiReports().getReportId());
				osiReportsDTO.setReportName(osiRptGrpRpts.getOsiReports().getReportName());
				osiReportsDTO.setUserReportName(osiRptGrpRpts.getOsiReports().getUserReportName());
				osiRptGrpRptsDTO.setOsiReports(osiReportsDTO);
				osiRptGrpRptsDTOSet.add(osiRptGrpRptsDTO);
			}
			osiRptGroupsDTO.setOsiRptGrpRpts(osiRptGrpRptsDTOSet);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
        return osiRptGroupsDTO;
	}

	
}
