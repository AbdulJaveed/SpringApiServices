package com.osi.urm.web.rest;

import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.common.CommonService;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.OsiMenuEntriesService;
import com.osi.urm.service.dto.OsiMenuEntriesDTO;
import com.osi.urm.web.rest.util.SuccessResponse;

/**
 * REST controller for managing OsiMenuEntries.
 */
@RestController
@RequestMapping("/api")
public class OsiMenuEntriesResource {

	private final Logger log = LoggerFactory.getLogger(OsiMenuEntriesResource.class);

	@Autowired
	private OsiMenuEntriesService osiMenuEntriesService;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private Environment env;
	
	@Autowired
	private CommonService commonService;

	/**
	 * POST /menu-entries : Create a new osiMenuEntries.
	 *
	 * @param osiMenuEntriesDTO
	 *            the osiMenuEntriesDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new osiMenuEntriesDTO, or with status 400 (Bad Request) if the
	 *         osiMenuEntries has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */

	@PostMapping("/searchEntries")
	public List<OsiMenuEntriesDTO> searchMenuEntries(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiMenuEntriesDTO osiMenuEntriesDTO) throws RestServiceException {

		SuccessResponse successResponse;
		List<OsiMenuEntriesDTO> osiMenuEntriesDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer businessGroupId = auth.getOsiUserDTO().getBusinessGroupId();
			osiMenuEntriesDTOList = osiMenuEntriesService.findMenuEntries(osiMenuEntriesDTO, businessGroupId);
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return osiMenuEntriesDTOList;
	}

	@PostMapping("/menu-entries-multiple")
	public ResponseEntity<SuccessResponse> createOsiMenuEntries(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody List<OsiMenuEntriesDTO> osiMenuEntries) throws RestServiceException {

		SuccessResponse successResponse = null;
		List<OsiMenuEntriesDTO> resultSet = new ArrayList<>();
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			// Integer count = osiInventoryOrgService.save(osiInventoryOrgDTO,
			// auth.getOsiUserDTO().getId(),
			// auth.getOsiUserDTO().getBusinessGroupId());
			int businessGroupId = auth.getOsiUserDTO().getBusinessGroupId();
			int userId = auth.getOsiUserDTO().getId();
			//Date date = new Date();
			for (OsiMenuEntriesDTO entry : osiMenuEntries) {
				//entry.setStartDate(date);
				//entry.setEndDate(date);
				entry.setStartDate(commonService.getCurrentDateinUTC());
				entry.setEndDate(commonService.getCurrentDateinUTC());
				entry.setBusinessGroupId(businessGroupId);
				OsiMenuEntriesDTO result = osiMenuEntriesService.save(entry, userId);
				resultSet.add(result);
			}
			if (!resultSet.isEmpty()) {

				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1030"));
			}

		} catch (BusinessException e) {
			// System.out.println("Exception in
			// createOsiMenuEntries:"+e.getMessage());
			successResponse.setMessage(env.getProperty("ERR_1031"));
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}

	/**
	 * PUT /menu-entries : Updates an existing osiMenuEntries.
	 *
	 * @param osiMenuEntriesDTO
	 *            the osiMenuEntriesDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         osiMenuEntriesDTO, or with status 400 (Bad Request) if the
	 *         osiMenuEntriesDTO is not valid, or with status 500 (Internal
	 *         Server Error) if the osiMenuEntriesDTO couldnt be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/menu-entries")
	public ResponseEntity<SuccessResponse> updateOsiMenuEntries(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiMenuEntriesDTO osiMenuEntriesDTO) throws RestServiceException {
		log.debug("REST request to update OsiMenuEntries : {}", osiMenuEntriesDTO);
		SuccessResponse successResponse = null;
		try {
			System.out.println("in put mapping ==========================>");
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			int businessGroupId = auth.getOsiUserDTO().getBusinessGroupId();
			int userId = auth.getOsiUserDTO().getId();
			if (osiMenuEntriesDTO.getId() == null) {
				List<OsiMenuEntriesDTO> resultSet = new ArrayList<>();
				resultSet.add(osiMenuEntriesDTO);
				return createOsiMenuEntries(authToken, resultSet);
			}
			//Date date = new Date();
			if (osiMenuEntriesDTO.getStartDate() == null) {
				//osiMenuEntriesDTO.setStartDate(date);
				osiMenuEntriesDTO.setStartDate(commonService.getCurrentDateinUTC());
			}

			if (osiMenuEntriesDTO.getEndDate() == null) {
				//osiMenuEntriesDTO.setEndDate(date);
				osiMenuEntriesDTO.setEndDate(commonService.getCurrentDateinUTC());
			}
			if (osiMenuEntriesDTO.getBusinessGroupId() == null) {
				osiMenuEntriesDTO.setBusinessGroupId(businessGroupId);
			}

			OsiMenuEntriesDTO result = osiMenuEntriesService.save(osiMenuEntriesDTO, userId);

			if (result != null) {

				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1032"));
			}

		} catch (BusinessException e) {
			// System.out.println("Exception in
			// createOsiMenuEntries:"+e.getMessage());
			successResponse.setMessage(env.getProperty("ERR_1033"));
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}

		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);

		/*
		 * return ResponseEntity.ok() .body(result);
		 */
	}
	
	/**
	 * GET /menu-entries : get 10  MenuEntries.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         osiMenuEntries in body
	 */
	
	@GetMapping("/menu-entries-initially")
	public List<OsiMenuEntriesDTO> getOsiMenuEntriesInitially(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException {
		List<OsiMenuEntriesDTO> osiMenuEntriesDTOList = new ArrayList<>();
		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
		int businessGroupId = auth.getOsiUserDTO().getBusinessGroupId();
		try {
			log.debug("REST request to get all OsiMenuEntries");
			osiMenuEntriesDTOList = osiMenuEntriesService.findMenuEntriesInitially(businessGroupId);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return osiMenuEntriesDTOList;
	}

	/**
	 * GET /menu-entries : get all the osiMenuEntries.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         osiMenuEntries in body
	 */
	@GetMapping("/menu-entries")
	public List<OsiMenuEntriesDTO> getAllOsiMenuEntries(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException {
		List<OsiMenuEntriesDTO> osiMenuEntriesDTOList = new ArrayList<>();
		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
		int businessGroupId = auth.getOsiUserDTO().getBusinessGroupId();
		try {
			log.debug("REST request to get all OsiMenuEntries");
			osiMenuEntriesDTOList = osiMenuEntriesService.findAll(businessGroupId);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return osiMenuEntriesDTOList;
	}
	
	

	/**
	 * GET /menu-entries/:id : get the "id" osiMenuEntries.
	 *
	 * @param id
	 *            the id of the osiMenuEntriesDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         osiMenuEntriesDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/menu-entries/{id}")
	public ResponseEntity<OsiMenuEntriesDTO> getOsiMenuEntries(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable Integer id)
			throws RestServiceException {
		log.debug("REST request to get OsiMenuEntries : {}", id);
		OsiMenuEntriesDTO osiMenuEntriesDTO = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiMenuEntriesDTO = osiMenuEntriesService.findOne(id, auth.getOsiUserDTO().getBusinessGroupId());
			// osiMenuEntriesDTO = osiMenuEntriesService.findOne(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(osiMenuEntriesDTO, HttpStatus.OK);
	}

	/**
	 * DELETE /menu-entries/:id : delete the "id" osiMenuEntries.
	 *
	 * @param id
	 *            the id of the osiMenuEntriesDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */

	@PostMapping("/deleteMenuEntry")
	public ResponseEntity<SuccessResponse> deleteOsiMenuEntry(@Valid @RequestBody OsiMenuEntriesDTO osiMenuEntriesDTO,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		int count = 0;
		System.out.println("In deleteMenuEntry Rest==========================>");
		SuccessResponse successResponse = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			if (osiMenuEntriesDTO.getId() > 0) {
				count = osiMenuEntriesService.deleteMenuEntry(osiMenuEntriesDTO.getId(),
						auth.getOsiUserDTO().getBusinessGroupId(), auth.getOsiUserDTO().getId());
				if (count > 0) {
					successResponse = new SuccessResponse();
					successResponse.setHttpStatus(HttpStatus.OK.value());
					successResponse.setMessage(env.getProperty("MSG_1036"));
				}
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), e.getErrorCode(),
					env.getProperty(e.getErrorCode()), e.getSystemMessage());
		}
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);

	}

	@ExceptionHandler(RestServiceException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(RestServiceException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getErrorCode());
		error.setHttpStatus(ex.getHttpStatus());
		error.setErrorMessage(ex.getErrorMessage());
		error.setDeveloperMessage(ex.getDeveloperMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

}
