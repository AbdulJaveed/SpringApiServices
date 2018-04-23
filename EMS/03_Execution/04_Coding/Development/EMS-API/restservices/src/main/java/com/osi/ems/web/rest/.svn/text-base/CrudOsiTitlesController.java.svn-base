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

import com.osi.ems.service.ICrudOsiTitlesService;
import com.osi.ems.service.dto.CrudOsiTitlesVO;
import com.osi.ems.utils.CrudOsiTitlesUtil;
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
@RequestMapping("api/crud/osititles")
public class CrudOsiTitlesController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CrudOsiTitlesController.class);
    
    @Autowired
    ICrudOsiTitlesService crudOsiTitlesService;
    
    @Autowired
	private AuthTokenStore authTokenStore;
    
   	@Autowired
   	private Environment env;
    
    @RequestMapping(value="/getOsiTitles/{id}",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getOsiTitles(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException {
    	String response = null;
    	LOGGER.info("getOsiTitles : Begin");
    	try {
    		response = CrudOsiTitlesUtil.toJsonString(crudOsiTitlesService.getOsiTitles(id));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the osi titles");
		}
    	LOGGER.info("getOsiTitles : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    //@RequestMapping(value="/createOsiTitles",method = RequestMethod.POST, produces="application/json")
    @PostMapping("/createOsiTitles")
    public ResponseEntity<String>  createOsiTitles(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody CrudOsiTitlesVO crudOsiTitlesVO) throws RestServiceException {
    	String response = null;
    	LOGGER.info("createOsiTitles : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		crudOsiTitlesVO = crudOsiTitlesService.createOsiTitles(crudOsiTitlesVO,userId);
    		
    		response = "{\"response\" : \"Saved Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating the osi titles");
		}
    	LOGGER.info("createOsiTitles : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    //@RequestMapping(value="/updateOsiTitles",method = RequestMethod.PUT, produces="application/json")
    @PutMapping("/updateOsiTitles")
    public ResponseEntity<String>  updateOsiTitles(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody CrudOsiTitlesVO crudOsiTitlesVO) throws RestServiceException {
    	String response = null;
     	LOGGER.info("updateOsiTitles : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		crudOsiTitlesVO = crudOsiTitlesService.updateOsiTitles(crudOsiTitlesVO,userId);
    		
    		response = "{\"response\" : \"Updated Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while updating the osi titles");
		}
    	LOGGER.info("updateOsiTitles : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/deleteOsiTitles/{id}",method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity<String>  deleteOsiTitles(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException {
    	String response = null;
    	LOGGER.info("deleteOsiTitles : Begin");
    	try {
    		crudOsiTitlesService.deleteOsiTitles(id);
    		response = "{\"response\" : \"Deleted Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while deleting the osi titles");
		}
    	LOGGER.info("deleteOsiTitles : End");
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

