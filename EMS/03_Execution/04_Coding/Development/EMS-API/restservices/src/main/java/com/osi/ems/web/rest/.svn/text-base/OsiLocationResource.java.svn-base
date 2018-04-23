package com.osi.ems.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.service.OsiLocationService;
import com.osi.ems.service.dto.OsiLocationsDTO;
import com.osi.ems.service.dto.OsiRegionsDTO;
import com.osi.ems.service.dto.OsiTimezonesDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;

@RestController
@RequestMapping("/api")
public class OsiLocationResource {

	private final Logger log = LoggerFactory.getLogger(OsiLocationResource.class);

	@Autowired
	private OsiLocationService osiLocationService;

	@Autowired
	private Environment env;

	@Autowired
	private AuthTokenStore authTokenStore;

	@PostMapping("/locations")
	public ResponseEntity<SuccessResponse> createOsiLocation(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiLocationsDTO osiLocationsDTO) throws RestServiceException {

		SuccessResponse successResponse = null;
		log.info("createOsiLocation : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer userId = auth.getOsiUserDTO().getId();
			osiLocationsDTO = osiLocationService.createLocation(osiLocationsDTO, userId);
			if (osiLocationsDTO != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(osiLocationsDTO.getLocationId().toString());
			}

		} catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (DataAccessException e) {
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), e.getSystemMessage(),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating the osi location");
		}
		log.info("createOsiLocation : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}

	@GetMapping("/locations/{orgId}")
	public ResponseEntity<List<OsiLocationsDTO>> getOsiLocationByOrgId(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable("orgId") int orgId)
			throws Exception {
		log.info("getOsiLocationByOrgId : Begin");
		List<OsiLocationsDTO> result = new ArrayList<OsiLocationsDTO>();
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			result = osiLocationService.locationbyOrgId(orgId);

		} catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (DataAccessException e) {
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), e.getSystemMessage(),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the osi location");
		}
		log.info("getOsiLocationByOrgId : End");

		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/locationsById/{locId}")
	public ResponseEntity getOsiLocationByLocId(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@PathVariable("locId") int locId) throws Exception {
		OsiLocationsDTO result = new OsiLocationsDTO();
		log.info("getOsiLocationByLocId : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			result = osiLocationService.locationbyLocId(locId);

		} catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (DataAccessException e) {
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), e.getSystemMessage(),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the osi location");
		}
		log.info("getOsiLocationByLocId : End");

		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/regions")
	public ResponseEntity<List<OsiRegionsDTO>> getRegionInfo(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws Exception {
		List<OsiRegionsDTO> result = new ArrayList<OsiRegionsDTO>();
		log.info("getRegionInfo : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			result = osiLocationService.getRegionInfo();
		} catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (DataAccessException e) {
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), e.getSystemMessage(),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the region information");
		}
		log.info("getRegionInfo : End");
		return ResponseEntity.ok().body(result);

	}
	
	@GetMapping("/timezones")
	public ResponseEntity<List<OsiTimezonesDTO>> getTimezoneInfo(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws Exception {
		List<OsiTimezonesDTO> result = new ArrayList<OsiTimezonesDTO>();
		log.info("getTimezoneInfo : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			result = osiLocationService.getTimezoneInfo();
		} catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (DataAccessException e) {
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), e.getSystemMessage(),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the timezone information");
		}
		log.info("getTimezoneInfo : End");
		return ResponseEntity.ok().body(result);

	}
	
	@ExceptionHandler({ RestServiceException.class, BusinessException.class })
	public ResponseEntity<ErrorResponse> exceptionHandler(RestServiceException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getErrorCode());
		error.setHttpStatus(ex.getHttpStatus());
		error.setErrorMessage(ex.getErrorMessage());
		error.setDeveloperMessage(ex.getDeveloperMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
