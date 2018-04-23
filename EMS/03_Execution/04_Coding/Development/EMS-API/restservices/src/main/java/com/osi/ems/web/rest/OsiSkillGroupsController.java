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

import com.osi.ems.service.OsiSkillGroupsService;
import com.osi.ems.service.dto.OsiSkillGroupsDTO;
import com.osi.ems.utils.CrudOsiSkilsUtil;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;

@RestController
@RequestMapping("api/osiskillgroups/")
public class OsiSkillGroupsController {
	private static final Logger LOGGER = Logger.getLogger(OsiSkillGroupsController.class);

	@Autowired
	private Environment env;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private OsiSkillGroupsService osiSkillgroupService;

	@PostMapping
	public ResponseEntity<String> saveOsiSkillGroups(@RequestBody OsiSkillGroupsDTO osiSkillGroupsDto,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		Integer loggedInEmployeeId = null;
		String response = null;
		LOGGER.info("OsiSkillGroupsController::saveOsiSkillGroups :: begin");
		try {
			LOGGER.info(" #### Saving osi skillgroup info ..");
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = token.getOsiUserDTO().getId();
			osiSkillGroupsDto = this.osiSkillgroupService.saveOsiSkillGroups(osiSkillGroupsDto, loggedInEmployeeId);
			if (osiSkillGroupsDto != null) {
				response = "{\"response\" : \"Skill Group Saved Successfully\"}";
			}
		} catch (BusinessException e) {
			LOGGER.error("Error Occured " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while saving the osi skill group information");
		}

		LOGGER.info("OsiSkillGroupsController::saveOsiSkillGroups :: end");
		LOGGER.info(" #### successfully Saved osi skill group ..");

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateOsiSkillGroups(@RequestBody OsiSkillGroupsDTO osiSkillGroupsDto,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable("id") int osiSkillGroupId)
			throws URISyntaxException, RestServiceException {
		LOGGER.debug("REST request to update OsiSkill group : {}");
		LOGGER.info("OsiSkillGroupsController :: updateOsiSkillGroups :: Begin");
		SuccessResponse successResponse = null;
		String response = null;
		Integer loggedInEmployeeId = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = auth.getOsiUserDTO().getId();
			osiSkillGroupsDto.setGroupId(osiSkillGroupId);
			osiSkillGroupsDto = this.osiSkillgroupService.updateOsiSkillGroups(osiSkillGroupsDto, loggedInEmployeeId);
			if (osiSkillGroupsDto != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1058"));
				response = "{\"response\" : \"Skill Group Updated Successfully\"}";
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
							"ERR_1099", "Error occured while updating SkillGroups");
				}
			}
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while updating SkillGroups");
		}

		LOGGER.info("OsiSkillGroupsController :: updateOsiSkillGroups :: End");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<String> getOsiSkillsGroupList(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException {
		LOGGER.debug("REST request to get all OsiSkill groups");
		LOGGER.info("OsiSkillGroupsController :: getOsiSkillsGroupList :: Begin");
		List<OsiSkillGroupsDTO> osiSkillGroupsList = null;
		String response = null;
		try {
			osiSkillGroupsList = this.osiSkillgroupService.getAllOsiSkillGroups(null);
			response = CrudOsiSkilsUtil.toJsonString(osiSkillGroupsList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi Skill groups");
		}

		LOGGER.info("OsiSkillGroupsController :: getOsiSkillsGroupList :: End");
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@PostMapping("/list")
	public ResponseEntity<String> getOsiSkillsGroupListByFilter(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody OsiSkillGroupsDTO dto)
			throws RestServiceException {
		LOGGER.debug("REST request to get all OsiSkill groups");
		LOGGER.info("OsiSkillGroupsController :: getOsiSkillsGroupListByFilter :: Begin");
		List<OsiSkillGroupsDTO> osiSkillGroupsList = null;
		String response = null;
		try {
			osiSkillGroupsList = this.osiSkillgroupService.getAllOsiSkillGroups(dto);
			response = CrudOsiSkilsUtil.toJsonString(osiSkillGroupsList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi Skill groups");
		}

		LOGGER.info("OsiSkillGroupsController :: getOsiSkillsGroupListByFilter :: End");
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("activelist")
	public ResponseEntity<String> getActiveOsiSkillsGroupListByFilter(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException {
		LOGGER.debug("REST request to get all OsiSkill groups");
		LOGGER.info("OsiSkillGroupsController :: getActiveOsiSkillsGroupListByFilter :: Begin");
		List<OsiSkillGroupsDTO> osiSkillGroupsList = null;
		String response = null;
		try {
			osiSkillGroupsList = this.osiSkillgroupService.getAllActiveOsiSkillGroups();
			response = CrudOsiSkilsUtil.toJsonString(osiSkillGroupsList);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi Skill groups");
		}

		LOGGER.info("OsiSkillGroupsController :: getActiveOsiSkillsGroupListByFilter :: End");
		return ResponseEntity.ok().body(response);

	}

	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<String> getOsiSkillsGroupById(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		LOGGER.debug("REST request to get osiskillsgroup by id");
		LOGGER.info("OsiSkillGroupsController :: getOsiSkillsGroupById :: Begin");
		OsiSkillGroupsDTO osiSkillGroupsDto = null;
		String response = null;
		try {
			osiSkillGroupsDto = this.osiSkillgroupService.getOsiSkillGroupsById(id);
			response = CrudOsiSkilsUtil.toJsonString(osiSkillGroupsDto);
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while getting Osi skill groups");
		}

		LOGGER.info("OsiSkillGroupsController :: getOsiSkillsGroupById : End");

		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOsiSkillGroups(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@PathVariable("id") int osiSkillGroupId) throws URISyntaxException, RestServiceException {
		LOGGER.debug("REST request to update OsiSkill group : {}");
		LOGGER.info("OsiSkillGroupsController :: deleteOsiSkillGroups :: Begin");
		SuccessResponse successResponse = null;
		Integer loggedInEmployeeId = null;
		OsiSkillGroupsDTO osiSkillGroupsDto = null;
		String response = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			loggedInEmployeeId = auth.getOsiUserDTO().getId();
			osiSkillGroupsDto = this.osiSkillgroupService.deleteOsiSkillGroups(osiSkillGroupId, loggedInEmployeeId);
			if (osiSkillGroupsDto != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1059"));
			}
			response = "{\"response\" : \"Skill Group Deleted Successfully\"}";
		} catch (BusinessException e) {
			LOGGER.error("Error Occured : " + e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty("ERR_1000"), "ERR_1000",
					"Error occured while updating osi Osi skill groups");
		}

		LOGGER.info("OsiSkillGroupsController :: deleteOsiSkillGroups :: End");
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
