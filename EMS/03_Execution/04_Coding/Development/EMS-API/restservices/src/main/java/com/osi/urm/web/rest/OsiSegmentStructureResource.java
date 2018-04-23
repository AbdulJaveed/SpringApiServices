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

import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.OsiSegmentStructureService;
import com.osi.urm.service.dto.OsiKeyFlexDTO;
import com.osi.urm.service.dto.OsiSegmentStructureDetailsDTO;
import com.osi.urm.service.dto.OsiSegmentStructureHdrDTO;
import com.osi.urm.web.rest.util.SuccessResponse;

/**
 * REST controller for managing osiSegmentStructure.
 */
@RestController
@RequestMapping("/api")
public class OsiSegmentStructureResource {

	private final Logger log = LoggerFactory.getLogger(OsiSegmentStructureResource.class);

	@Autowired
	private OsiSegmentStructureService osiSegmentStructureService;
	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private Environment env;

	@Autowired
	private AppConfig appConfig;

	/**
	 * POST /segment-structure : Create a new osiSegmentStructure.
	 *
	 * @param osiSegmentStructureDTO
	 *            the osiSegmentStructureDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new osiSegmentStructureDTO, or with status 400 (Bad Request) if
	 *         the osiSegmentStructure has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/create-segment-structure")
	public ResponseEntity<SuccessResponse> createOsiSegmentStructure(
			@Valid @RequestBody OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {

		if (osiSegmentStructureHdrDTO.getSegmentStructureHdrId() != null) {
			return ResponseEntity.badRequest().body(null);
		}

		SuccessResponse successResponse = null;

		try {

			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);

			/*
			 * Integer businessGroupId =
			 * auth.getOsiUserDTO().getBusinessGroupId(); OsiBusinessGroupDTO
			 * osiBusinessGroupDTO = new OsiBusinessGroupDTO();
			 * osiBusinessGroupDTO.setBusinessGroupId(businessGroupId);
			 */
			osiSegmentStructureHdrDTO.setBusinessGroupId(auth.getOsiUserDTO().getBusinessGroupId());

			OsiSegmentStructureHdrDTO result = osiSegmentStructureService.save(osiSegmentStructureHdrDTO,
					auth.getOsiUserDTO().getId().intValue());
			if (result.getSegmentStructureHdrId() > 0) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1007"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
			// e.printStackTrace();
		} /*
			 * catch (Exception e) { e.printStackTrace();
			 * 
			 * throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
			 * e.getErrorCode(), env.getProperty(e.getErrorCode()),
			 * e.getSystemMessage());
			 * 
			 * // e.printStackTrace(); }
			 */
		// return ResponseEntity.created(
		// new URI("/api/menus/" + result.getId())).body(result);

		/*
		 * log.debug("REST request to save osiSegmentStructure : {}",
		 * osiSegmentStructureDTO); if (osiSegmentStructureDTO.getId() != null)
		 * { return
		 * ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(
		 * "osiSegmentStructure", "idexists",
		 * "A new osiSegmentStructure cannot already have an ID")).body(null); }
		 * osiSegmentStructureDTO result =
		 * osiSegmentStructureService.save(osiSegmentStructureDTO); return
		 * ResponseEntity.created(new URI("/api/segment-structure/" +
		 * result.getId()))
		 * .headers(HeaderUtil.createEntityCreationAlert("osiSegmentStructure",
		 * result.getId().toString())) .body(result);
		 */

		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);

	}

	/**
	 * PUT /segment-structure : Updates an existing osiSegmentStructure.
	 *
	 * @param osiSegmentStructureDTO
	 *            the osiSegmentStructureDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         osiSegmentStructureDTO, or with status 400 (Bad Request) if the
	 *         osiSegmentStructureDTO is not valid, or with status 500 (Internal
	 *         Server Error) if the osiSegmentStructureDTO couldnt be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/update-segment-structure")
	public ResponseEntity<SuccessResponse> updateOsiSegmentStructure(
			@Valid @RequestBody OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		/*
		 * log.debug("REST request to update osiSegmentStructure : {}",
		 * osiSegmentStructureDTO); if (osiSegmentStructureDTO.getId() == null)
		 * { return createosiSegmentStructure(osiSegmentStructureDTO); }
		 * osiSegmentStructureDTO result =
		 * osiSegmentStructureService.save(osiSegmentStructureDTO); return
		 * ResponseEntity.ok()
		 * .headers(HeaderUtil.createEntityUpdateAlert("osiSegmentStructure",
		 * osiSegmentStructureDTO.getId().toString())) .body(result);
		 */

		if (osiSegmentStructureHdrDTO.getSegmentStructureHdrId() == null) {
			return ResponseEntity.badRequest().body(null);
		}

		SuccessResponse successResponse = null;

		try {

			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);

			/*
			 * Integer businessGroupId =
			 * auth.getOsiUserDTO().getBusinessGroupId(); OsiBusinessGroupDTO
			 * osiBusinessGroupDTO = new OsiBusinessGroupDTO();
			 * osiBusinessGroupDTO.setBusinessGroupId(businessGroupId);
			 */
			osiSegmentStructureHdrDTO.setBusinessGroupId(auth.getOsiUserDTO().getBusinessGroupId());

			OsiSegmentStructureHdrDTO result = osiSegmentStructureService.save(osiSegmentStructureHdrDTO,
					auth.getOsiUserDTO().getId().intValue());
			if (result.getSegmentStructureHdrId() > 0) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1008"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
			// e.printStackTrace();
		} /*
			 * catch (Exception e) { e.printStackTrace();
			 * 
			 * throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
			 * e.getErrorCode(), env.getProperty(e.getErrorCode()),
			 * e.getSystemMessage());
			 * 
			 * // e.printStackTrace(); }
			 */

		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);

		// return null;
	}

	/**
	 * GET /segment-structure : get all the osiSegmentStructures.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         osiSegmentStructures in body
	 * @throws URISyntaxException
	 *             if there is an error to generate the pagination HTTP headers
	 */
	@GetMapping("/getall-segment-structure")
	public List<OsiSegmentStructureHdrDTO> getAllOsiSegmentStructure(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {

		List<OsiSegmentStructureHdrDTO> result = null;
		try {

			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			result = osiSegmentStructureService.findAll(auth.getOsiUserDTO().getBusinessGroupId());

		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} /*
			 * catch (Exception e) { e.printStackTrace();
			 * 
			 * throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
			 * e.getErrorCode(), env.getProperty(e.getErrorCode()),
			 * e.getSystemMessage());
			 * 
			 * // e.printStackTrace(); }
			 */

		/*
		 * log.debug("REST request to get a page of osiSegmentStructures");
		 * Page<osiSegmentStructureDTO> page =
		 * osiSegmentStructureService.findAll(pageable); HttpHeaders headers =
		 * PaginationUtil.generatePaginationHttpHeaders(page,
		 * "/api/segment-structure"); return new
		 * ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		 */
		return result;
	}

	/**
	 * GET /segment-structure/:id : get the "id" osiSegmentStructure.
	 *
	 * @param id
	 *            the id of the osiSegmentStructureDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         osiSegmentStructureDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/get-one-segment-structure-id/{businessGroupId}")
	public OsiSegmentStructureHdrDTO getOsiSegmentStructureId(@PathVariable Integer businessGroupId,
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
		OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO = new OsiSegmentStructureHdrDTO();
		try {
				osiSegmentStructureHdrDTO = osiSegmentStructureService.getOsiSegmentStructureValues(businessGroupId);
		} catch (BusinessException e) {
			//e.printStackTrace();
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
		return osiSegmentStructureHdrDTO;
	}

	@GetMapping("/getone-segment-structure/{segmentStructureHdrId}")
	public ResponseEntity<OsiSegmentStructureHdrDTO> getOsiSegmentStructure(@PathVariable Integer segmentStructureHdrId,
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException, URISyntaxException {

		OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO = new OsiSegmentStructureHdrDTO();

		try {

			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);

			osiSegmentStructureHdrDTO = osiSegmentStructureService.findOne(segmentStructureHdrId,
					auth.getOsiUserDTO().getBusinessGroupId());

		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} /*
			 * catch (Exception e) { e.printStackTrace();
			 * 
			 * throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
			 * e.getErrorCode(), env.getProperty(e.getErrorCode()),
			 * e.getSystemMessage());
			 * 
			 * // e.printStackTrace(); }
			 */

		/*
		 * log.debug("REST request to get osiSegmentStructure : {}", id);
		 * osiSegmentStructureDTO osiSegmentStructureDTO =
		 * osiSegmentStructureService.findOne(id); return
		 * Optional.ofNullable(osiSegmentStructureDTO) .map(result -> new
		 * ResponseEntity<>( result, HttpStatus.OK)) .orElse(new
		 * ResponseEntity<>(HttpStatus.NOT_FOUND));
		 */

		return ResponseEntity.ok().body(osiSegmentStructureHdrDTO);
	}

	/**
	 * DELETE /delete-segment-structure/:id : delete the "segmentStructureHdrId"
	 * osiSegmentStructure.
	 *
	 * @param id
	 *            the id of the osiSegmentStructureDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@PostMapping("/delete-segment-structure/{segmentStructureHdrId}")
	public ResponseEntity<SuccessResponse> deleteOsiSegmentStructure(
			@RequestBody OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {

		SuccessResponse successResponse = null;
		int count = 0;
		try {

			if (osiSegmentStructureHdrDTO.getSegmentStructureHdrIds().size() > 0) {
				AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
				count = osiSegmentStructureService.deleteAll(osiSegmentStructureHdrDTO.getSegmentStructureHdrIds(),
						auth.getOsiUserDTO());

				if (count > 0) {
					successResponse = new SuccessResponse();
					successResponse.setHttpStatus(HttpStatus.OK.value());
					successResponse.setMessage(env.getProperty("MSG_1009"));
				}
			}

		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}

		/*
		 * log.debug("REST request to delete osiSegmentStructure : {}", id);
		 * osiSegmentStructureService.delete(id); return
		 * ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(
		 * "osiSegmentStructure", id.toString())).build();
		 */
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}

	@GetMapping("/segment-structure-values")
	public ResponseEntity<List<OsiSegmentStructureDetailsDTO>> getOsiCategorySegmentStructureValues(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		List<OsiSegmentStructureDetailsDTO> osiSegmentStructureDetailsDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiSegmentStructureDetailsDTOList = osiSegmentStructureService.getOsiSegmentStructureValues(
					appConfig.getCategoryStructure(), auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiSegmentStructureDetailsDTOList);
	}

	@GetMapping("/segment-structure-values-coa")
	public ResponseEntity<List<OsiSegmentStructureDetailsDTO>> getOsiCoaSegmentStructureValues(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		List<OsiSegmentStructureDetailsDTO> osiSegmentStructureDetailsDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiSegmentStructureDetailsDTOList = osiSegmentStructureService.getOsiSegmentStructureValues(
					appConfig.getCoaStructure(), auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiSegmentStructureDetailsDTOList);
	}

	@GetMapping("/key-flex-fields/{segmentStructureHdrId}")
	public ResponseEntity<List<OsiKeyFlexDTO>> getOsiKeyFlexFields(@PathVariable Integer segmentStructureHdrId,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		List<OsiKeyFlexDTO> osiKeyFlexDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiKeyFlexDTOList = osiSegmentStructureService.getOsiKeyFlexFields(segmentStructureHdrId,
					auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiKeyFlexDTOList);
	}

	// Search segment author:btummala
	@PostMapping("/searchSegment")
	public List<OsiSegmentStructureHdrDTO> searchOsiSegmentHdr(
			@Valid @RequestBody OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		log.debug("REST request to get searched OsiSegmentStructureHdrDTO");
		List<OsiSegmentStructureHdrDTO> result = null;
		try {
			if (osiSegmentStructureHdrDTO != null) {
				AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
				result = osiSegmentStructureService.searchSegment(osiSegmentStructureHdrDTO,
						auth.getOsiUserDTO().getBusinessGroupId());
			} else {
				result = new ArrayList<OsiSegmentStructureHdrDTO>();
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} /*
			 * catch (Exception e) { e.printStackTrace(); }
			 */
		return result;
	}
	
	@PostMapping("/segment-already-inuse")
	public OsiSegmentStructureHdrDTO segmentIsInuse(
			@Valid @RequestBody OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws URISyntaxException, RestServiceException {
		log.debug("REST request to get searched OsiSegmentStructureHdrDTO");
		OsiSegmentStructureHdrDTO result = null;
		try {
			if (osiSegmentStructureHdrDTO != null) {
				AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
				result = osiSegmentStructureService.isSegmentUsedinCategoryOrCoa(osiSegmentStructureHdrDTO.getSegmentStructureHdrId(), auth.getOsiUserDTO().getBusinessGroupId(), 
						osiSegmentStructureHdrDTO.getSegHdrName());
			} 
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} /*
			 * catch (Exception e) { e.printStackTrace(); }
			 */
		return result;
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
