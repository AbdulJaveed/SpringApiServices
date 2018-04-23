package com.osi.ems.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
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

import com.osi.ems.common.CommonService;
import com.osi.ems.common.Util;
import com.osi.ems.domain.OsiAttachments;
import com.osi.ems.domain.OsiEmployees;
import com.osi.ems.mapper.OsiEmployeesMapper;
import com.osi.ems.service.OrgHierarchyService;
import com.osi.ems.service.OsiAddressService;
import com.osi.ems.service.OsiContactsService;
import com.osi.ems.service.OsiEmployeesService;
import com.osi.ems.service.dto.OrgHierarchyDTO;
import com.osi.ems.service.dto.OsiAttachmentsDTO;
import com.osi.ems.service.dto.OsiContactsDto;
import com.osi.ems.service.dto.OsiCountriesDTO;
import com.osi.ems.service.dto.OsiCountryVisasDTO;
import com.osi.ems.service.dto.OsiEmpAdditionalDocsDTO;
import com.osi.ems.service.dto.OsiOrgAddressesDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;

@RestController
@RequestMapping(value = "/api/v1/employees")
public class OsiEmployeesResource {

	private final Logger LOGGER = Logger.getLogger(OsiEmployeesResource.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private AuthTokenStore authTokenStore;
	
	@Autowired
	private OsiEmployeesService osiEmployeesService;
	
	@Autowired
	private OsiAddressService osiAddressService;
	
	@Autowired
	private OsiContactsService osiContactsService;
	
	@Autowired
	private OrgHierarchyService orgHierarchyService;
	
	@Autowired
	private OsiEmployeesMapper osiEmployeesMapper;
		
	@Autowired
	private CommonService commonService;
	
	@PostMapping("/{action}")
	public ResponseEntity<OsiEmployees> saveEmployeeBasicInfo(@RequestBody OsiEmployees osiEmployees, 
			@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken
			, @PathVariable("action") String action) throws RestServiceException {
		Integer loggedInEmployeeId = null;
		LOGGER.info("saveEmployeeBasicInfo : Begin");
		try {
			LOGGER.info(" #### Saving basic info in "+ action + " mode ..");
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = token.getOsiUserDTO().getId();
			if(action.equalsIgnoreCase("correction"))
				osiEmployees = osiEmployeesService.updateEmployee(osiEmployees, loggedInEmployeeId);				
			else if(action.equalsIgnoreCase("update") || action.equals("undefined"))
				osiEmployees = osiEmployeesService.saveEmployee(osiEmployees, loggedInEmployeeId);
			
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while saving the employee basic information");
		}
		LOGGER.info("saveEmployeeBasicInfo : End");
		LOGGER.info(" #### successfully Saved basic info in "+ action + " mode ..");
		/*return new ResponseEntity<OsiEmployees>(osiEmployees, HttpStatus.OK);*/
		return ResponseEntity.ok().body(osiEmployees);
	}
	
	@PostMapping(value = "/personalinfo/{action}")
	@Transactional
	public ResponseEntity<OsiEmployees> saveEmployeePersonalInfo(@RequestBody String osiEmployees, 
			@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken
			, @PathVariable("action") String action) throws RestServiceException {
		OsiEmployees osiEmployees1=null;
		LOGGER.info("saveEmployeePersonalInfo : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			JSONObject inputJson = new JSONObject(osiEmployees);
			JSONObject permanantAddress = inputJson.getJSONObject("perminent");
			JSONObject mailAddress = inputJson.getJSONObject("emailing");
			JSONArray contactsList = inputJson.getJSONArray("contacts");
			JSONObject employeesObj = inputJson.getJSONObject("osiEmployees");
			
			
			OsiOrgAddressesDTO permanantAddressDto = new OsiOrgAddressesDTO();
			
			boolean isPermanantAddressToBeSave = false;
			boolean isMailAddressToBeSave = false;
			if(permanantAddress.isNull("addressLine1") 
					&& permanantAddress.isNull("addressLine2") 
					&& permanantAddress.isNull("city")
					&& permanantAddress.isNull("countryId") 
					&& permanantAddress.isNull("stateId")
					&& permanantAddress.isNull("zipcode")) {
				isPermanantAddressToBeSave = false;
			}else {
				if(permanantAddress.has("addressId")){
					if(!permanantAddress.isNull("addressId"))
						
					permanantAddressDto.setAddressId(permanantAddress.getInt("addressId"));
				}
				if(permanantAddress.has("addressLine1")){
					if(!permanantAddress.isNull("addressLine1"))
						
					permanantAddressDto.setAddressLine1(permanantAddress.getString("addressLine1"));
				}
				if(permanantAddress.has("addressLine2")){
					if(!permanantAddress.isNull("addressLine2"))
						permanantAddressDto.setAddressLine2(permanantAddress.getString("addressLine2"));
				
				}
				if(permanantAddress.has("city")){
					if(!permanantAddress.isNull("city"))
						permanantAddressDto.setCity(permanantAddress.getString("city"));
				
				}
				
				if(permanantAddress.has("countryId")){
					if(!permanantAddress.isNull("countryId"))
						permanantAddressDto.setCountryId(permanantAddress.getInt("countryId"));
				
				}
				if(permanantAddress.has("objectId"))
					permanantAddressDto.setObjectId(permanantAddress.getInt("objectId"));
				if(permanantAddress.has("objectType"))
					permanantAddressDto.setObjectType(permanantAddress.getString("objectType"));
				
				if(permanantAddress.has("stateId")){
					if(!permanantAddress.isNull("stateId"))
						permanantAddressDto.setStateId(permanantAddress.getInt("stateId"));
				
				}
				if(permanantAddress.has("zipcode")){
					if(!permanantAddress.isNull("zipcode"))
						permanantAddressDto.setZipcode(permanantAddress.getString("zipcode"));
				
				}
				
				isPermanantAddressToBeSave = true;
			}
			
			OsiOrgAddressesDTO mailingAddressDto = new OsiOrgAddressesDTO();
			
			if(mailAddress.isNull("addressLine1") 
					&& mailAddress.isNull("addressLine2") 
					&& mailAddress.isNull("city")
					&& mailAddress.isNull("countryId") 
					&& mailAddress.isNull("stateId")
					&& mailAddress.isNull("zipcode")) {
				isMailAddressToBeSave = false;
			}else {
				if(mailAddress.has("addressId")){
					if(!mailAddress.isNull("addressId"))
						mailingAddressDto.setAddressId(mailAddress.getInt("addressId"));
				
				}
				if(mailAddress.has("addressLine1")){
					if(!mailAddress.isNull("addressLine1"))
						mailingAddressDto.setAddressLine1(mailAddress.getString("addressLine1"));
				
				}
				
				if(mailAddress.has("addressLine2")){
					if(!mailAddress.isNull("addressLine2"))
						mailingAddressDto.setAddressLine2(mailAddress.getString("addressLine2"));
				
				}
				//  
				if(mailAddress.has("city")){
					if(!mailAddress.isNull("city"))
						mailingAddressDto.setCity(mailAddress.getString("city"));
				
				}
				if(mailAddress.has("countryId")){
					if(!mailAddress.isNull("countryId"))
						mailingAddressDto.setCountryId(mailAddress.getInt("countryId"));
				
				}
				if(mailAddress.has("stateId")){
					if(!mailAddress.isNull("stateId"))
						mailingAddressDto.setStateId(mailAddress.getInt("stateId"));
				
				}
				if(mailAddress.has("zipcode")){
					if(!mailAddress.isNull("zipcode"))
						mailingAddressDto.setZipcode(mailAddress.getString("zipcode"));
				
				}
				if(mailAddress.has("objectId"))
					permanantAddressDto.setObjectId(mailAddress.getInt("objectId"));
				if(mailAddress.has("objectType"))
					mailingAddressDto.setObjectType(mailAddress.getString("objectType"));
				
				isMailAddressToBeSave = true;
			}
			
			try {
				OsiOrgAddressesDTO permanantAddressSavedObj = null;
				OsiOrgAddressesDTO mailingAddressSavedObj = null;
				if(isPermanantAddressToBeSave)
					permanantAddressSavedObj = osiAddressService.saveAddress(permanantAddressDto, auth.getOsiUserDTO().getId());
				if(isMailAddressToBeSave)
					mailingAddressSavedObj = osiAddressService.saveAddress(mailingAddressDto, auth.getOsiUserDTO().getId());
			
			List<OsiContactsDto> osiContacts = new ArrayList<OsiContactsDto>();
			for(int i =0; i< contactsList.length() ; i++) {
				OsiContactsDto contactDto = new OsiContactsDto();
				JSONObject obj = contactsList.getJSONObject(i);
				if(obj.has("contactId"))
					contactDto.setContactId(obj.getInt("contactId"));
				if(obj.has("contactName"))
					contactDto.setContactName(obj.getString("contactName"));
				if(obj.has("contactNumber"))
					contactDto.setContactNumber(obj.getString("contactNumber"));
				if(obj.has("contactType"))
					contactDto.setContactType(obj.getString("contactType"));
				if(obj.has("createdBy"))
					contactDto.setCreatedBy(obj.getInt("createdBy"));
				if(obj.has("creationDate"))
					contactDto.setCreationDate(obj.getString("creationDate"));
				if(obj.has("lastUpdateDate"))
					contactDto.setLastUpdateDate(obj.getString("lastUpdateDate"));
				if(obj.has("lastUpdatedBy"))
					contactDto.setLastUpdatedBy(obj.getInt("lastUpdatedBy"));
				if(obj.has("countryCode"))
					contactDto.setCountryCode(obj.getString("countryCode"));
				if(obj.has("relation"))
					contactDto.setRelation(obj.getString("relation"));
				if(obj.has("seq"))
					contactDto.setSeq(obj.getInt("seq"));
				contactDto.setEmployeeId(employeesObj.getInt("employeeId"));
				
				osiContacts.add(contactDto);
			}
			if(!osiContacts.isEmpty())
				osiContactsService.saveContactList(osiContacts, auth.getOsiUserDTO().getId());
			
			OsiEmployees employee = new OsiEmployees();
			if(employeesObj.has("employeeId"))
				employee.setEmployeeId(employeesObj.getInt("employeeId"));
			/*if(employeesObj.has("dateOfBirth"))
				employee.setDateOfBirth(employeesObj.getString("dateOfBirth"));*/
			if(employeesObj.has("knownAs")){
				if(!employeesObj.isNull("knownAs"))
						employee.setKnownAs(employeesObj.getString("knownAs"));
			}
			if(employeesObj.has("bloodType")) {
				employee.setBloodType(employeesObj.getString("bloodType"));
			}
			if(employeesObj.has("personalEmail"))
				employee.setPersonalEmail(employeesObj.getString("personalEmail"));
			if(employeesObj.has("previousLastName")){
				if(!employeesObj.isNull("previousLastName"))
					employee.setPreviousLastName(employeesObj.getString("previousLastName"));
			}
			if(employeesObj.has("version")){
				if(!employeesObj.isNull("version"))
					employee.setVersion(employeesObj.getInt("version"));
			}
			if(employeesObj.has("totalExp")){
				if(!employeesObj.isNull("totalExp"))
					employee.setTotalExp(employeesObj.getDouble("totalExp"));
			}
			employee.setPermanentAddressId((permanantAddressSavedObj != null) ? permanantAddressSavedObj.getAddressId() : null);
			employee.setMailAddressId((mailingAddressSavedObj != null) ? mailingAddressSavedObj.getAddressId() : null);
			
			OsiAttachments osiAttachment = null;
			
			if(employeesObj.has("osiEmpAttachments") && !employeesObj.isNull("osiEmpAttachments")) {
				JSONArray resumeAttachments = employeesObj.getJSONArray("osiEmpAttachments");
				for(int k =0; k< resumeAttachments.length() ; k++) {
					OsiAttachmentsDTO attachmentDto = new OsiAttachmentsDTO();
					JSONObject obj = resumeAttachments.getJSONObject(k);
					System.out.println(employeesObj.get("resumeId"));
					if(employeesObj.has("resumeId") && !employeesObj.get("resumeId").equals(null))
						attachmentDto.setAttachmentId(employeesObj.getInt("resumeId"));
					if(obj.has("originalFileName"))
						attachmentDto.setOriginalFileName(obj.getString("originalFileName"));
					//if(obj.has("fileType"))
						//attachmentDto.setFileType(obj.getString("fileType"));
					attachmentDto.setFileType("octet-stream");
					if(obj.has("duplicateFileName"))
						attachmentDto.setDuplicateFileName(obj.getString("duplicateFileName"));
					if(employeesObj.has("employeeId")) {
						attachmentDto.setEmployeeId(employeesObj.getInt("employeeId"));
						attachmentDto.setObjectId(employeesObj.getInt("employeeId"));
					}
					if(obj.has("fileContent"))
						attachmentDto.setFileContent(obj.getString("fileContent"));
					attachmentDto.setAttachmentType("RESUME");
					attachmentDto.setObjectType("osi_employees");
					attachmentDto.setCreatedBy(auth.getOsiUserDTO().getId());
					attachmentDto.setLastUpdatedBy(auth.getOsiUserDTO().getId());
					
					osiAttachment = osiEmployeesMapper.mapToAttachments(attachmentDto);
				}
				employee.setResumeId(osiAttachment.getAttachmentId());
				employee.setResumeLastUpdated(commonService.getCurrentDateStringinUTC());
				employee.setResumeExists(1);
			} else {
				employee.setResumeExists(0);
			}
			
				
			if(action.equalsIgnoreCase("correction"))
				osiEmployees1 = osiEmployeesService.updateEmployeePersonalInfo(employee, auth.getOsiUserDTO().getId());
			else if(action.equalsIgnoreCase("update"))
				osiEmployees1 = osiEmployeesService.saveEmployeePersonalInfo(employee, auth.getOsiUserDTO().getId());
			} catch (BusinessException e) {
				LOGGER.error("Error Occured "+e.getSystemMessage());
				throw new BusinessException(e.getErrorCode(), e.getSystemMessage());
			}catch (Exception e) {
				LOGGER.error("Error Occured : "+e.getMessage());
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						env.getProperty("ERR_1000"),"ERR_1000", "Error occured while saving the personal information information");
			}
			//TODO hard coded value
			//result = osiEmployeesService.saveEmployeePersonalInfo(osiEmployees, loggedInEmployeeId);
			/*if (result > 0) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1006"));
			}*/
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while saving the employee personal information");
		}
		LOGGER.info("saveEmployeePersonalInfo : End");
		return new ResponseEntity<OsiEmployees>(osiEmployees1, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<String> getEmployeeBasicInfo(@PathVariable("id") int id, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		String response = null;
		try {
			LOGGER.info("getEmployeeBasicInfo : Begin");
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token == null)
				throw new BusinessException("ERR_2000", "Invalid Token");
			
			OsiEmployees osiEmployee = osiEmployeesService.findByEmployeeId(id);
			response = Util.toJsonString(osiEmployee);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the employee basic information");
		}
		LOGGER.info("getEmployeeBasicInfo : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	/**
	 * Search based on the employeeId and search date
	 * @param inputObj
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@PostMapping("/retrieve")
	public ResponseEntity<String> getEmployeeBasicInfo(@RequestBody String inputObject, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		String response = null;
		LOGGER.info("getEmployeeBasicInfo : Begin");
		try {
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token == null)
				throw new BusinessException("ERR_2000", "Invalid Token");
			
			OsiEmployees osiEmployee = osiEmployeesService.findByEmployeeIdAndSerachDate(inputObject);
			response = Util.toJsonString(osiEmployee);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the employee basic information");
		}
		LOGGER.info("getEmployeeBasicInfo : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/searchEmployees")
	public ResponseEntity<String> searchEmployees(@RequestBody String searchData, 
			@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		String response = null;
		LOGGER.info("searchEmployees : Begin");
		try {
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token != null) {
				/*Integer loggedInEmployeeId = token.getOsiUserDTO().getId();*/
				List<OsiEmployees> employeeList = osiEmployeesService.searchEmployees(searchData);
			
				if (employeeList != null && employeeList.size() > 0) {
					response = Util.toJsonString(employeeList);
				}/*else {
					throw new BusinessException("ERR_1002","No Employees Found..");
				}*/
			} else {
				throw new BusinessException("ERR_2001", "Invalid Token, Please Login again");
			}
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching the employee");
		}
		LOGGER.info("searchEmployees : End");
		LOGGER.info(" #### successfully search Employees based on the search date");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getAllCountries")
	public ResponseEntity<List<OsiCountriesDTO>> getAllCountries(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		List<OsiCountriesDTO> countryList = null;
		LOGGER.info("getAllCountries : Begin");
		try {
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token == null) {
				throw new BusinessException("ERR_2000", "Invalid Token");
			}
			
			countryList = osiEmployeesService.getAllCountries();
		}  catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the countries");
		}
		LOGGER.info("getAllCountries : End");
		return ResponseEntity.ok().body(countryList);
	}
	
	@GetMapping("/getAllCountryVisasById/{countryId}")
	public ResponseEntity<List<OsiCountryVisasDTO>> getAllCountryVisas(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,@PathVariable("countryId") int countryId) throws RestServiceException {
		List<OsiCountryVisasDTO> countryVisaList = null;
		LOGGER.info("getAllCountryVisas : Begin");
		try {
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token == null) {
				throw new BusinessException("ERR_2000", "Invalid Token");
			}
			
			countryVisaList = osiEmployeesService.getAllCountryVisas(countryId);
		}  catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the all country visas");
		}
		LOGGER.info("getAllCountryVisas : End");
		return ResponseEntity.ok().body(countryVisaList);
	}
	
	@GetMapping("/getPassportInfo/{empId}")
	public ResponseEntity<String> getPassportInfoByEmployeeId(@PathVariable("empId") int empId, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		String response = null;
		LOGGER.info("getPassportInfoByEmployeeId : Begin");
		try {
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token == null)
				throw new BusinessException("ERR_2000", "Invalid Token");
			
			OsiEmployees osiEmployee = osiEmployeesService.getPassportInfoByEmployeeId(empId);
			response = Util.toJsonString(osiEmployee);
		}  catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the passport information");
		}
		LOGGER.info("getPassportInfoByEmployeeId : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/getPassportInfo")
    public ResponseEntity<String> getPassportInfoByEmployeeIdAndDate(@RequestBody String inputObject, 
    																 @Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) 
    																 throws RestServiceException{
    	String response = null;
    	LOGGER.info("getPassportInfoByEmployeeIdAndDate : Begin");
    	OsiEmployees osiEmployee = null;
    	try{
    		AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token == null)
				throw new BusinessException("ERR_2000", "Invalid Token");
			
    		if(null != inputObject) {
    			osiEmployee = osiEmployeesService.getPassportInfoByEmployeeIdAndSearchDate(inputObject);
    			response = Util.toJsonString(osiEmployee);
    		} else {
    			throw new BusinessException("ERR_", "Invalid employeeId or date..");
    		}
    	}  catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the passport information");
		}
		LOGGER.info("getPassportInfoByEmployeeIdAndDate : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	@PostMapping("/savePassportInfo")
	public ResponseEntity<SuccessResponse> savePassportInfo(@RequestBody OsiEmployees osiEmployees, 
			@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		SuccessResponse successResponse = null;
		int result = 0;

		LOGGER.info("savePassportInfo : Begin");
		try {
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			Integer loggedInEmployeeId = token.getOsiUserDTO().getId();
			result = osiEmployeesService.savePassportInfo(osiEmployees, loggedInEmployeeId);			
			if (result > 0) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1008"));
			}
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the passport information");
		}
		LOGGER.info("savePassportInfo : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}
	
	@GetMapping("/personalInfo/{id}")
	public ResponseEntity<String> getPersonalInfo(@PathVariable("id") int id, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		String response = null;
		LOGGER.info("getPersonalInfo : Begin");
		try {
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token == null)
				throw new BusinessException("ERR_2000", "Invalid Token");
			
			response = osiEmployeesService.getPersonalInfoByEmployeeId(id);
			//response = Util.toJsonString(osiEmployee);
		}  catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the personal information");
		}
		LOGGER.info("getPersonalInfo : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/personalInfoByIdAndDate")
	public ResponseEntity<String> getPersonalInfoByIdAndDate(@RequestBody String inputObject, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		String response = null;
		LOGGER.info("getPersonalInfoByIdAndDate : Begin");
		try {
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token == null)
				throw new BusinessException("ERR_2000", "Invalid Token");
			
			response = osiEmployeesService.getPersonalInfoByEmployeeIdAndDate(inputObject);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the personal information");
		}
		LOGGER.info("getPersonalInfoByIdAndDate : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/searchEmployees-self")
	public ResponseEntity<String> searchEmployeesSelf(@RequestBody String searchData, 
			@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		String response = null;
		LOGGER.info("searchEmployeesSelf : Begin");
		try {
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token != null) {
				JSONObject searchObject = new JSONObject(searchData);
				if(null != token.getOsiUserDTO().getId())
					searchObject.put("employeeId", token.getOsiUserDTO().getId());
				else
					throw new BusinessException("ERR_2001", "No employee Id found in Token..");
				
				searchData = searchObject.toString();
				List<OsiEmployees> employeeList = osiEmployeesService.searchEmployeesSelf(searchData);
			
				if (employeeList != null && employeeList.size() > 0) {
					response = Util.toJsonString(employeeList);
				}/*else {
					throw new BusinessException("ERR_1002","No Employees Found..");
				}*/
			} else {
				throw new BusinessException("ERR_2001", "Invalid Token, Please Login again");
			}
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the employee information");
		}
		LOGGER.info("searchEmployeesSelf : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/all/get")
	public String getOrgHierarchyOfEmployees(@RequestBody String orgchart) throws RestServiceException {
		
		String result = "";
		LOGGER.info("getOrgHierarchyOfEmployees : Begin");
		OrgHierarchyDTO orgHierarchyInformation = null;
		try 
		{
			JSONObject jsonObject = new JSONObject(orgchart);

			//OrgHierarchyServiceImpl orgHierarchyServiceImpl = new OrgHierarchyServiceImpl();
			orgHierarchyInformation = orgHierarchyService.getOrgHierarchyEmployees(jsonObject);
			result = Util.toJsonString(orgHierarchyInformation);
			//logger.info("HTTP 200: OK");
			//return new Gson().toJson(orgHierarchyInformation);
		}catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the org hierarchy information");
		}
		LOGGER.info("getOrgHierarchyOfEmployees : End");
		//return new ResponseEntity<String>(result, HttpStatus.OK);
		return result;
		
	}
	
	// Employee Additional Documents
	@GetMapping("/additonaldocs/{employeeId}")
	public ResponseEntity<String> getEmployeeAdditionalDocuments(@PathVariable("employeeId") int employeeId, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		String response = null;
		try {
			LOGGER.info("getEmployeeAdditionalDocuments : Begin");
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token == null)
				throw new BusinessException("ERR_2000", "Invalid Token");
			
			List<OsiEmpAdditionalDocsDTO> osiEmpAdditionalDocsDTOList = osiEmployeesService.findAdditionalDocumentsByEmployeeId(employeeId);
			response = Util.toJsonString(osiEmpAdditionalDocsDTOList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the employee basic information");
		}
		LOGGER.info("getEmployeeAdditionalDocuments : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/saveadditionaldocs")
	ResponseEntity<String> saveAdditionalDocs(@RequestBody String inputObject, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		String response = null;
		LOGGER.info("saveAdditionalDocs : Begin");
		try {
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token == null)
				throw new BusinessException("ERR_2000", "Invalid Token");
			System.out.println(inputObject);
			
			if(null != inputObject) {
				JSONObject empAdditionalDocs = new JSONObject(inputObject);
				OsiEmpAdditionalDocsDTO  empAdditionalDocsDTO = new OsiEmpAdditionalDocsDTO();
				OsiAttachmentsDTO attachmentsDTO = new OsiAttachmentsDTO();
				if(empAdditionalDocs.has("attachmentId"))
					empAdditionalDocsDTO.setAttachmentId(empAdditionalDocs.getInt("attachmentId"));
				if(empAdditionalDocs.has("description"))
					empAdditionalDocsDTO.setDescription(empAdditionalDocs.getString("description"));
				if(empAdditionalDocs.has("docId"))
					empAdditionalDocsDTO.setDocId(empAdditionalDocs.getInt("docId"));
				if(empAdditionalDocs.has("employeeId"))
					empAdditionalDocsDTO.setEmployeeId(empAdditionalDocs.getInt("employeeId"));
				
				if(empAdditionalDocs.has("attachments")) {			
					JSONObject attachments = empAdditionalDocs.getJSONObject("attachments");
					if(attachments.has("attachmentId"))
						attachmentsDTO.setAttachmentId(attachments.getInt("attachmentId"));
					if(attachments.has("fileType")) {
						attachmentsDTO.setFileType((!attachments.getString("fileType").equalsIgnoreCase("image/jpeg")) ? "octet-stream" : attachments.getString("fileType"));
					}
					if(attachments.has("fileContent"))
						attachmentsDTO.setFileContent(attachments.getString("fileContent"));
					if(attachments.has("originalFileName"))
						attachmentsDTO.setOriginalFileName(attachments.getString("originalFileName"));
					if(attachments.has("objectId"))
						attachmentsDTO.setObjectId(attachments.getInt("objectId"));
					if(attachments.has("objectType"))
						attachmentsDTO.setObjectType(attachments.getString("objectType"));
					if(attachments.has("duplicateFileName"))
						attachmentsDTO.setDuplicateFileName(attachments.getString("duplicateFileName"));
					empAdditionalDocsDTO.setAttachments(attachmentsDTO);
				}
				osiEmployeesService.saveAdditionalDocs(empAdditionalDocsDTO);
			}
			//response = osiEmployeesService.getPersonalInfoByEmployeeIdAndDate(inputObject);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the personal information");
		}
		LOGGER.info("saveAdditionalDocs : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/overallSearch")
	public ResponseEntity<String> overallSearch(@RequestBody String searchData, 
			@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		String response = null;
		LOGGER.info("overallSearch : Begin");
		try {
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token != null) {
				List<OsiEmployees> employeeList = osiEmployeesService.overallSearch(searchData);
			
				if (employeeList != null && employeeList.size() > 0) {
					response = Util.toJsonString(employeeList);
				}
			} else {
				throw new BusinessException("ERR_2001", "Invalid Token, Please Login again");
			}
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching the employee");
		}
		LOGGER.info("overallSearch : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("/getAllEmployees")
	public ResponseEntity<String> searchEmployeesWithDateOnlyQB(@RequestBody String input, 
			@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		String response = null;
		LOGGER.info("searchEmployees : Begin");
		try {
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(token != null) {
				List<OsiEmployees> employeeList = osiEmployeesService.getAllEmployees(input);
			
				if (employeeList != null && employeeList.size() > 0) {
					response = Util.toJsonString(employeeList);
				} else {
					response = "No Records Found";
				}
			} else {
				throw new BusinessException("ERR_2001", "Invalid Token, Please Login again");
			}
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching the employee");
		}
		LOGGER.info("searchEmployees : End");
		LOGGER.info(" #### successfully search Employees based on the search date");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/updateEmployees")
	public ResponseEntity<List<OsiEmployees>> updateEmployeesQB(@RequestBody List<OsiEmployees> osiEmployeesList, 
			@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		Integer loggedInEmployeeId = null;
		LOGGER.info("saveEmployeeBasicInfo : Begin");
		try {
			LOGGER.info(" #### Saving basic info in " + " mode ..");
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = token.getOsiUserDTO().getId();
			osiEmployeesList = osiEmployeesService.updateEmployees(osiEmployeesList);				
			
		} catch (BusinessException e) {
			LOGGER.error("Error Occured "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while saving the employee basic information");
		}
		LOGGER.info("saveEmployeeBasicInfo : End");
		LOGGER.info(" #### successfully Saved basic info in " + " mode ..");
		/*return new ResponseEntity<OsiEmployees>(osiEmployees, HttpStatus.OK);*/
		return ResponseEntity.ok().body(osiEmployeesList);
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
