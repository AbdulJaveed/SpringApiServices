package com.osi.ems.web.rest;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.common.CommonService;
import com.osi.ems.service.OsiEmpBankDetailsService;
import com.osi.ems.service.dto.OsiEmpBankDetailsDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;


@RestController
@RequestMapping("/api")
public class OsiEmpBankDetailsResource {

    private final Logger log = LoggerFactory.getLogger(OsiEmpBankDetailsResource.class);
        
    
    @Autowired
    private OsiEmpBankDetailsService osiEmpBankDetailsService;
    
    @Autowired
	private AuthTokenStore authTokenStore;
   
    @Autowired
	private Environment env;

    @Autowired
    private CommonService commonService;
    
    @GetMapping("/bankDetailsList")
    public ResponseEntity<List<OsiEmpBankDetailsDTO>> getAllOsiBankDetailsList(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
    		 throws RestServiceException, DataAccessException, BusinessException {
        log.debug("REST request to get a page of OsiFunctions");
    	log.info("getAllOsiBankDetailsList : Begin");
        /*Page<OsiFunctionsDTO> page = osiFunctionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/functions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);*/
        List<OsiEmpBankDetailsDTO> osiEmpBankDetailsDTOList = null;
		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
		try{
			osiEmpBankDetailsDTOList = osiEmpBankDetailsService.getallBankDetails();
			
		} catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the osi bank information");
		}
		log.info("getAllOsiBankDetailsList : End");
		return ResponseEntity.ok().body(osiEmpBankDetailsDTOList);
    }
    
    @PostMapping("/empBankDetailsList")
    public ResponseEntity<List<OsiEmpBankDetailsDTO>> getEmpBankDetailsList(@RequestBody String inputObject, 
			 																@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			 																throws RestServiceException, DataAccessException, BusinessException {
        log.debug("REST request to get a page of OsiFunctions");
        /*Page<OsiFunctionsDTO> page = osiFunctionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/functions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);*/
        List<OsiEmpBankDetailsDTO> osiEmpBankDetailsDTOList = null;
    	log.info("getEmpBankDetailsList : Begin");
		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
		try{
			osiEmpBankDetailsDTOList = osiEmpBankDetailsService.getAllBankDetailsByEmpIdAndSearchDate(inputObject);
			
		} catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the osi bank information");
		}
		log.info("getEmpBankDetailsList : End");
		return ResponseEntity.ok().body(osiEmpBankDetailsDTOList);
		
    }
   
    
    @PostMapping("/saveEmpBankDetails")
	public ResponseEntity<OsiEmpBankDetailsDTO> saveEmpBankDetails(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiEmpBankDetailsDTO osiEmpBankDetailsDTO) throws RestServiceException, BusinessException {
		log.debug("REST request to update OsiResponsibility : {}", osiEmpBankDetailsDTO);
		SuccessResponse successResponse = null;
		OsiEmpBankDetailsDTO result = null;
		log.info("saveEmpBankDetails : Begin");
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			//osiEmpBankDetailsDTO.setCreatedDate(new Date());
			//osiEmpBankDetailsDTO.setCreatedDate(commonService.getCurrentDateinUTC());
			osiEmpBankDetailsDTO.setCreatedBy(auth.getOsiUserDTO().getId());
			result = osiEmpBankDetailsService.saveEmpBankDetails(osiEmpBankDetailsDTO, auth.getOsiUserDTO().getId());
			if (result != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1046"));
			}
		} catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while saving the osi bank information");
		}
		log.info("saveEmpBankDetails : End");
		return ResponseEntity.ok().body(result);
	}
    
    
    @GetMapping("/getEmpBankDetailsById/{id}")
    public ResponseEntity<OsiEmpBankDetailsDTO>  getEmpBankDetailById(@PathVariable Integer id, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException, DataAccessException, BusinessException {
    	OsiEmpBankDetailsDTO osiEmpBankDetailsDTO = null;
    	AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    	log.info("getEmpBankDetailById : Begin");
    	try{
    		osiEmpBankDetailsDTO = osiEmpBankDetailsService.getEmpBankDetailById(id);
    	} catch (BusinessException e) {
			log.error("Error Occured "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while retrieving the osi bank information");
		}
		log.info("getEmpBankDetailById : End");
    	return ResponseEntity.ok().body(osiEmpBankDetailsDTO);
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
