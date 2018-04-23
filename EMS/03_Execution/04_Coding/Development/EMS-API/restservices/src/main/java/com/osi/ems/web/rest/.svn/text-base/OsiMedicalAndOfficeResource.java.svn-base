package com.osi.ems.web.rest;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.domain.OsiContacts;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.mapper.OsiEmployeesMapper;
import com.osi.ems.repository.custom.OsiEmployeesRepositoryCustom;
import com.osi.ems.service.OsiEmployeesService;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;


/**
 * REST controller for Managing Office and Medical Info
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OsiMedicalAndOfficeResource {

	private final Logger LOGGER = Logger.getLogger(OsiMedicalAndOfficeResource.class);
 
    
    @Autowired
	private OsiEmployeesMapper osiEmployeeMapper;
	
    @Autowired
    private OsiEmployeesService osiEmployeesService;
    
    @Autowired
	private AuthTokenStore authTokenStore;
   
    @Autowired
	private Environment env;

    @Autowired
	OsiEmployeesRepositoryCustom osiEmployeesRepository;
    @PostMapping("/saveOsiEmployeesMedicalInfo/{action}")
	public ResponseEntity<SuccessResponse> createOsiEmployees(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiEmployeesDTO osiEmployeesDTO,@PathVariable("action") String action) throws RestServiceException, DataAccessException {
    	LOGGER.info("REST request to Save or Update to Osi Medical Information");
		SuccessResponse successResponse = null;
		OsiEmployees osiEmployees = new OsiEmployees();
		LOGGER.info("createOsiEmployees : Begin");
		try {
			
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			
			osiEmployeesDTO.setCreatedBy(auth.getOsiUserDTO().getId());
			if(action.equals("update"))
				osiEmployeesRepository.saveEmployeeInfo(osiEmployeeMapper.osiEmployeesMedicalInfoToOsiEmployeesDTO(osiEmployeesDTO));
			else
				osiEmployeesRepository.updateEmployee(osiEmployeeMapper.osiEmployeesMedicalInfoToOsiEmployeesDTOUpdate(osiEmployeesDTO));
			LOGGER.info("OSI Employee medical information saved successfully");
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		catch (DataAccessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while saving medical information");
		}

		LOGGER.info("createOsiEmployees : End");
		return ResponseEntity.ok().body(successResponse);
	}

    @PostMapping("/saveOsiEmployeesOfficeInfo/{action}")
	public ResponseEntity<SuccessResponse> saveOsiEmployeesOfficeInfo(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiEmployeesDTO osiEmployeesDTO,@PathVariable("action") String action) throws RestServiceException{
    	LOGGER.info("REST request to Save or Update to Osi Office Information");
		SuccessResponse successResponse = null;
		OsiEmployees osiEmployees = new OsiEmployees();
		LOGGER.info("saveOsiEmployeesOfficeInfo : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiEmployeesDTO.setCreatedBy(auth.getOsiUserDTO().getId());
			
//			try {
				if(action.equals("update"))
					osiEmployeesRepository.saveEmployeeInfo(osiEmployeeMapper.osiEmployeesOfficeInfoToOsiEmployeesDTO(osiEmployeesDTO));
				else
					osiEmployeesRepository.updateEmployee(osiEmployeeMapper.osiEmployeesOfficeInfoToOsiEmployeesDTOUpdate(osiEmployeesDTO));
				
				osiEmployeesService.saveContacts(osiEmployeesDTO,osiEmployeesDTO.getEmployeeId(),osiEmployeesDTO.getFullName());
			/*} catch (DataAccessException ex) {
				LOGGER.error("Error :  "+ex.getSystemMessage());
				throw new BusinessException(ex.getErrorCode(), ex.getSystemMessage());
				
			}catch(BusinessException ex){
				LOGGER.error("Error Occured : "+ex.getSystemMessage());
				throw new BusinessException(ex.getErrorCode(), ex.getSystemMessage());
			}*/
			
			if (osiEmployees != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1046"));
			}
			
			LOGGER.info("OSI Employee office information saved successfully");
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while saving office information");
		}
		LOGGER.info("saveOsiEmployeesOfficeInfo : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}
    
    @GetMapping("/findOsiEmployees/{id}")
    public ResponseEntity<String> findOsiEmployee(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,@PathVariable("id") int employee_id) throws RestServiceException{
    	String response = null;
    	OsiEmployees osiEmployees = null;
    	LOGGER.info("findOsiEmployee : Begin");
    	LOGGER.info("REST request to find the OSI Employees for Employee Id : "+employee_id);
    	try{
    		osiEmployees = osiEmployeesService.findByEmployeeId(employee_id);
    		response = toJsonString(osiEmployees);
    	} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving employee information");
		}
    	LOGGER.info("findOsiEmployee : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    

    @GetMapping("/findOsiContacts/{id}")
    public ResponseEntity<String> findOsiContacts(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,@PathVariable("id") int employee_id) throws Exception{
    	String response = null;
    	OsiContacts osiContacts = null;
    	LOGGER.info("findOsiContacts : Begin");
    	LOGGER.info("REST request to find the OSI Employees contact Information for Employee Id : "+employee_id);
    	try{
    		osiContacts = osiEmployeesService.findOsiContacts(employee_id);
    		if(osiContacts != null)
    			response = toJsonString(osiContacts);
    	} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving osi contacts");
		}
    	LOGGER.info("findOsiContacts : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @PostMapping("/findOsiEmployeesByIdAndDate")
    public ResponseEntity<String> findOsiEmployeesByIdAndDate(@RequestBody String inputObject, @Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException{
    	String response = null;
    	OsiEmployees osiEmployees = null;
    	LOGGER.info("REST request to find the OSI Employees Information for : "+inputObject);
    	LOGGER.info("findOsiEmployeesByIdAndDate : Begin");
    	try{
    		if(null != inputObject) {
    			osiEmployees = osiEmployeesService.findByEmployeeIdAndSerachDate(inputObject);
    			response = toJsonString(osiEmployees);
    		} else {
    			LOGGER.error("invalid Employee Id or Date");
    			throw new BusinessException("ERR_", "invalid employeeId or Date..");
    		}
    	}catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving osi employees");
		}
    	LOGGER.info("findOsiEmployeesByIdAndDate : End");
    	LOGGER.info("Osi Employee Information retreived successfully: "+response);
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    	
    	
    }
    @ExceptionHandler({RestServiceException.class,DataAccessException.class, BusinessException.class})
   	public ResponseEntity<ErrorResponse> exceptionHandler(RestServiceException ex) {
   		ErrorResponse error = new ErrorResponse();
   		error.setErrorCode(ex.getErrorCode());
   		error.setHttpStatus(ex.getHttpStatus());
   		error.setErrorMessage(ex.getErrorMessage());
   		error.setDeveloperMessage(ex.getDeveloperMessage());
   		return new ResponseEntity<ErrorResponse>(error,  HttpStatus.INTERNAL_SERVER_ERROR);
   	}
	
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
	

}
