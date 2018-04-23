package com.osi.urm.reports.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.ems.common.CommonService;
import com.osi.urm.domain.OsiReports;
import com.osi.urm.domain.OsiReportsGeneration;
import com.osi.urm.domain.OsiRmRequests;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.reports.repository.OsiReportsRepository;
import com.osi.urm.reports.service.OsiReportService;
import com.osi.urm.service.dto.OsiReportsDTO;
import com.osi.urm.service.dto.OsiReportsGenerationDTO;
import com.osi.urm.service.dto.OsiRmRequestsDTO;

/**
 * Service Implementation for managing OsiBusinessGroup.
 */
@Service
@Transactional
public class OsiReportServiceImpl implements OsiReportService {

	private final Logger log = LoggerFactory.getLogger(OsiReportServiceImpl.class);
    @Autowired
    private OsiReportsRepository osiReportsRepository;

	@Override
	@Transactional(readOnly = true) 
	public List<OsiReportsDTO> getReportListByRptGrp(Integer businessGroupId, Integer reportGroupId) throws BusinessException {
	    	List<OsiReportsDTO> osiReportsDTOList = null;
	    	try {
				List<OsiReports> osiReportsList  = osiReportsRepository.getReportListByRptGrp(businessGroupId, reportGroupId);
				if(osiReportsList==null || (osiReportsList!=null && osiReportsList.size()==0)){
					throw new DataAccessException("ERR_1002", null);
				}
				osiReportsDTOList = new ArrayList<OsiReportsDTO>();
				for (OsiReports osiReports : osiReportsList) {
					OsiReportsDTO osiReportsDTO = new OsiReportsDTO();
					osiReportsDTO.setReportId(osiReports.getReportId());
					osiReportsDTO.setUserReportName(osiReports.getUserReportName());
					osiReportsDTOList.add(osiReportsDTO);
				}
				
			} catch (DataAccessException e) {
				throw new BusinessException(e.getErrorCode(), e.getMessage()); 
			}catch (Exception e) {
				throw new BusinessException("ERR_1000", e.getMessage());
			}
	    	return osiReportsDTOList;
	}
	
	@Override
	@Transactional(readOnly = true) 
	public List<OsiReportsDTO> getAllReportList(Integer businessGroupId) throws BusinessException {
	    	List<OsiReportsDTO> osiReportsDTOList = null;
	    	try {
				List<OsiReports> osiReportsList  = osiReportsRepository.getAllReports(businessGroupId);
				if(osiReportsList==null || (osiReportsList!=null && osiReportsList.size()==0)){
					throw new DataAccessException("ERR_1002", null);
				}
				osiReportsDTOList = new ArrayList<OsiReportsDTO>();
				for (OsiReports osiReports : osiReportsList) {
					OsiReportsDTO osiReportsDTO = new OsiReportsDTO();
					osiReportsDTO.setReportId(osiReports.getReportId());
					osiReportsDTO.setUserReportName(osiReports.getUserReportName());
					osiReportsDTOList.add(osiReportsDTO);
				}
				
			} catch (DataAccessException e) {
				throw new BusinessException(e.getErrorCode(), e.getMessage()); 
			}catch (Exception e) {
				throw new BusinessException("ERR_1000", e.getMessage());
			}
	    	return osiReportsDTOList;
	}

	public String getReportFields(Integer reportId, Integer businessGroupId, Map<String, String> selectedValues, Map<String, String> osi_envlist) throws BusinessException{
		String string="";
		String multiSelect = "";
		String mandatoryMark="";
		String textFieldType="text";
		String mandatory="";
		String dateFormat = "dd-M-yy";
		String dropDownListType="";
		try {
			List<OsiReportsGeneration> osiReportArgsList = osiReportsRepository.getReportFields(reportId, businessGroupId);
			for (OsiReportsGeneration osiReportsGeneration : osiReportArgsList) {
				textFieldType="text";
				string = string+"<tr>";
				multiSelect = "";
				mandatoryMark="";
				mandatory="";
				dateFormat = "";
				dropDownListType="-1";
				if(osiReportsGeneration.getDateFormat()!=null)
					dateFormat = osiReportsGeneration.getDateFormat();
				if(osiReportsGeneration.getMultSelect().equalsIgnoreCase("Y"))
					multiSelect = "multiple " +"size='5' ";
				if(osiReportsGeneration.getParamRequired().equalsIgnoreCase("Y")){
					mandatoryMark = " checkRequired='required' ";
					mandatory = "<span class='mandatory' style='color: red;'>*</span>";
				}
				if(osiReportsGeneration.getListDataType().equalsIgnoreCase("S") && osiReportsGeneration.getParamRequired().equalsIgnoreCase("N")){
					dropDownListType="-1x";
				}
				if(osiReportsGeneration.getListDataType().equalsIgnoreCase("N"))
					textFieldType = "number";
				if(osiReportsGeneration.getValidationType().equalsIgnoreCase("N")){
						string = string+"<td width='44%' id='"+osiReportsGeneration.getParameterName()+"'><label style='margin-left: 30px;'>"+osiReportsGeneration.getScreenDisplayName()+mandatory+"</label></td><td id='tdId"+osiReportsGeneration.getParameterName()+"'><input type='"+textFieldType+"' "+mandatoryMark+" class='form-control' max='"+osiReportsGeneration.getListDataMaxSize()+"' name='Value_"+osiReportsGeneration.getParameterName()+"' id='Id_"+osiReportsGeneration.getParameterName()+"'></td>";
				}else if(osiReportsGeneration.getValidationType().equalsIgnoreCase("T")){
						string = string+"<td width='44%' id='"+osiReportsGeneration.getParameterName()+"'><label style='margin-left: 30px;'>"+osiReportsGeneration.getScreenDisplayName()+mandatory+"</label></td><td id='tdId"+osiReportsGeneration.getParameterName()+"'><select "+mandatoryMark+" class='form-control' "+multiSelect+" name='Value_"+osiReportsGeneration.getParameterName()+"' id='Id_"+osiReportsGeneration.getParameterName()+"' onchange='getChildList(this);'><option value='"+dropDownListType+"' selected='selected'>Select</option>";
						string = string+getSQLStatement(osiReportsGeneration.getListId(), selectedValues, osi_envlist)+"</td>";
				}else if(osiReportsGeneration.getValidationType().equalsIgnoreCase("D")){
					string = string+"<td width='44%' id='date_"+osiReportsGeneration.getParameterName()+"'><label style='margin-left: 30px;'>"+osiReportsGeneration.getScreenDisplayName()+mandatory+"</label></td><td id='tdId"+osiReportsGeneration.getParameterName()+"'><input type='text' "+mandatoryMark+" readonly='true' class='form-control' name='Value_"+osiReportsGeneration.getParameterName()+"' id='Id_"+osiReportsGeneration.getParameterName()+"' dateFormat='"+dateFormat+"'></td>";
				}
				else{
					string = string+"<td width='44%' id='"+osiReportsGeneration.getParameterName()+"'><label style='margin-left: 30px;'>"+osiReportsGeneration.getScreenDisplayName()+mandatory+"</label></td><td id='tdId"+osiReportsGeneration.getParameterName()+"'><select "+mandatoryMark+" class='form-control' "+multiSelect+" name='Value_"+osiReportsGeneration.getParameterName()+"' id='Id_"+osiReportsGeneration.getParameterName()+"' ><option value='"+dropDownListType+"' selected='selected'>Select</option>";
					string = string+getLOVList(osiReportsGeneration.getListId(), selectedValues);
					string = string+"</td>";
				}
				string = string+"</tr>";
			}
		}catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return string;
	}

	@Override
	public String getSQLStatement(int listId, Map<String, String> selectedValues, Map<String, String> osi_envlist)
			throws BusinessException, DataAccessException {
		return osiReportsRepository.executeSQLStatement(osiReportsRepository.getSQLStatement(listId, selectedValues, osi_envlist));
	}

	@Override
	public String getLOVList(int listId, Map<String, String> selectedValues) throws BusinessException, DataAccessException {
		return osiReportsRepository.getLOVList(listId, selectedValues);
	}

	@Override
	public String getChildFields(String parameter, Map<String, String> selectedValues, Map<String, String> osi_envlist,
			int reportId) throws BusinessException {
		String string="";
		String multiSelect = "";
		String mandatoryMark="";
		String dropDownListType="";
		try {
			List<OsiReportsGeneration> osiReportArgsList = osiReportsRepository.getChildFields(parameter, reportId);
			for (OsiReportsGeneration osiReportsGeneration : osiReportArgsList) {
			//	string = string+"<tr>";
				dropDownListType="-1";
				if(osiReportsGeneration.getMultSelect().equalsIgnoreCase("Y"))
					multiSelect = "multiple " +"size='5' ";
				if(osiReportsGeneration.getParamRequired().equalsIgnoreCase("Y"))
					mandatoryMark = " checkRequired='required' ";
				if(osiReportsGeneration.getListDataType().equalsIgnoreCase("S") && osiReportsGeneration.getParamRequired().equalsIgnoreCase("N")){
					dropDownListType="-1x";
				}
				if(osiReportsGeneration.getValidationType().equalsIgnoreCase("T")){
				string ="<select "+mandatoryMark+" "+multiSelect+" class='form-control' name='Value_"+osiReportsGeneration.getParameterName()+"' id='Id_"+osiReportsGeneration.getParameterName()+"'  onchange='getChildList(this);'><option value='"+dropDownListType+"' selected='selected'>Select</option>";
				string = string+getSQLStatement(osiReportsGeneration.getListId(), selectedValues, osi_envlist)+"</td>";
				
				}else if(osiReportsGeneration.getValidationType().equalsIgnoreCase("L")){
					string = string+"<select "+mandatoryMark+" class='form-control' "+multiSelect+" name='Value_"+osiReportsGeneration.getParameterName()+"' id='Id_"+osiReportsGeneration.getParameterName()+"' ><option value='"+dropDownListType+"' selected='selected'>Select</option>";
					string = string+getLOVList(osiReportsGeneration.getListId(), selectedValues);
					string = string+"</td>";
				}
				//string = string+"</tr>";
			}
		}catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return string;
	}

	@Override
	public String saveReportRequest(int userId, OsiReportsGenerationDTO osiReportsGenerationDTO, int reportId,
			Map<String, Object> map) throws BusinessException {
		String requestId = null;
		try {
			requestId = osiReportsRepository.saveReportRequest(userId, osiReportsGenerationDTO, reportId, map);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return requestId;
	}

	@Override
	public OsiRmRequestsDTO getMoreDetailsForRequest(int requestId) throws BusinessException {
		OsiRmRequestsDTO osiRmRequestsDTO = null;
		try {
			OsiRmRequests osiRmRequests = osiReportsRepository.getMoreDetailsForRequest(requestId);
			osiRmRequestsDTO = new OsiRmRequestsDTO();
			BeanUtils.copyProperties(osiRmRequests, osiRmRequestsDTO);
		}catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiRmRequestsDTO;
	}

	@Override
	public boolean waitForRequest(int requestId) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String saveReportRequest(int userId, int reportId, Map<String, Object> map) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OsiReportsGenerationDTO getOutputFileLocation(int requestId) throws BusinessException {
		String enCodedString = null;
		OsiReportsGenerationDTO osiReportsGenerationDTO = null;
		try {
			CommonService commonService = new CommonService();
			OsiRmRequests  osiRmRequests = osiReportsRepository.getOutputLogFileLocation(requestId);
			if(osiRmRequests!=null && osiRmRequests.getOutputFileName()!=null){
				enCodedString = commonService.encodeFile(osiRmRequests.getOutputFileName());
				osiReportsGenerationDTO = new OsiReportsGenerationDTO();
				osiReportsGenerationDTO.setDownloadFile(enCodedString);
				File file = new File(osiRmRequests.getOutputFileName());
				if(file.exists())
					osiReportsGenerationDTO.setDownloadFileName(file.getName());
			}else{
				throw new BusinessException("ERR_1002", null);
			}
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (BusinessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiReportsGenerationDTO;
	}

	@Override
	public OsiReportsGenerationDTO getLogFileLocation(int requestId) throws BusinessException {
		String enCodedString = null;
		OsiReportsGenerationDTO osiReportsGenerationDTO = null;
		try {
			CommonService commonService = new CommonService();
			OsiRmRequests  osiRmRequests = osiReportsRepository.getOutputLogFileLocation(requestId);
			if(osiRmRequests!=null && osiRmRequests.getLogFileName()!=null){
				enCodedString = commonService.encodeFile(osiRmRequests.getLogFileName());
				osiReportsGenerationDTO = new OsiReportsGenerationDTO();
				osiReportsGenerationDTO.setDownloadFile(enCodedString);
				File file = new File(osiRmRequests.getLogFileName());
				if(file.exists())
					osiReportsGenerationDTO.setDownloadFileName(file.getName());
			}else{
				throw new BusinessException("ERR_1002", null);
			}
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (BusinessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
		return osiReportsGenerationDTO;
	}
	@Override
	public int getReportId(String reportName) throws BusinessException {
		return 0;
	}

	@Override
	public void unscheduleRequest(int userId, int requestId) throws BusinessException {
		try {
			osiReportsRepository.unscheduleRequest(userId, requestId);
		} catch (DataAccessException e) {
			throw new BusinessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
	}
	
	 public static Timestamp getTodayTimestamp() throws Exception{
			java.util.Date date = null;
			java.sql.Timestamp timeStamp = null;
			try {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				java.sql.Date dt = new java.sql.Date(calendar.getTimeInMillis());
				java.sql.Time sqlTime = new java.sql.Time(calendar.getTime()
						.getTime());
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				date = simpleDateFormat.parse(dt.toString() + " "+ sqlTime.toString());
				timeStamp = new java.sql.Timestamp(date.getTime());
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return timeStamp;
		}
	    public static Timestamp getTimestampfromsdate(String sdate) throws Exception{
	    	java.util.Date date = null;
			java.sql.Timestamp timeStamp = null;
	    	try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				date = simpleDateFormat.parse(sdate);
				timeStamp = new java.sql.Timestamp(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				throw e;
			}
	    	return timeStamp;
	    }
	    
	    public static Timestamp getTimestampfromString(String sdate) throws Exception{
	    	java.util.Date date = null;
			java.sql.Timestamp timeStamp = null;
	    	try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
				date = simpleDateFormat.parse(sdate);
				timeStamp = new java.sql.Timestamp(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				throw e;
			}
	    	return timeStamp;
	    }

		@Override
		public List<OsiRmRequestsDTO> getRequestDetails(Integer userId, String adminRole, OsiRmRequestsDTO osiRmRequestsDTO)
				throws BusinessException {
			OsiRmRequests osiRmRequests = null;
			List<OsiRmRequests> osiRmRequestsList = null;
			List<OsiRmRequestsDTO> osiRmRequestsDTOList = null;
			try {
				osiRmRequests = new OsiRmRequests();
				BeanUtils.copyProperties(osiRmRequestsDTO, osiRmRequests);
				osiRmRequestsList = osiReportsRepository.getRequestDetails(userId, adminRole, osiRmRequests);
				osiRmRequestsDTOList = new ArrayList<OsiRmRequestsDTO>();
				for (OsiRmRequests osiRmRequests2 : osiRmRequestsList) {
					OsiRmRequestsDTO osiRmRequestsDTO1 = new OsiRmRequestsDTO();
					BeanUtils.copyProperties(osiRmRequests2, osiRmRequestsDTO1);
					osiRmRequestsDTOList.add(osiRmRequestsDTO1);
				}
			}catch (DataAccessException e) {
				throw new BusinessException(e.getErrorCode(), e.getMessage()); 
			}catch (Exception e) {
				throw new BusinessException("ERR_1000", e.getMessage());
			}
			return osiRmRequestsDTOList;
		}
}
