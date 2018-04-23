package com.osi.urm.web.rest;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.osi.urm.service.OsiLoginService;
import com.osi.urm.service.dto.OsiUserDTO;
import com.osi.urm.web.rest.util.SuccessResponse;

/**
 * REST controller for managing OsiLogin.
 */
@RestController
@RequestMapping("/api")
public class OsiLoginResource {

    private final Logger log = Logger.getLogger(OsiLoginResource.class);
        
    @Autowired
    private OsiLoginService osiLoginService;
	@Autowired
	private Environment env;
	@Autowired
	private AuthTokenStore authTokenStore;
    
    @PostMapping("/validate-login")
    public OsiUserDTO validateLogin(@Valid @RequestBody OsiUserDTO osiUserDTO) throws RestServiceException{
        log.debug("REST request to get all validateLogin");
        try {
			osiUserDTO = osiLoginService.validateLogin(osiUserDTO);
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
        return osiUserDTO;
    }
    @GetMapping("/logout")
    public ResponseEntity<SuccessResponse> logout(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException{
        log.debug("REST request to get all SuccessResponse");
        SuccessResponse successResponse = null;
        try {
        	AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
        	authTokenStore.removeToken(authToken);
        	osiLoginService.logout(auth.getOsiUserDTO().getId(), authToken);
            successResponse = new SuccessResponse();
            successResponse.setHttpStatus(HttpStatus.OK.value());
            successResponse.setMessage(env.getProperty("MSG_1000"));
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
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
