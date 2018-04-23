package com.osi.ems.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.domain.OsiOrganizations;
import com.osi.ems.service.OsiOrganizationsService;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;


@RestController
@RequestMapping(value = "/api")
public class OsiOrganizationsResource {

	private final Logger log = LoggerFactory.getLogger(OsiOrganizationsResource.class);
	
	@Autowired
    private OsiOrganizationsService osiOrganizationsService;
	
	@Autowired
	private Environment env;

	@Autowired
	private AuthTokenStore authTokenStore;
	
	/**
	 * Method for getting the {@link OsiOrganizationsService} information by id.
	 * @return Returns the {@link ResponseData} object.
	 * @throws RestServiceException 
	 */
	@GetMapping
	public ResponseEntity<List<OsiOrganizationsDTO>> getOrganizations() throws RestServiceException {
		List<OsiOrganizationsDTO> organizationsDTOList;
		log.info("getOrganizations : Begin");
		try {
			organizationsDTOList = this.osiOrganizationsService.findAll();
		}catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving osi organizations");
		}
		log.info("getOrganizations : End");
		
		return ResponseEntity.ok().body(organizationsDTOList);
		
	}
	
	@ExceptionHandler({ RestServiceException.class, BusinessException.class })
	public ResponseEntity<ErrorResponse> exceptionHandler(RestServiceException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getErrorCode());
		error.setHttpStatus(ex.getHttpStatus());
		error.setErrorMessage(ex.getErrorMessage());
		error.setDeveloperMessage(ex.getDeveloperMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/organizationsById/{orgId}")
	public ResponseEntity getOrganizationsByOrgId(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable("orgId") int orgId)
			throws Exception {
		OsiOrganizationsDTO result = new OsiOrganizationsDTO();
		log.info("getOrganizationsByOrgId : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			result = osiOrganizationsService.getOrganizationsByOrgId(orgId);
			
		}catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving osi organizations");
		}
		log.info("getOrganizationsByOrgId : End");
		
		return  ResponseEntity.ok().body(result);
	}
	
	  @RequestMapping(value="/organizations",method = RequestMethod.GET, produces="application/json")
	    public ResponseEntity<List<OsiOrganizationsDTO>>  getAllOsiOrganizations(@RequestHeader Map<String, Object> requestHeader) throws RestServiceException {
	    	List<OsiOrganizationsDTO> response = new ArrayList<OsiOrganizationsDTO>();
	    	log.info("getAllOsiOrganizations : Begin");
	    	try {
	    		response = osiOrganizationsService.getOsiOrganizations();
			}catch (BusinessException e) {
				log.error("Error Occured "+e.getMessage());
				throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
						e.getErrorCode(), e.getSystemMessage());
			}catch (Exception e) {
				log.error("Error Occured : "+e.getMessage());
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving osi organizations");
			}
			log.info("getAllOsiOrganizations : End");
	    	return new ResponseEntity<List<OsiOrganizationsDTO>>(response, HttpStatus.OK);
	    }  
	    
	   
	    @RequestMapping(value="/orgSearch",method = RequestMethod.POST, produces="application/json")
	    public ResponseEntity<String>  searchOrgName(@RequestHeader Map<String, Object> requestHeader,@RequestBody String payload) throws RestServiceException {
	    	String response = null;
	    	String name="";
    		String country="";
    		log.info("searchOrgName : Begin");
    		String location="";
	    	Map<String, String> searchFieldsMap = new HashMap<>(0);
	    	try {
	    		if(payload!=null){
	    			JSONObject request = new JSONObject(payload);
	    			Set<String> keySet = request.keySet();
	    			for (String searchField : keySet) {
	    				searchFieldsMap.put(searchField, request.getString(searchField));
					}
	    		
	    			 
	    		}
	    		 name=searchFieldsMap.get("orgName");
	    		 country=searchFieldsMap.get("country");
	    		 location=searchFieldsMap.get("location");
	    		
	    		ObjectMapper mapper = new ObjectMapper();
	    		response = mapper.writeValueAsString(osiOrganizationsService.findOrg(name,country,location));
	    	
			}catch (BusinessException e) {
				log.error("Error Occured "+e.getMessage());
				throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
						e.getErrorCode(), e.getSystemMessage());
			}catch (Exception e) {
				log.error("Error Occured : "+e.getMessage());
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving osi organizations");
			}
			log.info("searchOrgName : End");
	    	
	    	return new ResponseEntity<String>(response, HttpStatus.OK);
	    }
	 
	    @RequestMapping(value="/contactPersonSearch",method = RequestMethod.POST, produces="application/json")
	    public ResponseEntity<String>  contactPersonSearch(@RequestHeader Map<String, Object> requestHeader,@RequestBody String payload) throws RestServiceException {
	    
	    	String response = null;
	    	String empName="";
    		Integer orgId=null;
    		log.info("contactPersonSearch : Begin");
	    	Map<String, String> searchFieldsMap = new HashMap<>(0);
	    	try {
	    		if(payload!=null){
	    			JSONObject request = new JSONObject(payload);
	    			Set<String> keySet = request.keySet();
	    			for (String searchField : keySet) {
	    				searchFieldsMap.put(searchField, request.getString(searchField));
					}
	    		
	    			empName=searchFieldsMap.get("empName");
		    		orgId=Integer.parseInt(searchFieldsMap.get("orgId"));		    			    		
		    		ObjectMapper mapper = new ObjectMapper();
		    		response = mapper.writeValueAsString(osiOrganizationsService.contactPersonSearch(empName,orgId));
		    	
	    		}
	    	
			}catch (BusinessException e) {
				log.error("Error Occured "+e.getMessage());
				throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
						e.getErrorCode(), e.getSystemMessage());
			}catch (Exception e) {
				log.error("Error Occured : "+e.getMessage());
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the contacts");
			}
			log.info("contactPersonSearch : End");
	    	
	    	return new ResponseEntity<String>(response, HttpStatus.OK);
	    }
	 
	    
	    @RequestMapping(value="/getOsiCurrencyInfo",method = RequestMethod.GET, produces="application/json")
	    public ResponseEntity<String>  getOsiCurrencyInfo(@RequestHeader Map<String, Object> requestHeader) throws RestServiceException {
	    	String response = null;
	    	log.info("getOsiCurrencyInfo : Begin");
	    	try {
	    		
	    		ObjectMapper mapper = new ObjectMapper();
	    		response = mapper.writeValueAsString(osiOrganizationsService.getOsiCurrencyInfo());
	    		
	    	}catch (BusinessException e) {
				log.error("Error Occured "+e.getMessage());
				throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
						e.getErrorCode(), e.getSystemMessage());
			}catch (Exception e) {
				log.error("Error Occured : "+e.getMessage());
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the currency information");
			}
			log.info("getOsiCurrencyInfo : End");
	    	return new ResponseEntity<String>(response, HttpStatus.OK);
	    }
	    
	    @PostMapping("/organizations")
		public ResponseEntity<SuccessResponse> createOrganizations(
				@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
				@RequestBody OsiOrganizationsDTO orgVO) throws DataAccessException,RestServiceException {
	    	log.info("createOrganizations : Begin");
			SuccessResponse successResponse = null;
			OsiOrganizations org = new OsiOrganizations();

			try {
				AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
				Integer userId = auth.getOsiUserDTO().getId();
				
				org = osiOrganizationsService.createOrganization(orgVO, userId);
				if (org!= null) {
					successResponse = new SuccessResponse();
					successResponse.setHttpStatus(HttpStatus.OK.value());
					successResponse.setMessage(org.getOrgId().toString());
				}

			}  catch (DataAccessException e) {
				throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), e.getSystemMessage(),
						e.getErrorCode(), e.getSystemMessage());
			}catch (BusinessException e) {
				log.error("Error Occured "+e.getMessage());
				throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
						e.getErrorCode(), e.getSystemMessage());
			}catch (Exception e) {
				log.error("Error Occured : "+e.getMessage());
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating the organization");
			}
			log.info("createOrganizations : End");
			return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
		}
	    
}
