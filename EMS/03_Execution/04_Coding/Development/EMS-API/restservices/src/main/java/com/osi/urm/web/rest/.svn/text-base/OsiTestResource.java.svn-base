package com.osi.urm.web.rest;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.web.rest.util.SuccessResponse;

/**
 * REST controller for managing OsiLogin.
 */
@RestController
@RequestMapping("/api")
@PropertySource("classpath:/errorOrSuccessMsg.properties")
public class OsiTestResource {
	@Autowired
	private Environment env;
    @GetMapping("/testservice")
	public ResponseEntity<SuccessResponse> testServices(){
    	SuccessResponse successResponse = new SuccessResponse();
    	successResponse.setMessage("Services are up and running....");
    	successResponse.setHttpStatus(HttpStatus.OK.value());
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}
    
    @GetMapping("/testdatabase")
	public ResponseEntity<SuccessResponse> testDatabase() throws RestServiceException{
    	SuccessResponse successResponse = null;
    	try{
    		Class.forName(env.getProperty("spring.datasource.driverClassName"));  
    		Connection con=DriverManager.getConnection(  
    				env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username") ,env.getProperty("spring.datasource.password"));
    		successResponse = new SuccessResponse();
        	successResponse.setMessage("Database got connected... connection object is : "+con);
        	successResponse.setHttpStatus(HttpStatus.OK.value());
        	if(con!=null)
        		con.close();
    	}catch (Exception e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), "Unable to connect to the database ", null, e.getMessage());
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
