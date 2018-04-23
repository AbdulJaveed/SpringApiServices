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

import com.osi.ems.service.OsiContactsService;
import com.osi.ems.service.dto.OsiContactsDto;
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
@RequestMapping("/api/contacts")
public class OsiContactsResource {

	private final Logger log = LoggerFactory.getLogger(OsiContactsResource.class);

	@Autowired
	private OsiContactsService osiContactsService;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private Environment env;

	/**
	 * Method for saving the osicountries.
	 * 
	 * @param osiContacts
	 * @param authToken
	 * @return
	 * @throws URISyntaxException
	 * @throws RestServiceException
	 */
	@PostMapping
	public ResponseEntity<SuccessResponse> createOsiContacts(@Valid @RequestBody OsiContactsDto osiContacts,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		SuccessResponse successResponse = null;
		log.debug("REST request to save OsiContacts : {}", osiContacts);
		log.info("createOsiContacts : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiContacts = this.osiContactsService.saveContact(osiContacts, auth.getOsiUserDTO().getId());
			if (osiContacts != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1055"));
				
			}
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating osi contacts");
		}

		log.info("createOsiContacts : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}
	
	@PostMapping("/list")
	public ResponseEntity<SuccessResponse> createOsiContactsList(@Valid @RequestBody List<OsiContactsDto> osiContacts,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		SuccessResponse successResponse = null;
		log.info("createOsiContactsList : Begin");
		log.debug("REST request to save OsiContacts : {}", osiContacts);
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiContacts = this.osiContactsService.saveContactList(osiContacts, auth.getOsiUserDTO().getId());
			if (osiContacts != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1055"));
				
			}
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating osi contacts");
		}

		log.info("createOsiContactsList : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}

	/**
	 * 
	 * @param osiContacts
	 * @param authToken
	 * @param countryId
	 * @return
	 * @throws URISyntaxException
	 * @throws RestServiceException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<SuccessResponse> updateOsiContacts(@Valid @RequestBody OsiContactsDto osiContacts,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable("id") int countryId)
			throws URISyntaxException, RestServiceException {
		log.debug("REST request to update OsiContacts : {}", osiContacts);
		log.info("updateOsiContacts : Begin");
		SuccessResponse successResponse = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiContacts = this.osiContactsService.updateContact(osiContacts, countryId,
					auth.getOsiUserDTO().getId());
			if (osiContacts != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1056"));
			}
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while updating osi contacts");
		}

		log.info("updateOsiContacts : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);

	}

	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping
	public ResponseEntity<List<OsiContactsDto>> getOsiContactsList(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		log.debug("REST request to get all Getall osi countries");
		log.info("getOsiContactsList : Begin");
		List<OsiContactsDto> osiContactsList = null;
		try {
			osiContactsList = this.osiContactsService.getAllContactsList();
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting osi contacts");
		}

		log.info("getOsiContactsList : End");
		return ResponseEntity.ok().body(osiContactsList);

	}

	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<OsiContactsDto> getOsiContactsById(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		log.debug("REST request to get all getAllOsiMenus");
		log.info("getOsiContactsById : Begin");
		OsiContactsDto osiContacts = null;
		try {
			osiContacts = this.osiContactsService.getContactByContactId(id);
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting osi contacts");
		}

		log.info("getOsiContactsById : End");
		return ResponseEntity.ok().body(osiContacts);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse> deleteOsiContacts(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		SuccessResponse successResponse = null;
		log.info("deleteOsiContacts : Begin");
		try {
			authTokenStore.retrieveToken(authToken);
			this.osiContactsService.deleteOsiContacts(id);

		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while deleting osi contacts");
		}

		log.info("deleteOsiContacts : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);

	}

	/**
	 * Generic exception handler for {@link RestServiceException} and {@link BusinessException}
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
