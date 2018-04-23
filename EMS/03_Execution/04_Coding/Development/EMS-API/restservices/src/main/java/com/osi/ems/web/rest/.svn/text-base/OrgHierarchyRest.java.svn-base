package com.osi.ems.web.rest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.service.OrgHierarchyService;
import com.osi.ems.service.dto.OrgHierarchyDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;

@RestController
@RequestMapping("/api/orghierarchy")
public class OrgHierarchyRest {
	public static final String max_level = "MAX_LEVEL";
	
	private  static final Logger logger = LoggerFactory.getLogger(OrgHierarchyRest.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private OrgHierarchyService orgHierarchyService;
	
	@PostMapping("/all/get")
	public ResponseEntity<OrgHierarchyDTO>  getOrgHierarchyOfEmployees(@RequestBody String orgchart)  throws RestServiceException {
		logger.info("getOrgHierarchyOfEmployees : Begin");
		OrgHierarchyDTO orgHierarchyInformation = null;
		try 
		{
			JSONObject jsonObject = new JSONObject(orgchart);

			//OrgHierarchyServiceImpl orgHierarchyServiceImpl = new OrgHierarchyServiceImpl();
			orgHierarchyInformation = orgHierarchyService.getOrgHierarchyEmployees(jsonObject);
			logger.info("HTTP 200: OK");
			//return new Gson().toJson(orgHierarchyInformation);
		}catch (BusinessException e) {
			logger.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			logger.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the organization heirarchy");
		}
		logger.info("getOrgHierarchyOfEmployees : End");
		return new ResponseEntity<OrgHierarchyDTO>(orgHierarchyInformation, HttpStatus.OK);
		
	}
	
	@GetMapping("/getEmpOrgList/{employeeId}")
	public ResponseEntity<String> getEmpDashBoard(@PathVariable("employeeId") Integer employeeId)  throws RestServiceException {
		String data = null;
		logger.info("getEmpDashBoard : Begin");
		try {
			//LOGGER.info("OrgListService - getEmpDashBoard method called: empId"+employeeId);
			data = orgHierarchyService.getEmployeeOrgList(employeeId);
			//LOGGER.info("HTTP 200: OK");
			}catch (BusinessException e) {
				logger.error("Error Occured : "+e.getSystemMessage());
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
			}catch (Exception e) {
				logger.error("Error Occured : "+e.getMessage());
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the employee dashboard");
			}
			logger.info("getEmpDashBoard : End");
		return new ResponseEntity<String>(data, HttpStatus.OK);
	}
	@GetMapping("/getLocationList")
	public ResponseEntity<String> getLocationList() throws RestServiceException {
		String data = null;
		logger.info("getLocationList : Begin");
		try {
			//LOGGER.info("OrgListService - getLocationList method called:");
			data = orgHierarchyService.getLocationList();
			//LOGGER.info("HTTP 200: OK");
			}catch (BusinessException e) {
				logger.error("Error Occured : "+e.getSystemMessage());
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
			}catch (Exception e) {
				logger.error("Error Occured : "+e.getMessage());
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the location list");
			}
			logger.info("getLocationList : End");
		return new ResponseEntity<String>(data, HttpStatus.OK);
	}
	/*@PutMapping("/updateEmpOrgList")
	public String updateEmpDashBoard(@QueryParam("empnumber") int empnumber,@QueryParam("filedname") String fieldname,@QueryParam("value") String value) {
		String data = null;
		try {
			LOGGER.info("OrgListService - updateEmpDashBoard method called: empId"+empnumber);
			data = orgHierarchyService.updateEmployeeOrgList(empnumber,fieldname,value);
			LOGGER.info("HTTP 200: OK");
			}
			catch (Exception e)
			{
				LOGGER.error("HTTP 500: INTERNAL SERVER ERROR");
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
			}
			return data.toString();
	}*/
	
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
