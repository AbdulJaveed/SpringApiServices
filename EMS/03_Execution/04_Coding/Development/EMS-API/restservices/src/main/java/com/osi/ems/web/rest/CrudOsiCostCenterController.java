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

import com.osi.ems.service.ICrudOsiCostCenterService;
import com.osi.ems.service.dto.CrudOsiCostCenterVO;
import com.osi.ems.service.dto.OsiCcGradesDTO;
import com.osi.ems.utils.CrudOsiCertificationsUtil;
import com.osi.ems.utils.CrudOsiCostCenterUtil;
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
@RequestMapping("api/crud/osicostcenter")
public class CrudOsiCostCenterController {

	public static final Logger LOGGER = LoggerFactory.getLogger(CrudOsiCostCenterController.class);

	@Autowired
	ICrudOsiCostCenterService crudOsiCostCenterService;

	@Autowired
	private Environment env;

	@Autowired
	private AuthTokenStore authTokenStore;

	@RequestMapping(value = "/getOsiCostCenter/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getOsiCostCenter(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("id") int id) throws RestServiceException {
		String response = null;
		LOGGER.info("getOsiCostCenter : Begin");
		try {
			response = CrudOsiCostCenterUtil.toJsonString(crudOsiCostCenterService.getOsiCostCenter(id));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting the osi cost center");
		}
		LOGGER.info("getOsiCostCenter : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	// @RequestMapping(value="/createOsiCostCenter",method = RequestMethod.POST,
	// produces="application/json")
	@PostMapping("/createOsiCostCenter")
	public ResponseEntity<String> createOsiCostCenter(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody CrudOsiCostCenterVO crudOsiCostCenterVO) throws RestServiceException {
		String response = null;
		LOGGER.info("createOsiCostCenter : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer userId = auth.getOsiUserDTO().getId();
			crudOsiCostCenterVO = crudOsiCostCenterService.createOsiCostCenter(crudOsiCostCenterVO, userId);
			response = CrudOsiCertificationsUtil.toJsonString(crudOsiCostCenterVO);
			// response = "{\"response\" : \"Saved Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while saving the osi cost center");
		}
		LOGGER.info("createOsiCostCenter : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	// @RequestMapping(value="/updateOsiCostCenter",method = RequestMethod.PUT,
	// produces="application/json")
	@PutMapping("/updateOsiCostCenter")
	public ResponseEntity<String> updateOsiCostCenter(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody CrudOsiCostCenterVO crudOsiCostCenterVO) throws RestServiceException {
		String response = null;
		LOGGER.info("updateOsiCostCenter : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer userId = auth.getOsiUserDTO().getId();
			crudOsiCostCenterVO = crudOsiCostCenterService.updateOsiCostCenter(crudOsiCostCenterVO, userId);
			response = CrudOsiCertificationsUtil.toJsonString(crudOsiCostCenterVO);
			crudOsiCostCenterService.deleteOsiCCGradesByCCID(crudOsiCostCenterVO.getCcId());
			// response = "{\"response\" : \"Updated Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while updating the osi cost center");
		}
		LOGGER.info("updateOsiCostCenter : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteOsiCostCenter/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> deleteOsiCostCenter(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("id") int id) throws RestServiceException {
		String response = null;
		LOGGER.info("deleteOsiCostCenter : Begin");
		try {
			crudOsiCostCenterService.deleteOsiCostCenter(id);
			response = "{\"response\" : \"Deleted Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while deleting the osi cost center");
		}
		LOGGER.info("deleteOsiCostCenter : End");
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

	@RequestMapping(value = "/getOsiCCGrades/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getOsiCCGrades(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("id") int id) throws RestServiceException {
		String response = null;
		LOGGER.info("getOsiCCGrades : Begin");
		try {
			response = CrudOsiCostCenterUtil.toJsonString(crudOsiCostCenterService.getOsiCCGrades(id));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting the osi cc grades");
		}
		LOGGER.info("getOsiCCGrades : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PostMapping("/createOsiCCGrades")
	public ResponseEntity<String> createOsiCCGrades(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody List<OsiCcGradesDTO> osiCcGradesDTOList) throws RestServiceException {
		String response = null;
		LOGGER.info("createOsiCCGrades : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer userId = auth.getOsiUserDTO().getId();
			List<OsiCcGradesDTO> resultList = new ArrayList<OsiCcGradesDTO>();

			// crudOsiCostCenterService.deleteOsiCCGradesByCCID(osiCcGradesDTOList.get(0).getCcId());
			for (OsiCcGradesDTO grade : osiCcGradesDTOList) {
				OsiCcGradesDTO osiCcGradesDTO = crudOsiCostCenterService.createOsiCCGradesHistoryDuplicate(grade,
						userId);
				resultList.add(osiCcGradesDTO);
			}
			response = CrudOsiCostCenterUtil.toJsonString(resultList);
			// response = "{\"response\" : \"Saved Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while creating the osi cc grades");
		}
		LOGGER.info("createOsiCCGrades : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PutMapping("/updateOsiCCGrades")
	public ResponseEntity<String> updateOsiCCGrades(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiCcGradesDTO osiCcGradesDTO) throws RestServiceException {
		String response = null;
		LOGGER.info("updateOsiCCGrades : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer userId = auth.getOsiUserDTO().getId();
			osiCcGradesDTO = crudOsiCostCenterService.updateOsiCCGrades(osiCcGradesDTO, userId);

			response = "{\"response\" : \"Updated Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while updating the osi cc grades");
		}
		LOGGER.info("updateOsiCCGrades : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteOsiCCGrades/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> deleteOsiCCGrades(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("id") int id) throws RestServiceException {
		String response = null;
		LOGGER.info("deleteOsiCCGrades : Begin");
		try {
			crudOsiCostCenterService.deleteOsiCCGrades(id);
			response = "{\"response\" : \"Deleted Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while deleting the osi cc grades");
		}
		LOGGER.info("deleteOsiCCGrades : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getOsiCCGradesByccId/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getOsiCCGradesByccId(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("id") int id) throws RestServiceException {
		LOGGER.info("getOsiCCGradesByccId : Begin");
		String response = null;
		try {
			response = CrudOsiCostCenterUtil.toJsonString(crudOsiCostCenterService.getOsiCCGradesByCCID(id));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting the osi cc grades");
		}
		LOGGER.info("getOsiCCGradesByccId : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllGradesByOrgId/{orgId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getAllGradesByOrgId(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("orgId") int orgId) throws RestServiceException {
		LOGGER.info("getAllGradesByOrgId : Begin");
		String response = null;
		try {
			response = CrudOsiCostCenterUtil.toJsonString(crudOsiCostCenterService.getAllGradesByOrganization(orgId));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting the all grades");
		}
		LOGGER.info("getAllGradesByOrgId : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getOsiCCGradesHistory/{ccId}/{orgId}/{gradeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getOsiCCGradesHistoryByccId(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("ccId") int ccId, @PathVariable("orgId") int orgId, @PathVariable("gradeId") int gradeId)
			throws RestServiceException, Exception {
		String response = null;
		LOGGER.info("getOsiGrades : Begin");
		try {
			response = CrudOsiCostCenterUtil
					.toJsonString(crudOsiCostCenterService.getOsiCCGradesHistory(ccId, orgId, gradeId));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting the grades");
		}
		LOGGER.info("getOsiGrades : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
