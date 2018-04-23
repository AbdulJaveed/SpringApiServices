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

import com.osi.ems.service.ICrudOsiSkilsService;
import com.osi.ems.service.dto.CrudOsiSkilsVO;
import com.osi.ems.utils.CrudOsiSkilsUtil;
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
@RequestMapping("api/crud/osiskils")
public class CrudOsiSkilsController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CrudOsiSkilsController.class);
    
    @Autowired
    ICrudOsiSkilsService crudOsiSkilsService;
    
    @Autowired
   	private AuthTokenStore authTokenStore;
    
   	@Autowired
   	private Environment env;
   	
    @RequestMapping(value="/getOsiSkils/{id}",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getOsiSkils(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException {
    	String response = null;
    	LOGGER.info("getOsiSkils : Begin");
    	try {
    		response = CrudOsiSkilsUtil.toJsonString(crudOsiSkilsService.getOsiSkils(id));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the osi skills");
		}
    	LOGGER.info("getOsiSkils : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    //@RequestMapping(value="/createOsiSkils",method = RequestMethod.POST, produces="application/json")
    @PostMapping("/createOsiSkils")
    public ResponseEntity<String>  createOsiSkils(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody CrudOsiSkilsVO crudOsiSkilsVO) throws RestServiceException {
    	String response = null;
    	LOGGER.info("createOsiSkils : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		crudOsiSkilsVO = crudOsiSkilsService.createOsiSkils(crudOsiSkilsVO,userId);
    		
    		response = "{\"response\" : \"Saved Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating the osi skills");
		}
    	LOGGER.info("createOsiSkils : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    //@RequestMapping(value="/updateOsiSkils",method = RequestMethod.PUT, produces="application/json")
    @PutMapping("/updateOsiSkils")
    public ResponseEntity<String>  updateOsiSkils(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody CrudOsiSkilsVO crudOsiSkilsVO) throws RestServiceException {
    	String response = null;
    	LOGGER.info("updateOsiSkils : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		crudOsiSkilsVO = crudOsiSkilsService.updateOsiSkils(crudOsiSkilsVO,userId);
    		
    		response = "{\"response\" : \"Updated Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while updating the osi skills");
		}
    	LOGGER.info("updateOsiSkils : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/deleteOsiSkils/{id}",method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity<String>  deleteOsiSkils(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException {
    	String response = null;
    	LOGGER.info("deleteOsiSkils : Begin");
    	try {
    		crudOsiSkilsService.deleteOsiSkils(id);
    		response = "{\"response\" : \"Deleted Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while deleting the osi skills");
		}
    	LOGGER.info("deleteOsiSkils : End");
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

