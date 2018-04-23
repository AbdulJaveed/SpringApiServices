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

import com.osi.ems.service.ICrudOsiCertificationsService;
import com.osi.ems.service.dto.CrudOsiCertificationsVO;
import com.osi.ems.utils.CrudOsiCertificationsUtil;
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
@RequestMapping("api/crud/osicertifications")
public class CrudOsiCertificationsController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CrudOsiCertificationsController.class);
    
    @Autowired
    ICrudOsiCertificationsService crudOsiCertificationsService;
    
	@Autowired
	private Environment env;
	
    @Autowired
   	private AuthTokenStore authTokenStore;
    
    @RequestMapping(value="/getOsiCertifications/{id}",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getOsiCertifications(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException {
    	String response = null;
    	LOGGER.info("getOsiCertifications : Begin");
    	try {
    		response = CrudOsiCertificationsUtil.toJsonString(crudOsiCertificationsService.getOsiCertifications(id));
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the certifications information");
		}
    	LOGGER.info("getOsiCertifications : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    //@RequestMapping(value="/createOsiCertifications",method = RequestMethod.POST, produces="application/json")
    @PostMapping("/createOsiCertifications")
    public ResponseEntity<String>  createOsiCertifications(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody CrudOsiCertificationsVO crudOsiCertificationsVO) throws RestServiceException {
    	String response = null;
    	LOGGER.info("createOsiCertifications : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		crudOsiCertificationsVO = crudOsiCertificationsService.createOsiCertifications(crudOsiCertificationsVO,userId);
    		
    		response = "{\"response\" : \"Saved Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the certifications information");
		}
    	LOGGER.info("createOsiCertifications : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    //@RequestMapping(value="/updateOsiCertifications",method = RequestMethod.PUT, produces="application/json")
    @PutMapping("/updateOsiCertifications")
    public ResponseEntity<String>  updateOsiCertifications(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody CrudOsiCertificationsVO crudOsiCertificationsVO) throws RestServiceException {
    	String response = null;
    	LOGGER.info("updateOsiCertifications : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		crudOsiCertificationsVO = crudOsiCertificationsService.updateOsiCertifications(crudOsiCertificationsVO,userId);
    		
    		response = "{\"response\" : \"Updated Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while updating the certifications information");
		}
    	LOGGER.info("updateOsiCertifications : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/deleteOsiCertifications/{id}",method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity<String>  deleteOsiCertifications(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException {
    	String response = null;
    	LOGGER.info("deleteOsiCertifications : Begin");
    	try {
    		crudOsiCertificationsService.deleteOsiCertifications(id);
    		response = "{\"response\" : \"Deleted Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while deleting the certifications information");
		}
    	LOGGER.info("deleteOsiCertifications : End");
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

