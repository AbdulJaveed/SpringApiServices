package com.osi.urm.reports.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.reports.service.OsiReportService;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.dto.OsiReportsDTO;
import com.osi.urm.service.dto.OsiReportsGenerationDTO;
import com.osi.urm.service.dto.OsiRmRequestsDTO;
import com.osi.urm.web.rest.util.SuccessResponse;

@RestController
@RequestMapping("/api")
public class OsiReportResource {
	private final Logger log = LoggerFactory.getLogger(OsiReportResource.class);

	@Autowired
	private Environment env;
	
	@Value("${spring.ENV_LIST}")
	String envList;
	
	@Value("${spring.adminUser}")
	String userName;
	
    @Autowired
    private OsiReportService osiReportService;
    
    @Autowired
	private AuthTokenStore authTokenStore;
	
    @GetMapping("/all-reports-by-grpt-id/{id}")
    public ResponseEntity<List<OsiReportsDTO>> getReportsByGrpId(@PathVariable Integer id, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException{
    List<OsiReportsDTO> osiReportsDTOList = null;
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		osiReportsDTOList = osiReportService.getReportListByRptGrp(auth.getOsiUserDTO().getBusinessGroupId(), id);
    	}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return ResponseEntity.ok().body(osiReportsDTOList);
    }
    
    @GetMapping("/all-reports")
    public ResponseEntity<List<OsiReportsDTO>> getAllReports(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException{
    List<OsiReportsDTO> osiReportsDTOList = null;
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		osiReportsDTOList = osiReportService.getAllReportList(auth.getOsiUserDTO().getBusinessGroupId());
    	}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return ResponseEntity.ok().body(osiReportsDTOList);
    }
    
    @PostMapping("/screen-fields")
    public ResponseEntity<OsiReportsGenerationDTO> getScreenFields(@Valid @RequestBody OsiReportsDTO osiReportsDTO, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken)
            throws RestServiceException {
    	 String screenFields = null;
    	 Map<String, String> selectedValues = null;
    	 Map<String, String> envMap = null;
    	 OsiReportsGenerationDTO osiReportsGenerationDTO = null;
			try {
				AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
				envMap = new HashMap<String, String>();
				if (null!=envMap) {
					String envArray[] = envList.split(",");
					for (String envva : envArray) {
						String envStr = (String)envva;
						String envValue = ""+auth.getOsiUserDTO().getId();
						if(envValue!=null)
							envMap.put(envStr, envValue);
					}
				 }
				if(osiReportsDTO.getSelectedValues()!=null){
					String selectedValuess[] = osiReportsDTO.getSelectedValues().split(";");
					selectedValues = new HashMap<String, String>();
					for (String string : selectedValuess) {
						String keyValue[] = string.split(":");
						selectedValues.put(keyValue[0], keyValue[1]);
					}
				}
				osiReportsGenerationDTO = new OsiReportsGenerationDTO();
	    		List<String> hrsList = new ArrayList<String>();
	    		List<String> minsList = new ArrayList<String>();
				 for(int i=0;i<24;i++){
					 if(i<10)
						 hrsList.add("0"+i);
					 else
						 hrsList.add(""+i);
				 }
				 for(int i=0;i<60;i++){
					 if(i<10)
						 minsList.add("0"+i);
					 else
						 minsList.add(""+i);
				 }
				 osiReportsGenerationDTO.setHrsList(hrsList);
				 osiReportsGenerationDTO.setMinsList(minsList);
				 if(Integer.parseInt(new SimpleDateFormat("H").format(new Date()))<10)
					 osiReportsGenerationDTO.setRequestTimeHr("0"+new SimpleDateFormat("H").format(new Date()));
				 else
					 osiReportsGenerationDTO.setRequestTimeHr(new SimpleDateFormat("H").format(new Date()));
				 if(Integer.parseInt(new SimpleDateFormat("m").format(new Date()))<10)
					 osiReportsGenerationDTO.setRequestTimeMins("0"+new SimpleDateFormat("m").format(new Date()));
				 else
					 osiReportsGenerationDTO.setRequestTimeMins(new SimpleDateFormat("m").format(new Date()));
				 screenFields = osiReportService.getReportFields(osiReportsDTO.getReportId(),auth.getOsiUserDTO().getBusinessGroupId(), selectedValues, envMap);
				 if(screenFields==null ||  screenFields.isEmpty()){
					 
				 }
				 osiReportsGenerationDTO.setScreenFields(screenFields);
				 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				 osiReportsGenerationDTO.setRequestDate(simpleDateFormat.format(new Date()));
			} catch (BusinessException e) {
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
			}
    	return ResponseEntity.ok().body(osiReportsGenerationDTO);
    }
    @PostMapping("/generate-report")
    public  String generateReport(@Valid @RequestBody OsiReportsGenerationDTO osiReportsGenerationDTO, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken)
            throws RestServiceException {
    		String htmlString = null;
    		Map<String, String> envMap = null;
			String fields[] = null;
			String fieldIds[] = null;
			try {
				AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
				Map<String, Object> parameters = new HashMap<String, Object>();
				osiReportsGenerationDTO.setBusinessGroupId(auth.getOsiUserDTO().getBusinessGroupId());
				parameters.put("RUN_BY", auth.getOsiUserDTO().getFirstName()+" "+ auth.getOsiUserDTO().getLastName());
				envMap = new HashMap<String, String>();
				if (null!=envMap) {
					String envArray[] = envList.split(",");
					for (String envva : envArray) {
						String envStr = (String)envva;
						String envValue = ""+auth.getOsiUserDTO().getId();
						parameters.put(envStr, envValue);
					}
				 }
				String finalInputParameters = osiReportsGenerationDTO.getFinalParameters().trim();
				if(finalInputParameters!=null && !finalInputParameters.equals("")){
					fields = finalInputParameters.split("##");
					for (int i = 0; i < fields.length; i++) {
						fieldIds = fields[i].split("::");
						if(fieldIds.length==2){
							if(!fieldIds[1].contains(",")){
							try{
								  int num = Integer.parseInt(fieldIds[1]);
								} catch (NumberFormatException e) {
									fieldIds[1] = "'"+fieldIds[1]+"'";
								}
							}
							System.out.println("id::"+fieldIds[0]+"::Value::"+fieldIds[1]);
							parameters.put(fieldIds[0], fieldIds[1]);
						}
					}
				}
				String requestId = osiReportService.saveReportRequest(auth.getOsiUserDTO().getId(), osiReportsGenerationDTO, osiReportsGenerationDTO.getReportId(), parameters);
				htmlString  = "Your request submitted successfully... Your request Id is: "+requestId; 
			} catch (BusinessException e) {
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
			}
    	return htmlString;
    }
    @PostMapping("/child-fields")
    public String getChildFields(@Valid @RequestBody OsiReportsDTO osiReportsDTO, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken)
            throws RestServiceException {
    	String htmlString = null;
	   	 Map<String, String> selectedValues = null;
	   	 Map<String, String> envMap = null;
			try {
				AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
				envMap = new HashMap<String, String>();
				if (null!=envMap) {
					String envArray[] = envList.split(",");
					for (String envva : envArray) {
						String envStr = (String)envva;
						String envValue = ""+auth.getOsiUserDTO().getId();
						if(envValue!=null)
							envMap.put(envStr, envValue);
					}
				 }
				if(osiReportsDTO.getSelectedValues()!=null){
					String selectedValuess[] = osiReportsDTO.getSelectedValues().split(";");
					selectedValues = new HashMap<String, String>();
					for (String string : selectedValuess) {
						String keyValue[] = string.split(":");
						selectedValues.put(keyValue[0], keyValue[1]);
					}
				}
				htmlString = osiReportService.getChildFields(osiReportsDTO.getChildParameter(), selectedValues, envMap , osiReportsDTO.getReportId());
			}  catch (BusinessException e) {
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
			}
   	return htmlString;
    }
    @PostMapping("/rm-hostory")
    public ResponseEntity<List<OsiRmRequestsDTO>> osirmHistory(@Valid @RequestBody OsiRmRequestsDTO osiRmRequestsDTO, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken)
            throws RestServiceException {
    	List<OsiRmRequestsDTO> osiRmRequestsDTOList  = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiRmRequestsDTOList = osiReportService.getRequestDetails(auth.getOsiUserDTO().getId(), userName, osiRmRequestsDTO);
		}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return ResponseEntity.ok().body(osiRmRequestsDTOList);
    }
    @GetMapping("/download-log-file/{id}")
    public ResponseEntity<OsiReportsGenerationDTO> downloadLogFile(@PathVariable Integer id, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException{
    	OsiReportsGenerationDTO osiReportsGenerationDTO = null;
    	try {
    		osiReportsGenerationDTO = osiReportService.getLogFileLocation(id);
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return ResponseEntity.ok().body(osiReportsGenerationDTO);
    }
    @GetMapping("/download-out-file/{id}")
    public ResponseEntity<OsiReportsGenerationDTO> downloadOutFile(@PathVariable Integer id, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException{
    	OsiReportsGenerationDTO osiReportsGenerationDTO = null;
    	try {
    		osiReportsGenerationDTO = osiReportService.getOutputFileLocation(id);
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return ResponseEntity.ok().body(osiReportsGenerationDTO);
    }
    @GetMapping("/request-more-details/{id}")
    public ResponseEntity<OsiRmRequestsDTO> requestMoreDetails(@PathVariable Integer id, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException{
    	OsiRmRequestsDTO osiRmRequestsDTO = null;
		try {
			osiRmRequestsDTO = osiReportService.getMoreDetailsForRequest(id);
		}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return ResponseEntity.ok().body(osiRmRequestsDTO);
    }
    @GetMapping("/unschedule/{id}")
    public ResponseEntity<SuccessResponse> unschedule(@PathVariable Integer id, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException{
    	SuccessResponse successResponse = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiReportService.unscheduleRequest(auth.getOsiUserDTO().getId(), id);
			successResponse = new SuccessResponse();
			successResponse.setHttpStatus(HttpStatus.OK.value());
			successResponse.setMessage(env.getProperty("MSG_1100"));
		}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return ResponseEntity.ok().body(successResponse);
    }
    @ExceptionHandler(RestServiceException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(RestServiceException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getErrorCode());
		error.setHttpStatus(ex.getHttpStatus());
		error.setErrorMessage(ex.getErrorMessage());
		error.setDeveloperMessage(ex.getDeveloperMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}


}
