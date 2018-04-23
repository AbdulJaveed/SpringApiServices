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

import com.osi.ems.service.ICrudOsiJobCodesService;
import com.osi.ems.service.dto.CrudOsiJobCodesVO;
import com.osi.ems.utils.CrudOsiJobCodesUtil;
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
@RequestMapping("api/crud/osijobcodes")
public class CrudOsiJobCodesController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CrudOsiJobCodesController.class);
    
    @Autowired
    ICrudOsiJobCodesService crudOsiJobCodesService;
    
   	@Autowired
   	private Environment env;
   	
    @Autowired
	private AuthTokenStore authTokenStore;
    
    @RequestMapping(value="/getOsiJobCodes/{id}",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getOsiJobCodes(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException {
    	String response = null;
    	LOGGER.info("getOsiJobCodes : Begin");
    	try {
    		response = CrudOsiJobCodesUtil.toJsonString(crudOsiJobCodesService.getOsiJobCodes(id));
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the job codes");
		}
    	LOGGER.info("getOsiJobCodes : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    //@RequestMapping(value="/createOsiJobCodes",method = RequestMethod.POST, produces="application/json")
    @PostMapping("/createOsiJobCodes")
    public ResponseEntity<String>  createOsiJobCodes(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody CrudOsiJobCodesVO crudOsiJobCodesVO) throws RestServiceException {
    	String response = null;
    	LOGGER.info("getOsiJobCodes : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		crudOsiJobCodesVO = crudOsiJobCodesService.createOsiJobCodes(crudOsiJobCodesVO,userId);
    		if(crudOsiJobCodesVO!=null)
    		{response = "{\"response\" : \"Saved Successfully\"}";}
    		else{
    		response = "{\"response\" : \"Error occured while saving\"}";}
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating the job codes");
		}
    	LOGGER.info("createOsiJobCodes : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    //@RequestMapping(value="/updateOsiJobCodes",method = RequestMethod.PUT, produces="application/json")
    @PutMapping("/updateOsiJobCodes")
    public ResponseEntity<String>  updateOsiJobCodes(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody CrudOsiJobCodesVO crudOsiJobCodesVO) throws RestServiceException {
    	String response = null;
    	LOGGER.info("updateOsiJobCodes : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		crudOsiJobCodesVO = crudOsiJobCodesService.updateOsiJobCodes(crudOsiJobCodesVO,userId);
        		response = "{\"response\" : \"Saved Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while updating the job codes");
		}
    	LOGGER.info("updateOsiJobCodes : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/deleteOsiJobCodes/{id}",method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity<String>  deleteOsiJobCodes(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException {
    	String response = null;
    	LOGGER.info("deleteOsiJobCodes : Begin");
    	try {
    		crudOsiJobCodesService.deleteOsiJobCodes(id);
    		response = "{\"response\" : \"Deleted Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while deleting the job codes");
		}
    	LOGGER.info("deleteOsiJobCodes : End");
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

