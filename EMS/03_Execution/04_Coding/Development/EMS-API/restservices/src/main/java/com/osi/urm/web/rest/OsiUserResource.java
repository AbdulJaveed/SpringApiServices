package com.osi.urm.web.rest;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.osi.urm.config.AppConfig;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.OsiAttachmentsService;
import com.osi.urm.service.OsiUserService;
import com.osi.urm.service.dto.OsiAttachmentsDTO;
import com.osi.urm.service.dto.OsiRespUserDTO;
import com.osi.urm.service.dto.OsiUserDTO;
import com.osi.urm.service.dto.OsiUserFuncExclDTO;
import com.osi.urm.service.dto.OsiUserOperationExclDTO;
import com.osi.urm.web.rest.util.SuccessResponse;

/**
 * REST controller for managing OsiUser.
 */
@RestController
@RequestMapping("/api")
public class OsiUserResource {

	private final Logger log = LoggerFactory.getLogger(OsiUserResource.class);

	@Autowired
	private OsiUserService osiUserService;

	@Autowired
	OsiAttachmentsService osiAttachmentsService;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private Environment env;
	
	@PostMapping("/searchedUsers")
	public ResponseEntity<List<OsiUserDTO>> getSearchedUsers(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiUserDTO osiUserDTO) throws RestServiceException {

		List<OsiUserDTO> osiUserDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer businessGroupId = auth.getOsiUserDTO().getBusinessGroupId();
			osiUserDTOList = osiUserService.findUser(osiUserDTO, businessGroupId);
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return  ResponseEntity.ok().body(osiUserDTOList);
	}

	/**
	 * POST /users : Create a new osiUser.
	 *
	 * @param osiUserDTO
	 *            the osiUserDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new osiUserDTO, or with status 400 (Bad Request) if the osiUser
	 *         has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/users")
	public ResponseEntity<Integer> createOsiUser(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody OsiUserDTO osiUserDTO)
			throws RestServiceException {
		OsiUser osiuser=new OsiUser();
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiuser=osiUserService.save(osiUserDTO, auth.getOsiUserDTO().getId(), auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (DataAccessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return  new ResponseEntity<Integer>(osiuser.getUserId(), HttpStatus.OK);
	}

	/**
	 * PUT /users : Updates an existing osiUser.
	 *
	 * @param osiUserDTO
	 *            the osiUserDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         osiUserDTO, or with status 400 (Bad Request) if the osiUserDTO is
	 *         not valid, or with status 500 (Internal Server Error) if the
	 *         osiUserDTO couldnt be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/users")
	public ResponseEntity<Integer> updateOsiUser(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody OsiUserDTO osiUserDTO)
			throws RestServiceException {
		OsiUser osiuser=new OsiUser();
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			if (osiUserDTO.getId() != null) {
				osiUserDTO.setUpdatedBy(auth.getOsiUserDTO().getId());
				osiUserDTO.setUpdatedDate(new Date());
				osiuser=osiUserService.save(osiUserDTO, auth.getOsiUserDTO().getId(),
						auth.getOsiUserDTO().getBusinessGroupId());
			}
		} catch (BusinessException e) {
			osiuser.setUserId(null);
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (DataAccessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<Integer>(osiuser.getUserId(), HttpStatus.OK);
	}

	/**
	 * GET /users : get all the osiUsers.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of osiUsers
	 *         in body
	 * @throws URISyntaxException
	 *             if there is an error to generate the pagination HTTP headers
	 */
	
	@GetMapping("/usersInitially")
	public ResponseEntity<List<OsiUserDTO>> getUsersInitially(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		log.debug("REST request to get a page of OsiUsers");
		List<OsiUserDTO> OsiUserDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			OsiUserDTOList = osiUserService.findUserInitially(auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(OsiUserDTOList);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<OsiUserDTO>> getAllOsiUsers(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		log.debug("REST request to get a page of OsiUsers");
		List<OsiUserDTO> OsiUserDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			OsiUserDTOList = osiUserService.findAll(auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(OsiUserDTOList);
	}

	@GetMapping("/activeUsers")
	public ResponseEntity<List<OsiUserDTO>> getAllActiveUsers(
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		log.debug("REST request to get a page of OsiUsers");
		List<OsiUserDTO> OsiUserDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			OsiUserDTOList = osiUserService.findAllActiveUsers(auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(OsiUserDTOList);
	}
	
	/**
	 * GET /users/:id : get the "id" osiUser.
	 *
	 * @param id
	 *            the id of the osiUserDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         osiUserDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<OsiUserDTO> getOsiUser(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@PathVariable Integer id) throws RestServiceException {
		log.debug("REST request to get OsiUser : {}", id);
		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
		OsiUserDTO result = new OsiUserDTO();
		try {
			result = osiUserService.findOne(id, auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(result);
	}

	/**
	 * DELETE /users/:id : delete the "id" osiUser.
	 *
	 * @param id
	 *            the id of the osiUserDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@PostMapping("/deleteUsers")
	public ResponseEntity<SuccessResponse> deleteOsiUser(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiUserDTO osiUserDTO) throws RestServiceException {
		SuccessResponse successResponse = null;
		int count = 0;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			count = osiUserService.delete(osiUserDTO.getIds(), auth.getOsiUserDTO().getBusinessGroupId(),
					auth.getOsiUserDTO().getId());
			if (count > 0) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1035"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> uploadFile(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestParam("uploadfile") MultipartFile uploadfile, @RequestParam("userId") Integer userId) 
			throws RestServiceException {
		SuccessResponse successResponse = null;
		OsiAttachmentsDTO osiAttachmentDTO = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiAttachmentDTO = osiAttachmentsService.save(uploadfile, auth.getOsiUserDTO().getId(),
					auth.getOsiUserDTO().getBusinessGroupId(), userId);
			if (osiAttachmentDTO != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1033"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/getUserResponsibilities", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OsiRespUserDTO>> getUserResponsibilities(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,@RequestBody OsiUserDTO osiUserDTO) throws RestServiceException {
		log.debug("REST request to get user responsibilities");
		List<OsiRespUserDTO> osiRespUserDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiRespUserDTOList = osiUserService.findUserResponsibilities(osiUserDTO.getId(),auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiRespUserDTOList);
	}
	
	@RequestMapping(value = "/getUserFunctionExclusions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OsiUserFuncExclDTO>> getUserFunctionExclusions(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,@RequestBody OsiUserDTO osiUserDTO) throws RestServiceException {
		log.debug("REST request to get user function exclusions");
		List<OsiUserFuncExclDTO> osiUserFuncExclDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiUserFuncExclDTOList = osiUserService.getUserFunctionExclusions(osiUserDTO.getId(),auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiUserFuncExclDTOList);
	}
	
	@RequestMapping(value = "/getUserOperationExclusions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OsiUserOperationExclDTO>> getUserOperationExclusions(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,@RequestBody OsiUserDTO osiUserDTO) throws RestServiceException {
		log.debug("REST request to get user operation exclusions");
		List<OsiUserOperationExclDTO> osiUserOpExclDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiUserOpExclDTOList = osiUserService.getUserOperationExclusions(osiUserDTO.getId(),auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiUserOpExclDTOList);
	}
	
	@RequestMapping(value = "/getUserAttachments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OsiAttachmentsDTO>> getUserAttachments(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,@RequestBody OsiUserDTO osiUserDTO) throws RestServiceException {
		log.debug("REST request to get user categories");
		List<OsiAttachmentsDTO> osiUserAttachmentDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiUserAttachmentDTOList = osiUserService.getUserAttachments(osiUserDTO.getId(),auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiUserAttachmentDTOList);
	}
	
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OsiUserDTO> getUserInfo(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiUserDTO osiUserDTO) throws RestServiceException {
		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
		OsiUserDTO result = new OsiUserDTO();
		try {
			result = osiUserService.findUserById(osiUserDTO.getId(), auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(result);
	}
	
	
	@PutMapping("/updateUserProfile")
	public ResponseEntity<Integer> updateUserProfile(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody OsiUserDTO osiUserDTO)
			throws RestServiceException {
		OsiUser osiuser=new OsiUser();
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			if (osiUserDTO.getId() != null) {
				osiUserDTO.setUpdatedBy(auth.getOsiUserDTO().getId());
				osiUserDTO.setUpdatedDate(new Date());
				osiuser=osiUserService.updateUserProfile(osiUserDTO,auth.getOsiUserDTO().getBusinessGroupId());
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<Integer>(osiuser.getUserId(), HttpStatus.OK);
	}
	
	
	@PutMapping("/updateUserPassword")
	public ResponseEntity<Integer> updateUserPassword(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @RequestBody OsiUserDTO osiUserDTO)
			throws RestServiceException {
		Integer resultCount;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			resultCount=osiUserService.updatePassword(auth.getOsiUserDTO().getId(),auth.getOsiUserDTO().getBusinessGroupId(), osiUserDTO.getPassword());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<Integer>(resultCount, HttpStatus.OK);
	}

	
	@PutMapping("/resetUserPassword/{decodedPassword}")
	public ResponseEntity<Integer> resetUserPassword(@PathVariable String decodedPassword,
			@RequestBody OsiUserDTO osiUserDTO, @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws Exception {
		OsiUser osiuser = new OsiUser();
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			if (osiUserDTO.getId() != null) {
				osiuser = osiUserService.updateUserPassword(auth.getOsiUserDTO().getBusinessGroupId(),
						auth.getOsiUserDTO().getId(), osiUserDTO.getId(), osiUserDTO.getPassword());
			}
			/*if (osiUserDTO.getEmailId() != null) {
				sendEmail.sendMailWithPasswordAfterReset(osiUserDTO.getEmailId(), decodedPassword);
			}*/
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<Integer>(osiuser.getUserId(), HttpStatus.OK);
	}
	@ExceptionHandler({ RestServiceException.class, BusinessException.class })
	public ResponseEntity<ErrorResponse> exceptionHandler(RestServiceException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getErrorCode());
		error.setHttpStatus(ex.getHttpStatus());
		error.setErrorMessage(ex.getErrorMessage());
		error.setDeveloperMessage(ex.getDeveloperMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.valueOf(ex.getHttpStatus()));
	}

}
