package com.osi.ems.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.domain.OsiCategories;
import com.osi.ems.service.OsiFlexiFieldService;
import com.osi.ems.service.dto.OsiFlexiFieldDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;


@RestController
@RequestMapping(value="/api/v1/flexidata")
public class FlexiFieldController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(FlexiFieldController.class);

	@Autowired
	OsiFlexiFieldService flexiFieldService;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/{categoryName}/{orgId}")
	public ResponseEntity<List<OsiFlexiFieldDTO>> getFlexiFields(@PathVariable("categoryName") String categoryName, @PathVariable("orgId") int orgId) throws RestServiceException {
		List<OsiFlexiFieldDTO> flexifieldList = null;
	 	LOGGER.info("getFlexiFields : Begin");
		try {
			flexifieldList = flexiFieldService.getFlexiFields(categoryName, orgId);
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the flex fields data");
		}
    	LOGGER.info("getFlexiFields : End");
		return ResponseEntity.ok().body(flexifieldList);
	}
	
	
	@GetMapping("/functionInfo/{functionId}/{orgId}")
	public ResponseEntity<OsiCategories> getFlexiCategoryType(@PathVariable("functionId") String functionId, @PathVariable("orgId") int orgId) throws RestServiceException {
		OsiCategories categories = null;
		LOGGER.info("getFlexiFields : Begin");
		try {
			categories = flexiFieldService.getFlexiFieldsName(functionId, orgId);
		}catch (BusinessException e) {
			LOGGER.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			LOGGER.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting the flex fields function information");
		}
    	LOGGER.info("getFlexiFields : End");
		return ResponseEntity.ok().body(categories);
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
