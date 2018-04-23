package com.osi.urm.reports.repository.custom;

import java.util.List;
import java.util.Map;

import com.osi.urm.domain.OsiReports;
import com.osi.urm.domain.OsiReportsGeneration;
import com.osi.urm.domain.OsiRmRequests;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.service.dto.OsiReportsGenerationDTO;

public interface OsiReportsRepositoryCustom {
    List<OsiReports> getReportListByRptGrp(Integer businessGroupId, Integer rptGrpId) throws DataAccessException;
	public List<OsiReports> getAllReports(Integer businessGroupId) throws DataAccessException;
	public List<OsiReportsGeneration> getReportFields(int reportId, Integer businessGroupId) throws DataAccessException;
	public String getSQLStatement(int listId, Map<String, String> selectedValues, Map<String, String> osi_envlist) throws DataAccessException;
	public String getLOVList(int listId, Map<String, String> selectedValues) throws DataAccessException;
	public List<OsiReportsGeneration> getChildFields(String parameter, int reportId) throws DataAccessException;
	public String executeSQLStatement(String sqlStatement) throws DataAccessException;
	public String saveReportRequest(int userId, OsiReportsGenerationDTO osiReportsGenerationVO, int reportId, Map<String, Object> map) throws DataAccessException;
	public OsiRmRequests getMoreDetailsForRequest(int requestId) throws DataAccessException;
	public List<OsiRmRequests> getRequestDetails(Integer userId, String adminRole, OsiRmRequests osiRmRequests) throws DataAccessException;
	public boolean waitForRequest(int requestId) throws DataAccessException;
	public String saveReportRequest(int userId, int reportId, Map<String, Object> map) throws DataAccessException;
	public OsiRmRequests getOutputLogFileLocation(int requestId) throws DataAccessException;
	public int getReportId(String reportName) throws DataAccessException;
	public void unscheduleRequest(int userId, int requestId) throws DataAccessException;
	public String getReportOutputType(int reportId) throws DataAccessException;
}
