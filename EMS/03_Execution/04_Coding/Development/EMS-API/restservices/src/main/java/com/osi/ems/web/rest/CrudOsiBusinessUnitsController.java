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

import com.osi.ems.service.ICrudOsiBusinessUnitsService;
import com.osi.ems.service.dto.CrudOsiBusinessUnitsVO;
import com.osi.ems.service.dto.OsiBuGradesDTO;
import com.osi.ems.utils.CrudOsiBusinessUnitsUtil;
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
@RequestMapping("api/crud/osibusinessunits")
public class CrudOsiBusinessUnitsController {

	public static final Logger LOGGER = LoggerFactory.getLogger(CrudOsiBusinessUnitsController.class);

	@Autowired
	ICrudOsiBusinessUnitsService crudOsiBusinessUnitsService;

	@Autowired
	private Environment env;

	@Autowired
	private AuthTokenStore authTokenStore;

	@RequestMapping(value = "/getOsiBusinessUnits/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getOsiBusinessUnits(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("id") int id) throws RestServiceException {
		LOGGER.info("getOsiBusinessUnits : Begin");
		String response = null;
		try {
			response = CrudOsiBusinessUnitsUtil.toJsonString(crudOsiBusinessUnitsService.getOsiBusinessUnits(id));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting the bussiness units");
		}
		LOGGER.info("getOsiBusinessUnits : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	// @RequestMapping(value="/createOsiBusinessUnits",method = RequestMethod.POST,
	// produces="application/json")
	@PostMapping("/createOsiBusinessUnits")
	public ResponseEntity<String> createOsiBusinessUnits(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO) throws RestServiceException {
		String response = null;
		LOGGER.info("createOsiBusinessUnits : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer userId = auth.getOsiUserDTO().getId();
			crudOsiBusinessUnitsVO = crudOsiBusinessUnitsService.createOsiBusinessUnits(crudOsiBusinessUnitsVO, userId);
			response = CrudOsiBusinessUnitsUtil.toJsonString(crudOsiBusinessUnitsVO);
			// response = "{\"response\" : \"Saved Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while creating the bussiness units");
		}
		LOGGER.info("getOsiBusinessUnits : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	// @RequestMapping(value="/updateOsiBusinessUnits",method = RequestMethod.PUT,
	// produces="application/json")
	@PutMapping("/updateOsiBusinessUnits")
	public ResponseEntity<String> updateOsiBusinessUnits(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody CrudOsiBusinessUnitsVO crudOsiBusinessUnitsVO) throws RestServiceException {
		String response = null;
		LOGGER.info("updateOsiBusinessUnits : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer userId = auth.getOsiUserDTO().getId();
			crudOsiBusinessUnitsVO = crudOsiBusinessUnitsService.updateOsiBusinessUnits(crudOsiBusinessUnitsVO, userId);
			response = CrudOsiBusinessUnitsUtil.toJsonString(crudOsiBusinessUnitsVO);
			 crudOsiBusinessUnitsService.deleteOsiBUGradesByBUID(crudOsiBusinessUnitsVO.getBuId());
			// response = "{\"response\" : \"Updated Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while updating the bussiness units");
		}
		LOGGER.info("updateOsiBusinessUnits : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteOsiBusinessUnits/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> deleteOsiBusinessUnits(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("id") int id) throws RestServiceException {
		String response = null;
		LOGGER.info("deleteOsiBusinessUnits : Begin");
		try {
			crudOsiBusinessUnitsService.deleteOsiBusinessUnits(id);
			response = "{\"response\" : \"Deleted Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while deleting the bussiness units");
		}
		LOGGER.info("deleteOsiBusinessUnits : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PostMapping("/createOsiBUGrades")
	public ResponseEntity<String> createOsiBUGrades(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody List<OsiBuGradesDTO> osiBuGradesDTOList) throws RestServiceException {
		String response = null;
		LOGGER.info("createOsiBUGrades : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer userId = auth.getOsiUserDTO().getId();
			List<OsiBuGradesDTO> resultList = new ArrayList<OsiBuGradesDTO>();

			 //crudOsiBusinessUnitsService.deleteOsiBUGradesByBUID(osiBuGradesDTOList.get(0).getBuId());
			for (OsiBuGradesDTO buGrade : osiBuGradesDTOList) {
				OsiBuGradesDTO osiBuGradesDTO = crudOsiBusinessUnitsService.createOsiBUGradesWithHistoryDuplicate(buGrade, userId);
				resultList.add(osiBuGradesDTO);
			}
			response = CrudOsiBusinessUnitsUtil.toJsonString(resultList);
			// response = "{\"response\" : \"Saved Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while creating the bussiness units");
		}
		LOGGER.info("createOsiBUGrades : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getOsiBUGradesBybuId/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getOsiCCGradesByccId(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("id") int id) throws RestServiceException {
		String response = null;
		LOGGER.info("getOsiCCGradesByccId : Begin");
		try {
			response = CrudOsiBusinessUnitsUtil.toJsonString(crudOsiBusinessUnitsService.getOsiBUGradesByBUID(id));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while creating the bussiness units");
		}
		LOGGER.info("getOsiCCGradesByccId : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllGradesByOrgId/{orgId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getAllGradesByOrgId(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("orgId") int orgId) throws RestServiceException {
		String response = null;
		LOGGER.info("getAllGradesByOrgId : Begin");
		try {
			response = CrudOsiBusinessUnitsUtil
					.toJsonString(crudOsiBusinessUnitsService.getAllGradesByOrganization(orgId));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting the bussiness units");
		}
		LOGGER.info("getAllGradesByOrgId : End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getOsiBUGradesHistory/{buId}/{orgId}/{gradeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getOsiBUGradesHistory(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("buId") int buId, @PathVariable("orgId") int orgId, @PathVariable("gradeId") int gradeId)
			throws RestServiceException, Exception {
		String response = null;
		LOGGER.info("getOsiGrades : Begin");
		try {
			response = CrudOsiBusinessUnitsUtil
					.toJsonString(crudOsiBusinessUnitsService.getOsiBUGradesHistory(buId, orgId, gradeId));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting the BUGrades History");
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
