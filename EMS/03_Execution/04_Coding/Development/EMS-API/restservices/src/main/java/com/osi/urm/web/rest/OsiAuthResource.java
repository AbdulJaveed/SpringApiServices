package com.osi.urm.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.urm.exception.AuthException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;

/**
 * REST controller for managing OsiLogin.
 */
@RestController
@RequestMapping("/api")
public class OsiAuthResource {

	@Autowired
	private Environment env;
    private final Logger log = LoggerFactory.getLogger(OsiAuthResource.class);
        
    @GetMapping("/unauthorized")
	public ResponseEntity<ErrorResponse> exceptionHandler() throws RestServiceException{
		ErrorResponse error;
		try {
			error = new ErrorResponse();
			error.setErrorCode("ERR_2001");
			error.setHttpStatus(HttpStatus.UNAUTHORIZED.value());
			error.setErrorMessage(env.getProperty("ERR_2001"));
		} catch (AuthException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), e.getMessage() ,e.getErrorCode(), null);
		}
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNAUTHORIZED);
	}
}
