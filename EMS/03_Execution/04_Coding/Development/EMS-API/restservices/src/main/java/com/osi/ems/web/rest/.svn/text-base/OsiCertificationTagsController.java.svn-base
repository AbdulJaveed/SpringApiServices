package com.osi.ems.web.rest;

import java.net.URISyntaxException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.service.OsiCertificationTagsService;
import com.osi.ems.service.dto.OsiCertificationTagsDto;
import com.osi.ems.utils.CrudOsiSkilsUtil;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;

@RestController
@RequestMapping("api/osicertificationtags/")
public class OsiCertificationTagsController {
	private static final Logger LOGGER = Logger.getLogger(OsiCertificationTagsController.class);

	@Autowired
	private Environment env;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private OsiCertificationTagsService osiCertificationTagsService;

	@PostMapping
	public ResponseEntity<String> saveOsiCertificationTags(@RequestBody OsiCertificationTagsDto osiCertificationTagsDto,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		Integer loggedInEmployeeId = null;
		SuccessResponse successResponse = null;
		String response = null;
		LOGGER.info("OsiCertificationTagsController::saveOsiCertificationTags :: begin");
		try {
			LOGGER.info(" #### Saving osi certificationgroup info ..");
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = token.getOsiUserDTO().getId();
			osiCertificationTagsDto = this.osiCertificationTagsService.saveOsiCertificationTags(osiCertificationTagsDto,
					loggedInEmployeeId);
			if (osiCertificationTagsDto != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1060"));
			}
			response = "{\"response\" : \"Certification Tags Saved Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while saving the osi certification group information");
		}

		LOGGER.info("OsiCertificationTagsController::saveOsiCertificationTags :: end");
		LOGGER.info(" #### successfully Saved osi certification group ..");

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateOsiCertificationTags(
			@RequestBody OsiCertificationTagsDto osiCertificationTagsDto,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@PathVariable("id") int osiCertificationTagId) throws URISyntaxException, RestServiceException {
		LOGGER.debug("REST request to update OsiCertification group : {}");
		LOGGER.info("OsiCertificationTagsController :: updateOsiCertificationTags :: Begin");
		SuccessResponse successResponse = null;
		String response = null;
		Integer loggedInEmployeeId = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = auth.getOsiUserDTO().getId();
			osiCertificationTagsDto.setTagId(osiCertificationTagId);
			osiCertificationTagsDto = this.osiCertificationTagsService
					.updateOsiCertificationTags(osiCertificationTagsDto, loggedInEmployeeId);
			if (osiCertificationTagsDto != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1061"));
			}
			response = "{\"response\" : \"Certification Tags Updated Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			if (e.getMessage() != null) {
				if (e.getMessage().contains("tag_name_UNIQUE")) {
					throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1098"),
							"ERR_1098", "Error occured while updating Certification Tags");
				}
			}
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while updating osi contacts");
		}

		LOGGER.info("OsiCertificationTagsController :: updateOsiCertificationTags :: End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<String> getOsiCertificationsTagsList(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		LOGGER.debug("REST request to get all OsiCertification groups");
		LOGGER.info("OsiCertificationTagsController :: getOsiCertificationsTagsList :: Begin");
		List<OsiCertificationTagsDto> osiCertificationTagsList = null;
		String response = null;
		try {
			osiCertificationTagsList = this.osiCertificationTagsService.getAllOsiCertificationTags(null);
			response = CrudOsiSkilsUtil.toJsonString(osiCertificationTagsList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi Certification groups");
		}

		LOGGER.info("OsiCertificationTagsController :: getOsiCertificationsTagsList :: End");
		return ResponseEntity.ok().body(response);

	}

	@PostMapping("/list")
	public ResponseEntity<String> getOsiCertificationsTagsListByFilter(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody OsiCertificationTagsDto dto)
			throws RestServiceException {
		LOGGER.debug("REST request to get all OsiCertification groups");
		LOGGER.info("OsiCertificationTagsController :: getOsiCertificationsTagsListByFilter :: Begin");
		List<OsiCertificationTagsDto> osiCertificationTagsList = null;
		String response = null;
		try {
			osiCertificationTagsList = this.osiCertificationTagsService.getAllOsiCertificationTags(dto);
			response = CrudOsiSkilsUtil.toJsonString(osiCertificationTagsList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi Certification groups");
		}

		LOGGER.info("OsiCertificationTagsController :: getOsiCertificationsTagsListByFilter :: End");
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("/activelist")
	public ResponseEntity<String> getActiveOsiCertificationsTagsListByFilter(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		LOGGER.debug("REST request to get all OsiCertification groups");
		LOGGER.info("OsiCertificationTagsController :: getActiveOsiCertificationsTagsListByFilter :: Begin");
		List<OsiCertificationTagsDto> osiCertificationTagsList = null;
		String response = null;
		try {
			osiCertificationTagsList = this.osiCertificationTagsService.getAllActiveOsiCertificationTags();
			response = CrudOsiSkilsUtil.toJsonString(osiCertificationTagsList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi Certification groups");
		}

		LOGGER.info("OsiCertificationTagsController :: getActiveOsiCertificationsTagsListByFilter :: End");
		return ResponseEntity.ok().body(response);

	}

	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<String> getOsiCertificationsTagsById(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		LOGGER.debug("REST request to get osicertificationsgroup by id");
		LOGGER.info("OsiCertificationTagsController :: getOsiCertificationsTagsById :: Begin");
		OsiCertificationTagsDto osiCertificationTagsDto = null;
		String response = null;
		try {
			osiCertificationTagsDto = this.osiCertificationTagsService.getOsiCertificationTagsById(id);
			response = CrudOsiSkilsUtil.toJsonString(osiCertificationTagsDto);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting osi contacts");
		}

		LOGGER.info("OsiCertificationTagsController :: getOsiCertificationsTagsById : End");

		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOsiCertificationTags(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@PathVariable("id") int osiCertificationTagId) throws URISyntaxException, RestServiceException {
		LOGGER.debug("REST request to delete OsiCertification group : {}");
		LOGGER.info("OsiCertificationTagsController :: deleteOsiCertificationTags :: Begin");
		SuccessResponse successResponse = null;
		OsiCertificationTagsDto osiCertificationTagsDto = null;
		Integer loggedInEmployeeId = null;
		String response = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = auth.getOsiUserDTO().getId();

			osiCertificationTagsDto = this.osiCertificationTagsService.deleteOsiCertificationTags(osiCertificationTagId,
					loggedInEmployeeId);
			if (osiCertificationTagsDto != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1062"));
			}
			response = "{\"response\" : \"Certification Tags Deleted Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while deleting osi contacts");
		}

		LOGGER.info("OsiCertificationTagsController :: deleteOsiCertificationTags :: End");
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
