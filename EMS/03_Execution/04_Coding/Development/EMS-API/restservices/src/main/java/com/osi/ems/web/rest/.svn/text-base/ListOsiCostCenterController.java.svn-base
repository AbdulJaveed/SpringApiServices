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

import com.osi.ems.service.IListOsiCostCenterService;
import com.osi.ems.utils.ListOsiCostCenterUtil;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;

/**
 * @author osi
 *
 */

@Controller
@RestController
@RequestMapping("api/list/osicostcenter")
public class ListOsiCostCenterController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ListOsiCostCenterController.class);
    
    @Autowired
    IListOsiCostCenterService osicostcenterService;
    
	@Autowired
   	private Environment env;
	
    @RequestMapping(value="/getAllOsiCostCenters",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getAllOsiCostCenters(@RequestHeader Map<String, Object> requestHeader) throws RestServiceException {
    	String response = null;
    	LOGGER.info("getAllOsiCostCenters : Begin");
    	try {
    		response = ListOsiCostCenterUtil.toJsonString(osicostcenterService.getOsiCostCenterList(null));
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting cost centers");
		}
    	LOGGER.info("getAllOsiCostCenters : End");
    	
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/searchOsiCostCenter",method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<String>  searchOsiCostCenter(@RequestHeader Map<String, Object> requestHeader,@RequestBody String payload) throws RestServiceException {
    	String response = null;
    	LOGGER.info("searchOsiCostCenter : Begin");
    	Map<String, String> searchFieldsMap = new HashMap<>(0);
    	try {
    		if(payload!=null){
    			JSONObject request = new JSONObject(payload);
    			Set<String> keySet = request.keySet();
    			for (String searchField : keySet) {
    				searchFieldsMap.put(searchField, request.getString(searchField));
				}
    		}
    		response = ListOsiCostCenterUtil.toJsonString(osicostcenterService.getOsiCostCenterList(searchFieldsMap));
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching cost centers");
		}
    	LOGGER.info("searchOsiCostCenter : End");
    	
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/searchOsiCostCenterWithPagenation",method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<String>  searchOsiCostCenterWithPagenation(@RequestHeader Map<String, Object> requestHeader,@RequestBody String payload) throws RestServiceException {
    	String response = null;
    	LOGGER.info("searchOsiCostCenterWithPagenation : Begin");
    	Map<String, String> searchFieldsMap = new HashMap<>(0);
    	try {
    		if(payload!=null){
    			JSONObject request = new JSONObject(payload);
    			Set<String> keySet = request.keySet();
    			for (String searchField : keySet) {
    				searchFieldsMap.put(searchField, request.getString(searchField));
				}
    		}
    		response = ListOsiCostCenterUtil.toJsonString(osicostcenterService.getOsiCostCenterList(searchFieldsMap));
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching cost centers");
		}
    	LOGGER.info("searchOsiCostCenterWithPagenation : End");
    	
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

