package com.osi.ems.web.rest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.service.OsiSubPracticeService;
import com.osi.ems.service.dto.OsiSubPracticeDto;
import com.osi.ems.service.dto.OsiSubPracticeGradesDto;
import com.osi.ems.utils.CrudOsiBusinessUnitsUtil;
import com.osi.ems.utils.CrudOsiSkilsUtil;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;

@RestController
@RequestMapping("api/osisubpractice")
public class OsiSubPracticeController {
	private static final Logger LOGGER = Logger.getLogger(OsiSubPracticeController.class);

	@Autowired
	private Environment env;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private OsiSubPracticeService osiSubPracticeService;

	@PostMapping
	public ResponseEntity<String> saveosiSubPractice(@RequestBody OsiSubPracticeDto osiSubPracticeDto,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		Integer loggedInEmployeeId = null;
		String response = null;
		LOGGER.info("OsiSubPracticeController::saveosiSubPractice :: begin");
		try {
			LOGGER.info(" #### Saving osi Sub Practice info ..");
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = token.getOsiUserDTO().getId();
			osiSubPracticeDto = this.osiSubPracticeService.saveOsiSubPractice(osiSubPracticeDto, loggedInEmployeeId);
			if (osiSubPracticeDto != null) {
				response = CrudOsiBusinessUnitsUtil.toJsonString(osiSubPracticeDto);
			}
		} catch (BusinessException e) {
			LOGGER.error("Error Occured " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while saving the osisubpractice information");
		}

		LOGGER.info("OsiSubPracticeController::saveosiSubPractice :: end");
		LOGGER.info(" #### successfully Saved OsiSubPractice ..");

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateosiSubPractice(@RequestBody OsiSubPracticeDto osiSubPracticeDto,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable("id") int subPracticeId)
			throws URISyntaxException, RestServiceException {
		LOGGER.debug("REST request to update Osi SubPractice : {}");
		LOGGER.info("osiSubPracticeDtoController :: updateosiSubPractice :: Begin");
		SuccessResponse successResponse = null;
		String response = null;
		Integer loggedInEmployeeId = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = auth.getOsiUserDTO().getId();
			osiSubPracticeDto.setSubPracticeId(subPracticeId);
			osiSubPracticeDto = this.osiSubPracticeService.updateOsiSubPractice(osiSubPracticeDto, loggedInEmployeeId);
			if (osiSubPracticeDto != null) {
				osiSubPracticeService.deleteOsiSubPracticeGradesBysubPracticeId(osiSubPracticeDto.getSubPracticeId());
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1058"));
				response = CrudOsiBusinessUnitsUtil.toJsonString(osiSubPracticeDto);
			}
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			if (e.getMessage() != null) {
				if (e.getMessage().contains("group_name_UNIQUE")) {
					throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1099"),
							"ERR_1099", "Error occured while updating osisubpractice ");
				}
			}
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while updating osi subPractice");
		}

		LOGGER.info("osiSubPracticeDtoController :: updateosiSubPractice :: End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<String> getSubPracticeList(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException {
		LOGGER.debug("REST request to get all OsiSubPractice");
		LOGGER.info("OsiSubPracticeController :: getSubPracticeList :: Begin");
		List<OsiSubPracticeDto> osiSubPracticeDto = null;
		String response = null;
		try {
			osiSubPracticeDto = this.osiSubPracticeService.getAllOsiSubPractice(null);
			response = CrudOsiSkilsUtil.toJsonString(osiSubPracticeDto);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting OsiSubPractice");
		}

		LOGGER.info("osiSubPracticeDtoController :: getSubPracticeList :: End");
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@PostMapping("/list")
	public ResponseEntity<String> getOsiSubPracticeListByFilter(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody OsiSubPracticeDto dto)
			throws RestServiceException {
		LOGGER.debug("REST request to get all OsiSubPractice");
		LOGGER.info("OsiSubPracticeController :: getOsiSubPracticeListByFilter :: Begin");
		List<OsiSubPracticeDto> osiSubPracticeList = null;
		String response = null;
		try {
			osiSubPracticeList = this.osiSubPracticeService.getAllOsiSubPractice(dto);
			response = CrudOsiSkilsUtil.toJsonString(osiSubPracticeList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting OsiSubPractice");
		}

		LOGGER.info("OsiSubPracticeController :: getOsiSubPracticeListByFilter :: End");
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("/activelist")
	public ResponseEntity<String> getActiveOsiSubPracticeListByFilter(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		LOGGER.debug("REST request to get all OsiSubPractice");
		LOGGER.info("osiSubPracticeDtoController :: getActiveOsiSubPracticeListByFilter :: Begin");
		List<OsiSubPracticeDto> osiSubPracticeDtoList = null;
		String response = null;
		try {
			osiSubPracticeDtoList = this.osiSubPracticeService.getAllActiveOsiSubPractice();
			response = CrudOsiSkilsUtil.toJsonString(osiSubPracticeDtoList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting OsiSubPractice");
		}

		LOGGER.info("osiSubPracticeDtoController :: getActiveOsiSubPracticeListByFilter :: End");
		return ResponseEntity.ok().body(response);

	}

	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<String> getOsiSubPracticeById(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		LOGGER.debug("REST request to get getOsiSubPracticeById by id");
		LOGGER.info("osiSubPracticeDtoController :: getOsiSubPracticeById :: Begin");
		OsiSubPracticeDto osiSubPracticeDto = null;
		String response = null;
		try {
			osiSubPracticeDto = this.osiSubPracticeService.getOsiSubPracticeById(id);
			response = CrudOsiSkilsUtil.toJsonString(osiSubPracticeDto);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting OsiSubPractice");
		}

		LOGGER.info("osiSubPracticeDtoController :: getOsiSubPracticeById : End");

		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteosiSubPracticeDto(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@PathVariable("id") int osiSubPracticeId) throws URISyntaxException, RestServiceException {
		LOGGER.debug("REST request to update OsiSubPractice : {}");
		LOGGER.info("osiSubPracticeDtoController :: deleteosiSubPracticeDto :: Begin");
		SuccessResponse successResponse = null;
		Integer loggedInEmployeeId = null;
		OsiSubPracticeDto osiSubPracticeDto = null;
		String response = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = auth.getOsiUserDTO().getId();
			osiSubPracticeDto = this.osiSubPracticeService.deleteOsiSubPractice(osiSubPracticeId, loggedInEmployeeId);
			if (osiSubPracticeDto != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1059"));
			}
			response = "{\"response\" : \"Osi SubPractice Deleted Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while updating osi OsiSubPractice");
		}

		LOGGER.info("osiSubPracticeDtoController :: deleteosiSubPracticeDto :: End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PostMapping("/createOsiSubPracticeGrades")
	public ResponseEntity<String> createOsiSubPracticeGrades(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody List<OsiSubPracticeGradesDto> osiSubPracticeGradesDTOList) throws RestServiceException {
		String response = null;
		LOGGER.info("osiSubPracticeDtoController :: createOsiSubPracticeGrades :: : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer userId = auth.getOsiUserDTO().getId();
			List<OsiSubPracticeGradesDto> resultList = new ArrayList<OsiSubPracticeGradesDto>();
			//existingGradesList = this.osiSubPracticeService.getOsiSubPracticeGradesBysubPracticeId(subPracticeId);
			//osiSubPracticeService.deleteOsiSubPracticeGradesBysubPracticeId(osiSubPracticeGradesDTOList.get(0).getSubPracticeId());
			for (OsiSubPracticeGradesDto subPracticeGrade : osiSubPracticeGradesDTOList) {
				OsiSubPracticeGradesDto osiBuGradesDTO = this.osiSubPracticeService
						.createOsiSubPracticesGradesWithHistoryDuplicate(subPracticeGrade, userId);
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
		LOGGER.info("osiSubPracticeDtoController :: createOsiSubPracticeGrades End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getOsiSubPracticeGradesBysubPracticeId/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getOsiCCGradesByccId(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("id") int id) throws RestServiceException {
		String response = null;
		LOGGER.info("osiSubPracticeDtoController :: createOsiSubPracticeGrades Begin");
		try {
			response = CrudOsiBusinessUnitsUtil
					.toJsonString(osiSubPracticeService.getOsiSubPracticeGradesBysubPracticeId(id));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while creating the bussiness units");
		}
		LOGGER.info("osiSubPracticeDtoController :: createOsiSubPracticeGrades End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getOsiSubPracticeGradesHistory/{subPracticeId}/{orgId}/{gradeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getOsiSubPracticeGradesHistory(@RequestHeader Map<String, Object> requestHeader,
			@PathVariable("subPracticeId") int subPracticeId, @PathVariable("orgId") int orgId,
			@PathVariable("gradeId") int gradeId) throws RestServiceException, Exception {
		String response = null;
		LOGGER.info("getOsiGrades : Begin");
		try {
			response = CrudOsiBusinessUnitsUtil
					.toJsonString(osiSubPracticeService.getOsiSubPracticeGradesHistory(subPracticeId, orgId, gradeId));
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting the BUGrades History");
		}
		LOGGER.info("osiSubPracticeDtoController :: createOsiSubPracticeGrades End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	/* Method for handling the exception */
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
