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

import com.osi.ems.common.Util;
import com.osi.ems.domain.OsiCountries;
import com.osi.ems.service.OsiCountriesService;
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
@RequestMapping("/api/countries")
public class OsiCountriesResource {

	private final Logger log = LoggerFactory.getLogger(OsiCountriesResource.class);

	@Autowired
	private OsiCountriesService osiCountiresService;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private Environment env;

	/**
	 * Method for saving the osicountries.
	 * 
	 * @param osiCountries
	 * @param authToken
	 * @return
	 * @throws URISyntaxException
	 * @throws RestServiceException
	 */
	@PostMapping
	public ResponseEntity<SuccessResponse> createOsiCountries(@Valid @RequestBody OsiCountries osiCountries,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		SuccessResponse successResponse = null;
		log.debug("REST request to save OsiCountries : {}", osiCountries);
		log.info("createOsiCountries : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiCountries = this.osiCountiresService.saveCountry(osiCountries, auth.getOsiUserDTO().getId());
			if (osiCountries != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1051"));
				
			}
		} catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating osi countries");
		}

		log.info("createOsiCountries : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}

	/**
	 * 
	 * @param osiCountries
	 * @param authToken
	 * @param countryId
	 * @return
	 * @throws URISyntaxException
	 * @throws RestServiceException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<SuccessResponse> updateOsiCountries(@Valid @RequestBody OsiCountries osiCountries,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable("id") int countryId)
			throws URISyntaxException, RestServiceException {
		log.debug("REST request to update OsiCountries : {}", osiCountries);
		log.info("updateOsiCountries : Begin");
		SuccessResponse successResponse = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiCountries = this.osiCountiresService.updateCountry(osiCountries, countryId,
					auth.getOsiUserDTO().getId());
			if (osiCountries != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1052"));
			}
		} catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while updating osi countries");
		}

		log.info("updateOsiCountries : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);

	}

	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping(value = "/countryCodes")
	public ResponseEntity<String> getCountryCodesList(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		log.debug("REST request to get all Getall osi countries");
		log.info("getCountryCodesList : Begin");
		String osiCountriesList = null;
		try {
			List<OsiCountries> response = this.osiCountiresService.getCountryCodesList();
			osiCountriesList = Util.toJsonString(response);
		} catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting osi country codes");
		}

		log.info("getCountryCodesList : End");
		return ResponseEntity.ok().body(osiCountriesList);

	}
	@GetMapping
	public ResponseEntity<List<OsiCountries>> getOsiCountriesList(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		log.debug("REST request to get all Getall osi countries");
		log.info("getOsiCountriesList : Begin");
		List<OsiCountries> osiCountriesList = null;
		try {
			osiCountriesList = this.osiCountiresService.getAllCountriesList();
		} catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting osi country codes list");
		}

		log.info("getOsiCountriesList : End");
		return ResponseEntity.ok().body(osiCountriesList);

	}
	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<OsiCountries> getOsiCountriesById(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		log.debug("REST request to get all getAllOsiMenus");

		log.info("getOsiCountriesById : Begin");
		OsiCountries osiCountries = null;
		try {
			osiCountries = this.osiCountiresService.getCountryByCountryId(id);
		} catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting osi countries");
		}

		log.info("getOsiCountriesById : End");
		return ResponseEntity.ok().body(osiCountries);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse> deleteOsiCountries(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		SuccessResponse successResponse = null;
		log.info("deleteOsiCountries : Begin");
		try {
			authTokenStore.retrieveToken(authToken);
			this.osiCountiresService.deleteOsiCountries(id);

		} catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while deleting osi countries");
		}

		log.info("deleteOsiCountries : End");
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
