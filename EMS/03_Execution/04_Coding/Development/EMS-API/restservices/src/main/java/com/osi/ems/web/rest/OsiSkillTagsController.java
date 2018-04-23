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

import com.osi.ems.service.OsiSkillTagsService;
import com.osi.ems.service.dto.OsiSkillTagsDto;
import com.osi.ems.utils.CrudOsiSkilsUtil;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;

@RestController
@RequestMapping("api/osiskilltags/")
public class OsiSkillTagsController {
	private static final Logger LOGGER = Logger.getLogger(OsiSkillTagsController.class);

	@Autowired
	private Environment env;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private OsiSkillTagsService osiSkillTagsService;

	@PostMapping
	public ResponseEntity<String> saveOsiSkillTags(@RequestBody OsiSkillTagsDto osiSkillTagsDto,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		Integer loggedInEmployeeId = null;
		SuccessResponse successResponse = null;
		String response = null;
		LOGGER.info("OsiSkillTagsController::saveOsiSkillTags :: begin");
		try {
			LOGGER.info(" #### Saving osi skillgroup info ..");
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = token.getOsiUserDTO().getId();
			osiSkillTagsDto = this.osiSkillTagsService.saveOsiSkillTags(osiSkillTagsDto, loggedInEmployeeId);
			if (osiSkillTagsDto != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1060"));
			}
			response = "{\"response\" : \"Skill Tags Saved Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while saving the osi skill group information");
		}

		LOGGER.info("OsiSkillTagsController::saveOsiSkillTags :: end");
		LOGGER.info(" #### successfully Saved osi skill group ..");

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateOsiSkillTags(@RequestBody OsiSkillTagsDto osiSkillTagsDto,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable("id") int osiSkillTagId)
			throws URISyntaxException, RestServiceException {
		LOGGER.debug("REST request to update OsiSkill group : {}");
		LOGGER.info("OsiSkillTagsController :: updateOsiSkillTags :: Begin");
		SuccessResponse successResponse = null;
		String response = null;
		Integer loggedInEmployeeId = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = auth.getOsiUserDTO().getId();
			osiSkillTagsDto.setTagId(osiSkillTagId);
			osiSkillTagsDto = this.osiSkillTagsService.updateOsiSkillTags(osiSkillTagsDto, loggedInEmployeeId);
			if (osiSkillTagsDto != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1061"));
			}
			response = "{\"response\" : \"Skill Tags Updated Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			if (e.getMessage() != null) {
				if (e.getMessage().contains("tag_name_UNIQUE")) {
					throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1098"),
							"ERR_1098", "Error occured while updating Skill Tags");
				}
			}
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while updating osi contacts");
		}

		LOGGER.info("OsiSkillTagsController :: updateOsiSkillTags :: End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping("/activelist")
	public ResponseEntity<String> getActiveOsiSkillsTagsList(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		LOGGER.debug("REST request to get all OsiSkill groups");
		LOGGER.info("OsiSkillTagsController :: getActiveOsiSkillsTagsList :: Begin");
		List<OsiSkillTagsDto> osiSkillTagsList = null;
		String response = null;
		try {
			osiSkillTagsList = this.osiSkillTagsService.getAllActiveOsiSkillTags();
			response = CrudOsiSkilsUtil.toJsonString(osiSkillTagsList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi Skill groups");
		}

		LOGGER.info("OsiSkillTagsController :: getActiveOsiSkillsTagsList :: End");
		return ResponseEntity.ok().body(response);

	}

	@GetMapping
	public ResponseEntity<String> getOsiSkillsTagsList(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException {
		LOGGER.debug("REST request to get all OsiSkill groups");
		LOGGER.info("OsiSkillTagsController :: getOsiSkillsTagsList :: Begin");
		List<OsiSkillTagsDto> osiSkillTagsList = null;
		String response = null;
		try {
			osiSkillTagsList = this.osiSkillTagsService.getAllOsiSkillTags(null);
			response = CrudOsiSkilsUtil.toJsonString(osiSkillTagsList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi Skill groups");
		}

		LOGGER.info("OsiSkillTagsController :: getOsiSkillsTagsList :: End");
		return ResponseEntity.ok().body(response);

	}

	@PostMapping("/list")
	public ResponseEntity<String> getOsiSkillsTagsListByFilter(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody OsiSkillTagsDto dto)
			throws RestServiceException {
		LOGGER.debug("REST request to get all OsiSkill groups");
		LOGGER.info("OsiSkillTagsController :: getOsiSkillsTagsListByFilter :: Begin");
		List<OsiSkillTagsDto> osiSkillTagsList = null;
		String response = null;
		try {
			osiSkillTagsList = this.osiSkillTagsService.getAllOsiSkillTags(dto);
			response = CrudOsiSkilsUtil.toJsonString(osiSkillTagsList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi Skill groups");
		}

		LOGGER.info("OsiSkillTagsController :: getOsiSkillsTagsListByFilter :: End");
		return ResponseEntity.ok().body(response);

	}

	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<String> getOsiSkillsTagsById(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		LOGGER.debug("REST request to get osiskillsgroup by id");
		LOGGER.info("OsiSkillTagsController :: getOsiSkillsTagsById :: Begin");
		OsiSkillTagsDto osiSkillTagsDto = null;
		String response = null;
		try {
			osiSkillTagsDto = this.osiSkillTagsService.getOsiSkillTagsById(id);
			response = CrudOsiSkilsUtil.toJsonString(osiSkillTagsDto);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting osi contacts");
		}

		LOGGER.info("OsiSkillTagsController :: getOsiSkillsTagsById : End");

		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOsiSkillTags(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@PathVariable("id") int osiSkillTagId) throws URISyntaxException, RestServiceException {
		LOGGER.debug("REST request to delete OsiSkill group : {}");
		LOGGER.info("OsiSkillTagsController :: deleteOsiSkillTags :: Begin");
		SuccessResponse successResponse = null;
		OsiSkillTagsDto osiSkillTagsDto = null;
		Integer loggedInEmployeeId = null;
		String response = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = auth.getOsiUserDTO().getId();

			osiSkillTagsDto = this.osiSkillTagsService.deleteOsiSkillTags(osiSkillTagId, loggedInEmployeeId);
			if (osiSkillTagsDto != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1062"));
			}
			response = "{\"response\" : \"Skill Tags Deleted Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while deleting osi contacts");
		}

		LOGGER.info("OsiSkillTagsController :: deleteOsiSkillTags :: End");
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
