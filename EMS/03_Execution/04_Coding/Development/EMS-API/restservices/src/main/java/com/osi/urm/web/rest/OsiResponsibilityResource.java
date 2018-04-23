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

import com.osi.ems.common.CommonService;
import com.osi.urm.config.AppConfig;
import com.osi.urm.domain.OsiResponsibilities;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.OsiResponsibilityService;
import com.osi.urm.service.dto.OsiResponsibilitiesDTO;
import com.osi.urm.service.impl.OsiResponsibilityServiceImpl;
import com.osi.urm.web.rest.util.SuccessResponse;

/**
 * REST controller for managing OsiResponsibility.
 */
@RestController
@RequestMapping("/api")
public class OsiResponsibilityResource {

	private final Logger log = LoggerFactory.getLogger(OsiResponsibilityResource.class);

	@Autowired
	private OsiResponsibilityService osiResponsibilityService;

	@Autowired
	OsiResponsibilityServiceImpl osiResponsibilityServiceImpl;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private Environment env;
	
	@Autowired
	private CommonService commonService;

	/**
	 * POST /responsibilities : Create a new osiResponsibility.
	 *
	 * @param osiResponsibilitiesDTO
	 *            the osiResponsibilitiesDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new osiResponsibilitiesDTO, or with status 400 (Bad Request) if
	 *         the osiResponsibility has already an ID
	 * @throws DataAccessException 
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/responsibilities")
	public ResponseEntity<SuccessResponse> createOsiResponsibility(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiResponsibilitiesDTO osiResponsibilitiesDTO) throws RestServiceException, DataAccessException {
		log.debug("REST request to update OsiResponsibility : {}", osiResponsibilitiesDTO);
		
		SuccessResponse successResponse = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			//osiResponsibilitiesDTO.setCreatedDate(new Date());
			osiResponsibilitiesDTO.setCreatedDate(commonService.getCurrentDateinUTC());
			osiResponsibilitiesDTO.setCreatedBy(auth.getOsiUserDTO().getId());
			osiResponsibilitiesDTO.setActive(1);
			osiResponsibilitiesDTO.setUpdatedBy(auth.getOsiUserDTO().getId());
			//osiResponsibilitiesDTO.setUpdatedDate(new Date());
			osiResponsibilitiesDTO.setUpdatedDate(commonService.getCurrentDateinUTC());
			OsiResponsibilitiesDTO result = osiResponsibilityService.save(osiResponsibilitiesDTO,
					auth.getOsiUserDTO().getBusinessGroupId());
			if (result != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1003"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}

	/**
	 * PUT /responsibilities : Updates an existing osiResponsibility.
	 *
	 * @param osiResponsibilitiesDTO
	 *            the osiResponsibilitiesDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         osiResponsibilitiesDTO, or with status 400 (Bad Request) if the
	 *         osiResponsibilitiesDTO is not valid, or with status 500 (Internal
	 *         Server Error) if the osiResponsibilitiesDTO couldnt be updated
	 * @throws DataAccessException 
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/responsibilities")
	public ResponseEntity<SuccessResponse> updateOsiResponsibility(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiResponsibilitiesDTO osiResponsibilitiesDTO) throws RestServiceException, DataAccessException {
		log.debug("REST request to update OsiResponsibility : {}", osiResponsibilitiesDTO);
		SuccessResponse successResponse = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			//osiResponsibilitiesDTO.setUpdatedDate(new Date());
			osiResponsibilitiesDTO.setUpdatedDate(commonService.getCurrentDateinUTC());
			osiResponsibilitiesDTO.setUpdatedBy(auth.getOsiUserDTO().getId());
			OsiResponsibilitiesDTO result = null;
			if (osiResponsibilitiesDTO.getId() == null) {
				result = osiResponsibilityService.save(osiResponsibilitiesDTO,
						auth.getOsiUserDTO().getBusinessGroupId());
			}
			result = osiResponsibilityService.save(osiResponsibilitiesDTO, auth.getOsiUserDTO().getBusinessGroupId());
			if (result != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1004"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}

	/**
	 * GET /responsibilities : get all the osiResponsibilities.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         osiResponsibilities in body
	 * @throws URISyntaxException
	 *             if there is an error to generate the pagination HTTP headers
	 */
	
	@GetMapping("/intiallyResponsibilities")
	public ResponseEntity<List<OsiResponsibilitiesDTO>> getInitialOsiResponsibilities(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		List<OsiResponsibilitiesDTO> osiResponsibilitiesDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiResponsibilitiesDTOList = osiResponsibilityService.findInitialResponsibilities(auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiResponsibilitiesDTOList);
	}

	@GetMapping("/responsibilities")
	public ResponseEntity<List<OsiResponsibilitiesDTO>> getAllOsiResponsibilities(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		List<OsiResponsibilitiesDTO> osiResponsibilitiesDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiResponsibilitiesDTOList = osiResponsibilityService.findAll(auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiResponsibilitiesDTOList);
	}
	
	@GetMapping("/userResponsibilities")
	public ResponseEntity<List<OsiResponsibilitiesDTO>> getAllOsiResponsibilitiesList(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		List<OsiResponsibilitiesDTO> osiResponsibilitiesDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiResponsibilitiesDTOList = osiResponsibilityService.findAllOsiResponsibilitiesList(auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiResponsibilitiesDTOList);
	}

	/**
	 * GET /responsibilities/:id : get the "id" osiResponsibility.
	 *
	 * @param id
	 *            the id of the osiResponsibilitiesDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         osiResponsibilitiesDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/responsibilities/{id}")
	public ResponseEntity<OsiResponsibilitiesDTO> getOsiResponsibility(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable Integer id)
			throws RestServiceException {
	
		log.debug("REST request to get OsiInventoryOrg : {}", id);

		OsiResponsibilitiesDTO osiResponsibilitiesDTO = new OsiResponsibilitiesDTO();
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiResponsibilitiesDTO = osiResponsibilityService.findOne(auth.getOsiUserDTO().getBusinessGroupId(), id);
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiResponsibilitiesDTO);

	}

	/**
	 * DELETE /responsibilities/:id : delete the "id" osiResponsibility.
	 *
	 * @param id
	 *            the id of the osiResponsibilitiesDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/responsibilities/{id}")
	public ResponseEntity<SuccessResponse> deleteOsiResponsibility(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,@PathVariable Integer id)
			throws RestServiceException {
		SuccessResponse successResponse = null;
		OsiResponsibilitiesDTO osiResponsibilitiesDTO = new OsiResponsibilitiesDTO();
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			 osiResponsibilitiesDTO=osiResponsibilityService.delete(id,auth.getOsiUserDTO().getId());
			if (osiResponsibilitiesDTO != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1029"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}
	
	 @PostMapping("/responsibilities/searchResp")
	    public ResponseEntity<List<OsiResponsibilitiesDTO>> searchOsiResponsibilities(@Valid @RequestBody OsiResponsibilities osiResponsibilities, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws URISyntaxException, RestServiceException {
	    
	    	log.debug("REST request to search Responsibilities : {}", osiResponsibilities);
	    	List<OsiResponsibilitiesDTO> searchResponsibilityList=new ArrayList<OsiResponsibilitiesDTO>();
			try {
				
				AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
				if(auth!=null)
				{
					searchResponsibilityList =osiResponsibilityService.findOsiResponsibilitiesByNameOrDescription(osiResponsibilities.getRespName(), osiResponsibilities.getDescription());
							
				}
				
			} catch (BusinessException e) {
				throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
				//e.printStackTrace();
			}
	        return  new ResponseEntity<List<OsiResponsibilitiesDTO>>(searchResponsibilityList,HttpStatus.OK);
	    }
	
	@ExceptionHandler({RestServiceException.class, BusinessException.class, DataAccessException.class})
	public ResponseEntity<ErrorResponse> exceptionHandler(RestServiceException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getErrorCode());
		error.setHttpStatus(ex.getHttpStatus());
		error.setErrorMessage(ex.getErrorMessage());
		error.setDeveloperMessage(ex.getDeveloperMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.valueOf(ex.getHttpStatus()));
	}

}
