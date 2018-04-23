package com.osi.ems.web.rest;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.osi.ems.domain.OsiStates;
import com.osi.ems.service.OsiStatesService;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;

/**
 * REST controller for managing OsiMenu.
 */
@RestController
@RequestMapping("/api/states")
public class OsiStatesResource {

	private final Logger log = LoggerFactory.getLogger(OsiStatesResource.class);

	@Autowired
	private OsiStatesService OsiStatesService;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private Environment env;

	/**
	 * Method for saving the osiStates.
	 * 
	 * @param osiStates
	 * @param authToken
	 * @return
	 * @throws URISyntaxException
	 * @throws RestServiceException
	 */
	@PostMapping
	public ResponseEntity<SuccessResponse> createOsiStates(@Valid @RequestBody OsiStates osiStates,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		SuccessResponse successResponse = null;
		log.info("createOsiStates : Begin");
		log.debug("REST request to save OsiStates : {}", osiStates);
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiStates = this.OsiStatesService.saveState(osiStates, auth.getOsiUserDTO().getId());
			if (osiStates != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1053"));
			}
		}catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating the osi states");
		}
		log.info("createOsiStates : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}

	/**
	 * 
	 * @param osiStates
	 * @param authToken
	 * @param stateId
	 * @return
	 * @throws URISyntaxException
	 * @throws RestServiceException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<SuccessResponse> updateOsiStates(@Valid @RequestBody OsiStates osiStates,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable("id") int stateId)
			throws URISyntaxException, RestServiceException {
		log.debug("REST request to update OsiStates : {}", osiStates);
		log.info("createOsiStates : Begin");
		SuccessResponse successResponse = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiStates = this.OsiStatesService.updateState(osiStates, stateId, auth.getOsiUserDTO().getId());
			if (osiStates != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1054"));
			}
		}catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while updating the osi states");
		}
		log.info("createOsiStates : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);

	}

	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping
	public ResponseEntity<List<OsiStates>> getOsiStatesList(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		log.debug("REST request to get all Getall osi states");
		log.info("getOsiStatesList : Begin");
		List<OsiStates> osiStatesList = null;
		try {
			osiStatesList = this.OsiStatesService.getAllStatesList();
		}catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the osi states");
		}
		log.info("getOsiStatesList : End");
		return ResponseEntity.ok().body(osiStatesList);

	}

	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<OsiStates> getOsiStatesById(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		log.debug("REST request to get all getAllOsiMenus");
		log.info("getOsiStatesById : Begin");
		OsiStates osiStates = null;
		try {
			osiStates = this.OsiStatesService.getStateByStateId(id);
		} catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the osi states");
		}
		log.info("getOsiStatesById : End");
		return ResponseEntity.ok().body(osiStates);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse> deleteOsiStates(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		SuccessResponse successResponse = null;
		log.info("deleteOsiStates : Begin");
		try {
			authTokenStore.retrieveToken(authToken);
			this.OsiStatesService.deleteOsiStates(id);

		} catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while deleting the osi states");
		}
		log.info("deleteOsiStates : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);

	}

	@GetMapping(value = "/bycountry/{id}")
	public ResponseEntity<List<OsiStates>> getOsiStatesListByCountryId(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable("id") int countryId)
			throws RestServiceException {
		log.debug("REST request to get all Getall osi states by country id");
		log.info("getOsiStatesListByCountryId : Begin");
		List<OsiStates> osiStatesList = null;
		try {
			osiStatesList = this.OsiStatesService.findStatesListByCountryId(countryId);
		} catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the osi states");
		}
		log.info("getOsiStatesListByCountryId : End");

		return ResponseEntity.ok().body(osiStatesList);

	}

	/**
	 * Generic exception handler for {@link RestServiceException} and
	 * {@link BusinessException}
	 * 
	 * @param ex
	 * @return REturns {@link ResponseEntity} object.
	 */
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
