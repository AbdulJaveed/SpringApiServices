package com.osi.urm.web.rest;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.common.CommonService;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.OsiUserOperationExclService;
import com.osi.urm.service.dto.OsiUserOperationExclDTO;
import com.osi.urm.web.rest.util.SuccessResponse;

@RestController
@RequestMapping("/api")
public class OsiUserOperationExclResource {
	
	private final Logger log = LoggerFactory.getLogger(OsiUserOperationExclResource.class);
	
	@Autowired
	private OsiUserOperationExclService osiUserOperationExclService;
	
	@Autowired
	private AuthTokenStore authTokenStore;
	
	@Autowired
	private CommonService cs;
	
	@Autowired
	private Environment env;
	
	@PostMapping("/osi-user-operation-excl")
	public ResponseEntity<SuccessResponse> createOsiUserOperationExcl(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@Valid @RequestBody List<OsiUserOperationExclDTO> osiUserOperationExclDTO)
			throws RestServiceException {
		log.debug("REST request to update OsiUserOperationExcl : {}", osiUserOperationExclDTO);
		SuccessResponse successResponse = null;
		try {
			AuthorizationToken auth = authTokenStore
					.retrieveToken(authToken);

			
			if(osiUserOperationExclDTO.size() > 0)
				osiUserOperationExclService.deleteByEmployeeId((int)osiUserOperationExclDTO.get(0).getEmployeeId());
			for(OsiUserOperationExclDTO osiUserOperation: osiUserOperationExclDTO){
				
				osiUserOperation.setCreatedBy(auth.getOsiUserDTO().getId());;
				osiUserOperation.setUpdatedBy(auth.getOsiUserDTO().getId());
				osiUserOperation.setCreatedDate(cs.getCurrentDateinUTC());
				osiUserOperation.setUpdatedDate(cs.getCurrentDateinUTC());
				osiUserOperation.setBusinessGroupId(auth.getOsiUserDTO().getBusinessGroupId());

				OsiUserOperationExclDTO result = osiUserOperationExclService.save(osiUserOperation, osiUserOperation.getBusinessGroupId());
				
				if (result != null) {
					successResponse = new SuccessResponse();
					successResponse.setHttpStatus(HttpStatus.OK.value());
					successResponse.setMessage(env.getProperty("MSG_1038"));
				}
			}
		}
		 catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}
	
	/*@GetMapping("/osi-user-operation-excl/{id}")
	public ResponseEntity<List<OsiUserOperationExcl>> getOsiExcludedOperation(@PathVariable Integer id) {
		
		List<OsiUserOperationExcl>	osiUserOperationExclusions = osiUserOperationExclService.findOne(id);
		
		return new ResponseEntity<List<OsiUserOperationExcl>>(osiUserOperationExclusions, HttpStatus.OK);
	}*/
	@GetMapping("/osi-user-operation-excl/{id}/{userId}")
	public ResponseEntity<String> getOsiExcludedOperation(@PathVariable Integer id, @PathVariable Integer userId) throws Exception {
		
		List<OsiUserOperationExclDTO> osiUserOperationExclusions = null;
		try {
			osiUserOperationExclusions = osiUserOperationExclService.findOne(id, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(toJsonString(osiUserOperationExclusions));
		return new ResponseEntity<String>(toJsonString(osiUserOperationExclusions), HttpStatus.OK);
	}
	
	@GetMapping("/osi-all-user-operation-excl/{id}")
	public ResponseEntity<String> findAllOsiExcludedOperation(@PathVariable Integer id) throws Exception {
		
		List<OsiUserOperationExclDTO> osiUserOperationExclusions = null;
		try {
			osiUserOperationExclusions = osiUserOperationExclService.findByEmployeeId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(toJsonString(osiUserOperationExclusions));
		return new ResponseEntity<String>(toJsonString(osiUserOperationExclusions), HttpStatus.OK);
	}
	
	/**
     * DELETE  /osi-users/:id : delete the "id" osiUser.
     *
     * @param id the id of the OsiUser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/osi-user-operation-excl/{funcId}/{userId}")
    public ResponseEntity<Void> deleteOsiUser(@PathVariable("funcId") Integer funcId,@PathVariable("userId") Integer userId, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) {
        try {
        	AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiUserOperationExclService.deleteByUserId(userId, funcId, auth.getOsiUserDTO().getBusinessGroupId());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ResponseEntity.ok()
        		.build();
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
	public static String toJsonString(Object object) throws Exception{
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		response = mapper.writeValueAsString(object);
		return response;
	}
    
}
