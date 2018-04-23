package com.osi.urm.reports.service;


import java.util.List;
import java.util.Map;

import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.service.dto.OsiReportsDTO;
import com.osi.urm.service.dto.OsiReportsGenerationDTO;
import com.osi.urm.service.dto.OsiRmRequestsDTO;

public interface OsiReportService {

	public List<OsiReportsDTO> getReportListByRptGrp(Integer businessGroupId, Integer reportGroupId) throws BusinessException;
	public List<OsiReportsDTO> getAllReportList(Integer businessGroupId) throws BusinessException;
	public String getReportFields(Integer reportId,Integer businessGroupId, Map<String, String> selectedValues, Map<String, String> osi_envlist) throws BusinessException;
	public String getSQLStatement(int listId, Map<String, String> selectedValues, Map<String, String> osi_envlist) throws BusinessException, DataAccessException;
	public String getLOVList(int listId, Map<String, String> selectedValues) throws BusinessException, DataAccessException;
	public String getChildFields(String parameter, Map<String, String> selectedValues, Map<String, String> osi_envlist, int reportId) throws BusinessException;
	public String saveReportRequest(int userId, OsiReportsGenerationDTO osiReportsGenerationVO, int reportId, Map<String, Object> map) throws BusinessException;
	public OsiRmRequestsDTO getMoreDetailsForRequest(int requestId) throws BusinessException;
	public boolean waitForRequest(int requestId) throws BusinessException;
	public String saveReportRequest(int userId, int reportId, Map<String, Object> map) throws BusinessException;
	public OsiReportsGenerationDTO getOutputFileLocation(int requestId) throws BusinessException;
	public OsiReportsGenerationDTO getLogFileLocation(int requestId) throws BusinessException;
	public int getReportId(String reportName) throws BusinessException;
	public void unscheduleRequest(int userId, int requestId) throws BusinessException;
	public List<OsiRmRequestsDTO> getRequestDetails(Integer userId, String adminRole, OsiRmRequestsDTO osiRmRequestsDTO) throws BusinessException; 
}
