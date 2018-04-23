package com.osi.urm.web.rest;

import java.net.URISyntaxException;
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

import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.OsiUserFuncExclService;
import com.osi.urm.service.dto.OsiUserFuncExclDTO;
import com.osi.urm.web.rest.util.SuccessResponse;


/**
 * REST controller for managing OsiFunction.
 */
@RestController
@RequestMapping("/api")
public class OsiUserFuncExclResource {

    private final Logger log = LoggerFactory.getLogger(OsiUserFuncExclResource.class);
        
    @Autowired
    private OsiUserFuncExclService osiUserFuncExclService;
    
    @Autowired
	private AuthTokenStore authTokenStore;
    
    @Autowired
	private Environment env;
    
    /**
     * POST  /osi-functions : Create a list of OsiUserFuncExcl
     * @param osiUserFuncExclDTOList the osiFunctionsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new osiFunctionsDTO, or with status 400 (Bad Request) if the osiFunction has already an ID
     * @throws RestServiceException
     */
    
	@PostMapping("/osi_user_func_excl")
	public ResponseEntity<SuccessResponse> createOsiFunction(
			@Valid @RequestBody List<OsiUserFuncExclDTO> osiUserFuncExclDTOList,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException {
		log.debug("REST request to update OsiResponsibility : {}",
				osiUserFuncExclDTOList);
		SuccessResponse successResponse = null;
		try {
			
			AuthorizationToken auth = authTokenStore
					.retrieveToken(authToken);
			System.out.println(" === = "+auth.getOsiUserDTO().getId());
			if(osiUserFuncExclDTOList.size() > 0)
				osiUserFuncExclService.deleteByEmployeeId((int)osiUserFuncExclDTOList.get(0).getEmployeeId());
			
		for (OsiUserFuncExclDTO osiUserFuncExclDTO : osiUserFuncExclDTOList) {
			
			osiUserFuncExclDTO.setUpdatedBy(auth.getOsiUserDTO().getId());;
			/*osiUserFuncExclDTO.setCreatedDate(new Date());*/
			
			osiUserFuncExclDTO.setBusinessGroupId(auth.getOsiUserDTO().getBusinessGroupId());
				OsiUserFuncExclDTO result = osiUserFuncExclService.save(osiUserFuncExclDTO,osiUserFuncExclDTO.getBusinessGroupId());
				if (result != null) {
					successResponse = new SuccessResponse();
					successResponse.setHttpStatus(HttpStatus.OK.value());
					successResponse.setMessage(env.getProperty("MSG_1037"));
				}
			
		}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					env.getProperty(e.getErrorCode()), e.getErrorCode(),
					e.getSystemMessage());
		}

		return new ResponseEntity<SuccessResponse>(successResponse,
				HttpStatus.OK);
	}
        
   /* @PostMapping("/osi_user_func_excl")
    public ResponseEntity<OsiUserFuncExcl> createOsiUserOperationExcl(@Valid @RequestBody OsiUserFuncExcl osiUserFuncExcl)
			throws URISyntaxException {
		
    	OsiUserFuncExcl result = null;
		try {
			result = osiUserFuncExclService.save(osiUserFuncExcl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.created(new URI("/api/osi_user_func_excl/" + result.getId())).body(result);
	}*/
    /**
     * PUT  /osi-functions : Updates an existing osiFunction.
     *
     * @param osiFunctionsDTO the osiFunctionsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated osiFunctionsDTO,
     * or with status 400 (Bad Request) if the osiFunctionsDTO is not valid,
     * or with status 500 (Internal Server Error) if the osiFunctionsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
 
    /**
     * GET  /osi-functions : get all the osiFunctions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of osiFunctions in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    
    /*@GetMapping("/osi_user_func_excl")
    public List<OsiFunctionsDTO> getAllOsiFunctions(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of OsiFunctions");
        return osiFunctionServiceImpl.findAll();
    }*/
    
    
    @GetMapping("/osi_user_func_excl/{id}")
    public ResponseEntity<List<OsiUserFuncExclDTO>> getOsiExcludedFunction(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable Integer id) throws RestServiceException {
		
		List<OsiUserFuncExclDTO> osiUserFunctionExclusionList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiUserFunctionExclusionList = osiUserFuncExclService.findOsiUserFuncExclByUserId(auth.getOsiUserDTO().getBusinessGroupId(), id);
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		
		return ResponseEntity.ok().body(osiUserFunctionExclusionList);
	}
    
    @DeleteMapping("/osi_user_func_excl/{id}")
    public ResponseEntity<Void> deleteOsiFunction(@PathVariable Integer id) {
        log.debug("REST request to delete OsiFunction : {}", id);
        osiUserFuncExclService.delete(id);
        return ResponseEntity.ok().build();
    }
    
    /*@DeleteMapping("/osi_user_func_excl/{id}")
    public ResponseEntity<Void> deleteOsiFunctionExcl(@PathVariable Integer id, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) {
        log.debug("REST request to delete OsiFunctionExcl : {}", id);
        AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
        osiUserFuncExclService.deleteByUserId(auth.getOsiUserDTO().getId(),id);
        return ResponseEntity.ok().build();
    }*/
  
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
