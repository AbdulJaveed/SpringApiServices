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

import com.osi.ems.service.IListOsiDepratmentService;
import com.osi.ems.utils.ListOsiDepratmentUtil;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;

/**
 * @author osi
 *
 */

@Controller
@RestController
@RequestMapping("api/list/osidepratment")
public class ListOsiDepratmentController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ListOsiDepratmentController.class);
    
    @Autowired
    IListOsiDepratmentService osidepratmentService;
    
    
	@Autowired
   	private Environment env;
	
	
    @RequestMapping(value="/getAllOsiDepratments",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getAllOsiDepratments(@RequestHeader Map<String, Object> requestHeader) throws RestServiceException {
    	String response = null;
    	try {
    		response = ListOsiDepratmentUtil.toJsonString(osidepratmentService.getOsiDepratmentList(null));
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting departments");
		}
    	LOGGER.info("searchOsiCostCenterWithPagenation : End");
    	
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/searchOsiDepratment",method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<String>  searchOsiDepratment(@RequestHeader Map<String, Object> requestHeader,@RequestBody String payload) throws RestServiceException {
    	String response = null;
    	Map<String, String> searchFieldsMap = new HashMap<>(0);
    	LOGGER.info("searchOsiDepratment : Begin");
    	try {
    		if(payload!=null){
    			JSONObject request = new JSONObject(payload);
    			Set<String> keySet = request.keySet();
    			for (String searchField : keySet) {
    				searchFieldsMap.put(searchField, request.getString(searchField));
				}
    		}
    		response = ListOsiDepratmentUtil.toJsonString(osidepratmentService.getOsiDepratmentList(searchFieldsMap));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching the departments");
		}
    	LOGGER.info("searchOsiDepratment : End");
    	
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/searchOsiDepratmentWithPagenation",method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<String>  searchOsiDepratmentWithPagenation(@RequestHeader Map<String, Object> requestHeader,@RequestBody String payload) throws RestServiceException {
    	String response = null;
    	LOGGER.info("searchOsiDepratmentWithPagenation : Begin");
    	Map<String, String> searchFieldsMap = new HashMap<>(0);
    	try {
    		if(payload!=null){
    			JSONObject request = new JSONObject(payload);
    			Set<String> keySet = request.keySet();
    			for (String searchField : keySet) {
    				searchFieldsMap.put(searchField, request.getString(searchField));
				}
    		}
    		response = ListOsiDepratmentUtil.toJsonString(osidepratmentService.getOsiDepratmentList(searchFieldsMap));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching the departments");
		}
    	LOGGER.info("searchOsiDepratmentWithPagenation : End");
    	
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

