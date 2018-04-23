/**
 * 
 */
package com.osi.ems.web.rest;

import java.util.ArrayList;
import java.util.List;
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

import com.osi.ems.service.ICrudOsiDepratmentService;
import com.osi.ems.service.dto.CrudOsiDepartmentVO;
import com.osi.ems.service.dto.OsiDeptGradesDTO;
import com.osi.ems.utils.CrudOsiBusinessUnitsUtil;
import com.osi.ems.utils.CrudOsiDepratmentUtil;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
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
@RequestMapping("api/crud/osidepratment")
public class CrudOsiDepratmentController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CrudOsiDepratmentController.class);
    
    @Autowired
    ICrudOsiDepratmentService crudOsiDepratmentService;    
    
    
   	@Autowired
   	private Environment env;
   	
    @Autowired
   	private AuthTokenStore authTokenStore;
    
    @RequestMapping(value="/getOsiDepratment/{id}",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getOsiDepratment(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException {
    	String response = null;
    	LOGGER.info("getOsiDepratment : Begin");
    	try {
    		response = CrudOsiDepratmentUtil.toJsonString(crudOsiDepratmentService.getOsiDepratment(id));
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the departments");
		}
    	LOGGER.info("getOsiDepratment : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    //@RequestMapping(value="/createOsiDepratment",method = RequestMethod.POST, produces="application/json")
    @PostMapping("/createOsiDepratment")
    public ResponseEntity<String>  createOsiDepratment(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody CrudOsiDepartmentVO crudOsiDepratmentVO) throws RestServiceException {
    	String response = null;
    	LOGGER.info("createOsiDepratment : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		crudOsiDepratmentVO = crudOsiDepratmentService.createOsiDepratment(crudOsiDepratmentVO,userId);
    		response = CrudOsiBusinessUnitsUtil.toJsonString(crudOsiDepratmentVO);
    		//response = "{\"response\" : \"Saved Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating the departments");
		}
    	LOGGER.info("createOsiDepratment : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    //@RequestMapping(value="/updateOsiDepratment",method = RequestMethod.PUT, produces="application/json")
    @PutMapping("/updateOsiDepratment")
    public ResponseEntity<String>  updateOsiDepratment(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody CrudOsiDepartmentVO crudOsiDepratmentVO) throws DataAccessException,Exception {
    	String response = null;
    	LOGGER.info("updateOsiDepratment : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		crudOsiDepratmentVO = crudOsiDepratmentService.updateOsiDepratment(crudOsiDepratmentVO,userId);
    		crudOsiDepratmentService.deleteOsiBUGradesByDeptID(crudOsiDepratmentVO.getDeptId());
    		response = CrudOsiBusinessUnitsUtil.toJsonString(crudOsiDepratmentVO);
    		//response = "{\"response\" : \"Updated Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while updating the departments");
		}
    	LOGGER.info("updateOsiDepratment : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/deleteOsiDepratment/{id}",method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity<String>  deleteOsiDepratment(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException {
    	String response = null;
    	LOGGER.info("deleteOsiDepratment : Begin");
    	try {
    		crudOsiDepratmentService.deleteOsiDepratment(id);
    		response = "{\"response\" : \"Deleted Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while deleting the departments");
		}
    	LOGGER.info("deleteOsiDepratment : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @PostMapping("/createOsiDeptGrades")
    public ResponseEntity<String>  createOsiBUGrades(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody List<OsiDeptGradesDTO> osiDeptGradesDTOList) throws RestServiceException {
    	String response = null;
    	LOGGER.info("createOsiBUGrades : Begin");
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		Integer userId = auth.getOsiUserDTO().getId();
    		List<OsiDeptGradesDTO> resultList = new ArrayList<OsiDeptGradesDTO> ();
    		
    		
    		for(OsiDeptGradesDTO deptGrade : osiDeptGradesDTOList) {
    			OsiDeptGradesDTO osiBuGradesDTO = crudOsiDepratmentService.createOsiDeptGradesHistoryDuplicate(deptGrade,userId);
    			resultList.add(osiBuGradesDTO);
    		}
    		response = CrudOsiDepratmentUtil.toJsonString(resultList);
    		//response = "{\"response\" : \"Saved Successfully\"}";
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating the BU grades");
		}
    	LOGGER.info("createOsiBUGrades : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/getOsiDeptGradesByDeptId/{id}",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getOsiCCGradesByccId(@RequestHeader Map<String, Object> requestHeader, @PathVariable("id") int id) throws RestServiceException {
    	String response = null;
    	LOGGER.info("getOsiCCGradesByccId : Begin");
    	try {
    		response = CrudOsiDepratmentUtil.toJsonString(crudOsiDepratmentService.getOsiDeptGradesByDeptID(id));
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting dept grades");
		}
    	LOGGER.info("getOsiCCGradesByccId : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/getAllGradesByOrgId/{orgId}",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getAllGradesByOrgId(@RequestHeader Map<String, Object> requestHeader, @PathVariable("orgId") int orgId) throws RestServiceException {
    	LOGGER.info("getAllGradesByOrgId : Begin");
    	String response = null;
    	try {
    		response = CrudOsiDepratmentUtil.toJsonString(crudOsiDepratmentService.getAllGradesByOrganization(orgId));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the all grades");
		}
    	LOGGER.info("getAllGradesByOrgId : End");
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value="/getOsiDeptGradesHistory/{deptId}/{orgId}/{gradeId}",method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<String>  getOsiDeptGradesHistory(@RequestHeader Map<String, Object> requestHeader
    		, @PathVariable("deptId") int deptId, @PathVariable("orgId") int orgId,  @PathVariable("gradeId") int gradeId) throws RestServiceException, Exception {
    	String response = null;
    	LOGGER.info("getOsiGrades : Begin");
    	try {
    		response = CrudOsiBusinessUnitsUtil.toJsonString(crudOsiDepratmentService.getOsiDeptGradesHistory(deptId, orgId, gradeId));
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the DeptGrades History");
		}
    	LOGGER.info("getOsiGrades : End");
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

