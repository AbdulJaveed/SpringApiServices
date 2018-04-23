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

import com.osi.ems.service.OsiCertificationGroupsService;
import com.osi.ems.service.dto.OsiCertificationGroupsDTO;
import com.osi.ems.utils.CrudOsiSkilsUtil;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;

@RestController
@RequestMapping("api/osicertificationgroups/")
public class OsiCertificationGroupsController {
	private static final Logger LOGGER = Logger.getLogger(OsiCertificationGroupsController.class);

	@Autowired
	private Environment env;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private OsiCertificationGroupsService osiCertificationgroupService;

	@PostMapping
	public ResponseEntity<String> saveOsiCertificationGroups(
			@RequestBody OsiCertificationGroupsDTO osiCertificationGroupsDto,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		Integer loggedInEmployeeId = null;
		String response = null;
		LOGGER.info("OsiCertificationGroupsController::saveOsiCertificationGroups :: begin");
		try {
			LOGGER.info(" #### Saving osi certificationgroup info ..");
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = token.getOsiUserDTO().getId();
			osiCertificationGroupsDto = this.osiCertificationgroupService
					.saveOsiCertificationGroups(osiCertificationGroupsDto, loggedInEmployeeId);
			if (osiCertificationGroupsDto != null) {
				response = "{\"response\" : \"Certification Group Saved Successfully\"}";
			}
		} catch (BusinessException e) {
			LOGGER.error("Error Occured " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while saving the osi certification group information");
		}

		LOGGER.info("OsiCertificationGroupsController::saveOsiCertificationGroups :: end");
		LOGGER.info(" #### successfully Saved osi certification group ..");

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateOsiCertificationGroups(
			@RequestBody OsiCertificationGroupsDTO osiCertificationGroupsDto,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@PathVariable("id") int osiCertificationGroupId) throws URISyntaxException, RestServiceException {
		LOGGER.debug("REST request to update OsiCertification group : {}");
		LOGGER.info("OsiCertificationGroupsController :: updateOsiCertificationGroups :: Begin");
		SuccessResponse successResponse = null;
		String response = null;
		Integer loggedInEmployeeId = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = auth.getOsiUserDTO().getId();
			osiCertificationGroupsDto.setGroupId(osiCertificationGroupId);
			osiCertificationGroupsDto = this.osiCertificationgroupService
					.updateOsiCertificationGroups(osiCertificationGroupsDto, loggedInEmployeeId);
			if (osiCertificationGroupsDto != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1064"));
				response = "{\"response\" : \"Certification Group Updated Successfully\"}";
			}
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			if (e.getMessage() != null) {
				if (e.getMessage().contains("group_name_UNIQUE")) {
					throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1096"),
							"ERR_1096", "Error occured while updating CertificationGroups");
				}
			}
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while updating CertificationGroups");
		}

		LOGGER.info("OsiCertificationGroupsController :: updateOsiCertificationGroups :: End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<String> getOsiCertificationsGroupList(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		LOGGER.debug("REST request to get all OsiCertification groups");
		LOGGER.info("OsiCertificationGroupsController :: getOsiCertificationsGroupList :: Begin");
		List<OsiCertificationGroupsDTO> osiCertificationGroupsList = null;
		String response = null;
		try {
			osiCertificationGroupsList = this.osiCertificationgroupService.getAllOsiCertificationGroups(null);
			response = CrudOsiSkilsUtil.toJsonString(osiCertificationGroupsList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi Certification groups");
		}

		LOGGER.info("OsiCertificationGroupsController :: getOsiCertificationsGroupList :: End");
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@PostMapping("/list")
	public ResponseEntity<String> getOsiCertificationsGroupListByFilter(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody OsiCertificationGroupsDTO dto)
			throws RestServiceException {
		LOGGER.debug("REST request to get all OsiCertification groups");
		LOGGER.info("OsiCertificationGroupsController :: getOsiCertificationsGroupListByFilter :: Begin");
		List<OsiCertificationGroupsDTO> osiCertificationGroupsList = null;
		String response = null;
		try {
			osiCertificationGroupsList = this.osiCertificationgroupService.getAllOsiCertificationGroups(dto);
			response = CrudOsiSkilsUtil.toJsonString(osiCertificationGroupsList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi Certification groups");
		}

		LOGGER.info("OsiCertificationGroupsController :: getOsiCertificationsGroupListByFilter :: End");
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("/activelist")
	public ResponseEntity<String> getActiveOsiCertificationsGroupListByFilter(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		LOGGER.debug("REST request to get all OsiCertification groups");
		LOGGER.info("OsiCertificationGroupsController :: getActiveOsiCertificationsGroupListByFilter :: Begin");
		List<OsiCertificationGroupsDTO> osiCertificationGroupsList = null;
		String response = null;
		try {
			osiCertificationGroupsList = this.osiCertificationgroupService.getAllActiveOsiCertificationGroups();
			response = CrudOsiSkilsUtil.toJsonString(osiCertificationGroupsList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi Certification groups");
		}

		LOGGER.info("OsiCertificationGroupsController :: getActiveOsiCertificationsGroupListByFilter :: End");
		return ResponseEntity.ok().body(response);

	}

	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<String> getOsiCertificationsGroupById(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		LOGGER.debug("REST request to get osicertificationsgroup by id");
		LOGGER.info("OsiCertificationGroupsController :: getOsiCertificationsGroupById :: Begin");
		OsiCertificationGroupsDTO osiCertificationGroupsDto = null;
		String response = null;
		try {
			osiCertificationGroupsDto = this.osiCertificationgroupService.getOsiCertificationGroupsById(id);
			response = CrudOsiSkilsUtil.toJsonString(osiCertificationGroupsDto);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi certification groups");
		}

		LOGGER.info("OsiCertificationGroupsController :: getOsiCertificationsGroupById : End");

		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOsiCertificationGroups(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@PathVariable("id") int osiCertificationGroupId) throws URISyntaxException, RestServiceException {
		LOGGER.debug("REST request to update OsiCertification group : {}");
		LOGGER.info("OsiCertificationGroupsController :: deleteOsiCertificationGroups :: Begin");
		SuccessResponse successResponse = null;
		Integer loggedInEmployeeId = null;
		OsiCertificationGroupsDTO osiCertificationGroupsDto = null;
		String response = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = auth.getOsiUserDTO().getId();
			osiCertificationGroupsDto = this.osiCertificationgroupService
					.deleteOsiCertificationGroups(osiCertificationGroupId, loggedInEmployeeId);
			if (osiCertificationGroupsDto != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1065"));
			}
			response = "{\"response\" : \"Certification Group Deleted Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while updating osi Osi certification groups");
		}

		LOGGER.info("OsiCertificationGroupsController :: deleteOsiCertificationGroups :: End");
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
