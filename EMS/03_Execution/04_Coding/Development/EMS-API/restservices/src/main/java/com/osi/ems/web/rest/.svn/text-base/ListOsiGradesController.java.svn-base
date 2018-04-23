/**
 * 
 */
package com.osi.ems.web.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.service.IListOsiGradesService;
import com.osi.ems.utils.ListOsiGradesUtil;
import com.osi.ems.utils.ListOsiTitlesUtil;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;

/**
 * @author osi
 *
 */

@Controller
@RestController
@RequestMapping("api/list/osigrades")
public class ListOsiGradesController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ListOsiGradesController.class);
    
    @Autowired
    IListOsiGradesService osigradesService;
    

	@Autowired
   	private Environment env;
	
    @RequestMapping(value="/getAllOsiGradess",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getAllOsiGradess(@RequestHeader Map<String, Object> requestHeader) throws RestServiceException {
    	String response = null;
    	LOGGER.info("getAllOsiGradess : Begin");
    	try {
    		response = ListOsiGradesUtil.toJsonString(osigradesService.getOsiGradesList(null));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the osi grades");
		}
    	LOGGER.info("getAllOsiGradess : End");
    	
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/searchOsiGrades",method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<String>  searchOsiGrades(@RequestHeader Map<String, Object> requestHeader,@RequestBody String payload) throws RestServiceException {
    	String response = null;
    	LOGGER.info("searchOsiGrades : Begin");
    	Map<String, String> searchFieldsMap = new HashMap<>(0);
    	try {
    		if(payload!=null){
    			JSONObject request = new JSONObject(payload);
    			Set<String> keySet = request.keySet();
    			for (String searchField : keySet) {
    				searchFieldsMap.put(searchField, request.getString(searchField));
				}
    		}
    		response = ListOsiGradesUtil.toJsonString(osigradesService.getOsiGradesList(searchFieldsMap));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching the osi grades");
		}
    	LOGGER.info("searchOsiGrades : End");
    	
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/searchOsiGradesWithPagenation",method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<String>  searchOsiGradesWithPagenation(@RequestHeader Map<String, Object> requestHeader,@RequestBody String payload) throws RestServiceException {
    	String response = null;
    	LOGGER.info("searchOsiGradesWithPagenation : Begin");
    	Map<String, String> searchFieldsMap = new HashMap<>(0);
    	try {
    		if(payload!=null){
    			JSONObject request = new JSONObject(payload);
    			Set<String> keySet = request.keySet();
    			for (String searchField : keySet) {
    				searchFieldsMap.put(searchField, request.getString(searchField));
				}
    		}
    		response = ListOsiGradesUtil.toJsonString(osigradesService.getOsiGradesList(searchFieldsMap));
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching the osi grades");
		}
    	LOGGER.info("searchOsiGradesWithPagenation : End");
    	
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/getAllOsiTitles",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getAllOsiTitless(@RequestHeader Map<String, Object> requestHeader) throws RestServiceException {
    	String response = null;
    	LOGGER.info("getAllOsiTitless : Begin");
    	try {
    		response = ListOsiTitlesUtil.toJsonString(osigradesService.getOsiTitlesList());
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching the osi titles");
		}
    	LOGGER.info("getAllOsiTitless : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
    @RequestMapping(value="/getAllOsiTitleGrades",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getAllOsiTitleGrades(@RequestHeader Map<String, Object> requestHeader) throws RestServiceException {
    	String response = null;
    	LOGGER.info("getAllOsiTitless : Begin");
    	try {
    		response = ListOsiTitlesUtil.toJsonString(osigradesService.getOsiTitleGradesList());
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching the osi title grades");
		}
    	LOGGER.info("getAllOsiTitless : End");
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

