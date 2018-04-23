/**
 * 
 */
package com.osi.ems.web.rest;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.service.ICrudOsiGradesService;
import com.osi.ems.service.dto.CrudOsiGradesVO;
import com.osi.ems.utils.CrudOsiGradesUtil;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;

/**
 * @author osi
 *
 */

@Controller
@RestController
@RequestMapping("api/crud/osigrades")
public class CrudOsiGradesController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CrudOsiGradesController.class);
    
    @Autowired
    ICrudOsiGradesService crudOsiGradesService;
    
    @Autowired
   	private AuthTokenStore authTokenStore;
    
    @Autowired
	private Environment env;
    
    @RequestMapping(value="/getOsiGrades/{id}",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getOsiGrades(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException, Exception {
    	String response = null;
    	LOGGER.info("getOsiGrades : Begin");
    	try {
    		response = CrudOsiGradesUtil.toJsonString(crudOsiGradesService.getOsiGrades(id));
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the grades");
		}
    	LOGGER.info("getOsiGrades : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    //@RequestMapping(value="/createOsiGrades",method = RequestMethod.POST, produces="application/json")
    @PostMapping("/createOsiGrades")
    public ResponseEntity<String>  createOsiGrades(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody CrudOsiGradesVO crudOsiGradesVO) throws RestServiceException, BusinessException {
    	String response = null;
    	LOGGER.info("createOsiGrades : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		crudOsiGradesVO = crudOsiGradesService.createOsiGrades(crudOsiGradesVO,userId);
    		
    		response = "{\"response\" : \"Saved Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating the grades");
		}
    	LOGGER.info("createOsiGrades : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    //@RequestMapping(value="/updateOsiGrades",method = RequestMethod.PUT, produces="application/json")
    @PutMapping("/updateOsiGrades")
    public ResponseEntity<String>  updateOsiGrades(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody CrudOsiGradesVO crudOsiGradesVO) throws RestServiceException, Exception {
    	String response = null;
    	LOGGER.info("updateOsiGrades : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		crudOsiGradesVO = crudOsiGradesService.updateOsiGrades(crudOsiGradesVO,userId);
    		
    		response = "{\"response\" : \"Updated Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while updating the grades");
		}
    	LOGGER.info("updateOsiGrades : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/deleteOsiGrades/{id}",method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity<String>  deleteOsiGrades(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException,Exception {
    	String response = null;
    	LOGGER.info("deleteOsiGrades : Begin");
    	try {
    		crudOsiGradesService.deleteOsiGrades(id);
    		response = "{\"response\" : \"Deleted Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while deleting the grades");
		}
    	LOGGER.info("deleteOsiGrades : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
    @RequestMapping(value="/getOsiGradesHistory/{gradeId}/{orgId}",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getOsiGradesHistory(@RequestHeader Map<String, Object> requestHeader
    		, @PathVariable("gradeId") int gradeId, @PathVariable("orgId") int orgId) throws RestServiceException, Exception {
    	String response = null;
    	LOGGER.info("getOsiGrades : Begin");
    	try {
    		response = CrudOsiGradesUtil.toJsonString(crudOsiGradesService.getOsiGradesHistory(gradeId, orgId));
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the grades");
		}
    	LOGGER.info("getOsiGrades : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	@ExceptionHandler(RestServiceException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(RestServiceException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getErrorCode());
		error.setHttpStatus(ex.getHttpStatus());
		error.setErrorMessage(ex.getErrorMessage());
		error.setDeveloperMessage(ex.getDeveloperMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
  
}

