package com.osi.ems.web.rest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.service.OsiCategoryService;
import com.osi.ems.service.dto.OsiCategoryDTO;
import com.osi.ems.service.dto.OsiOrganizationsDTO;
import com.osi.ems.service.dto.OsiTablesDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;

/**
 * REST controller for managing OsiCategory.
 */
@RestController
@RequestMapping("/api")
public class OsiCategoryResource {

	private final Logger log = LoggerFactory.getLogger(OsiCategoryResource.class);

	@Autowired
	private OsiCategoryService osiCategoryService;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private Environment env;

	/**
	 * POST /categories : Create a new osiCategory.
	 *
	 * @param osiCategoryDTO
	 *            the osiCategoryDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         osiCategoriesDTO, or with status 400 (Bad Request) if the osiCategory
	 *         has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */

	@PostMapping("/categories")
	public ResponseEntity<OsiCategoryDTO> createOsiCategory(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiCategoryDTO osiCategoryDTO) throws RestServiceException {
		log.debug("REST request to create Osi Categories : {}", osiCategoryDTO);
		SuccessResponse successResponse = null;
		log.info("createOsiCategory : Begin");
		OsiCategoryDTO result = new OsiCategoryDTO();
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			
			result = osiCategoryService.save(osiCategoryDTO, auth.getOsiUserDTO().getId());
			if (result != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1046"));
			}
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while creating osi categories");
		}
		log.info("createOsiCategory : End");
		return ResponseEntity.ok().body(result);
	}

	/**
	 * POST /searchCategories : Search osiCategories.
	 *
	 * @param osiCategoryDTO
	 *            the osiCategoryDTO to search
	 * @return the ResponseEntity with status 200 and the list of osiCategories in
	 *         body
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/searchCategories")
	public List<OsiCategoryDTO> searchMenuEntries(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiCategoryDTO osiCategoryDTO) throws RestServiceException {
		log.info("searchMenuEntries : Begin");
		List<OsiCategoryDTO> osiCategoryDTOList = null;
		try {
			authTokenStore.retrieveToken(authToken);

			osiCategoryDTOList = osiCategoryService.findCategories(osiCategoryDTO);
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while seaching osi menu entries");
		}
		log.info("searchMenuEntries : End");
		return osiCategoryDTOList;
	}

	/**
	 * GET /organizations : get all the osiOrganizations.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         osiOrganizations in body
	 */
	@GetMapping("/orgnaizations")
	public List<OsiOrganizationsDTO> getAllOsiOrganizations(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		List<OsiOrganizationsDTO> osiOrganizationDTOList = new ArrayList<>();
		 authTokenStore.retrieveToken(authToken);
		 log.info("getAllOsiOrganizations : Begin");
		try {
			log.debug("REST request to get all OsiOrganizations");
			osiOrganizationDTOList = osiCategoryService.findAllOrganizations();
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting osi organizations");
		}
		log.info("getAllOsiOrganizations : End");
		return osiOrganizationDTOList;
	}

	/**
	 * GET /categories : get all the osiCategories.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of osiCategories
	 *         in body
	 */
	@GetMapping("/categories")
	public List<OsiCategoryDTO> getAllOsiCategories(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException {
		List<OsiCategoryDTO> osiCategoryDTOList = new ArrayList<>();
		 authTokenStore.retrieveToken(authToken);
		 log.info("getAllOsiCategories : Begin");
		try {
			log.debug("REST request to get all OsiCategories");
			osiCategoryDTOList = osiCategoryService.findAll();
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting osi categories");
		}
		log.info("getAllOsiCategories : End");
		return osiCategoryDTOList;
	}

	/**
	 * GET /tablenames : get all the osiDBTableNames.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         osiMenuEntries in body
	 */
	@GetMapping("/tablenames")
	public OsiTablesDTO getAllOsiTableNames(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException {
		OsiTablesDTO osiTablesDTO = new OsiTablesDTO();
		  authTokenStore.retrieveToken(authToken);
			log.info("getAllOsiTableNames : Begin");
		  try{
			log.debug("REST request to get all OsiTableNames");
			String[] items = env.getProperty("tables").split(",");
			osiTablesDTO.setTblName(Arrays.asList(items));
			log.debug("REST request to get all OsiTableNames");
		  }catch (Exception e) {
				log.error("Error Occured : "+e.getMessage());
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting table names");
			}
			log.info("getAllOsiTableNames : End");
		return osiTablesDTO;
	}

	/**
	 * GET /tablecolumns : get all the column Names by Table Name.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of column names
	 *         of particular table in body
	 */
	@PostMapping("/tablecolumns")
	public List<OsiTablesDTO> getAllColumnsByTableName(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiTablesDTO osiTablesDTO) throws RestServiceException {

		 authTokenStore.retrieveToken(authToken);

		log.debug("REST request to get all table names");
		log.info("getAllColumnsByTableName : Begin");
		List<OsiTablesDTO> osiTablesDTOList = null;
		try {

			osiTablesDTOList = osiCategoryService.findColumnsByTableName(osiTablesDTO.getTableName());
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting osi categories");
		}
		log.info("getAllColumnsByTableName : End");
		return osiTablesDTOList;
	}

	/**
	 * PUT /categories : Updates an existing osiCategories.
	 *
	 * @param osiCategoryDTO
	 *            the osiCategoryDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         osiCategoryDTO, or with status 400 (Bad Request) if the
	 *         osiCategoryDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the osiCategoryDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/categories")
	public ResponseEntity<OsiCategoryDTO> updateOsiCategory(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiCategoryDTO osiCategoryDTO) throws RestServiceException {
		log.debug("REST request to update OsiCategory : {}", osiCategoryDTO);
		SuccessResponse successResponse = null;
		OsiCategoryDTO result = new OsiCategoryDTO();
		log.info("updateOsiCategory : Begin");
		try {
			System.out.println("in put mapping ==========================>");
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);

			int userId = auth.getOsiUserDTO().getId();
			if (osiCategoryDTO.getId() == null) {
				List<OsiCategoryDTO> resultSet = new ArrayList<>();
				resultSet.add(osiCategoryDTO);
				return createOsiCategory(authToken, osiCategoryDTO);
			}

			result = osiCategoryService.save(osiCategoryDTO, userId);

			if (result != null) {

				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1032"));
			}

		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NOT_MODIFIED.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while updating osi categories");
		}

		log.info("updateOsiCategory : End");
		return ResponseEntity.ok().body(result);

	}

	
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
