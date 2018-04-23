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

import com.osi.ems.service.OsiAddressService;
import com.osi.ems.service.dto.OsiOrgAddressesDTO;
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
@RequestMapping("/api/addresses")
public class OsiAddressResource {

	private final Logger log = LoggerFactory.getLogger(OsiAddressResource.class);

	@Autowired
	private OsiAddressService osiAddressService;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private Environment env;

	/**
	 * Method for saving the osiAddress.
	 * 
	 * @param osiAddress
	 * @param authToken
	 * @return
	 * @throws URISyntaxException
	 * @throws RestServiceException
	 */
	@PostMapping
	public ResponseEntity<OsiOrgAddressesDTO> createOsiAddress(@Valid @RequestBody OsiOrgAddressesDTO osiAddress,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		OsiOrgAddressesDTO  addressDto = null;
		log.info("createOsiAddress : Begin");
		log.debug("REST request to save OsiAddress : {}", osiAddress);
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			addressDto = this.osiAddressService.saveAddress(osiAddress, auth.getOsiUserDTO().getId());

		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating osi address");
		}
		log.info("createOsiAddress : End");

		return new ResponseEntity<OsiOrgAddressesDTO>(addressDto, HttpStatus.OK);
	}

	/**
	 * 
	 * @param osiAddress
	 * @param authToken
	 * @param countryId
	 * @return
	 * @throws URISyntaxException
	 * @throws RestServiceException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<OsiOrgAddressesDTO> updateOsiAddress(@Valid @RequestBody OsiOrgAddressesDTO osiAddress,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable("id") int addressId)
			throws URISyntaxException, RestServiceException {
		log.debug("REST request to update OsiAddress : {}", osiAddress);
		log.info("updateOsiAddress : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			
			osiAddress = this.osiAddressService.updateAddress(osiAddress, addressId, auth.getOsiUserDTO().getId());

		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while updating osi address");
		}
		log.info("updateOsiAddress : End");
		
		return new ResponseEntity<OsiOrgAddressesDTO>(osiAddress, HttpStatus.OK);

	}

	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping
	public ResponseEntity<List<OsiOrgAddressesDTO>> getOsiAddressList(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		log.debug("REST request to get all Getall osi Address");
		log.info("getOsiAddressList : Begin");
		List<OsiOrgAddressesDTO> osiAddressList = null;
		try {
			osiAddressList = this.osiAddressService.getAllAddressesList();
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting osi address list");
		}
		log.info("getOsiAddressList : End");
		return ResponseEntity.ok().body(osiAddressList);

	}

	/**
	 * 
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<OsiOrgAddressesDTO> getOsiAddressById(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		log.debug("REST request to get all getAllOsiMenus");
		log.info("getOsiAddressById : Begin");
		OsiOrgAddressesDTO osiAddress = null;
		try {
			osiAddress = this.osiAddressService.getAddressByAddressId(id);
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting osi address by id "+id);
		}
		log.info("getOsiAddressById : End");
		return ResponseEntity.ok().body(osiAddress);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse> deleteOsiAddress(@PathVariable("id") int id,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		SuccessResponse successResponse = null;
		log.info("deleteOsiAddress : Begin");
		try {
			authTokenStore.retrieveToken(authToken);
			this.osiAddressService.deleteOsiOrgAddressesDTO(id);

		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while deleting osi address by id "+id);
		}
		log.info("deleteOsiAddress : End");
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);

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
