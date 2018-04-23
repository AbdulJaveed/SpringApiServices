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
import com.osi.urm.service.OsiLookupTypesService;
import com.osi.urm.service.dto.OsiLookupTypesDTO;
import com.osi.urm.web.rest.util.SuccessResponse;

/**
 * REST controller for managing OsiLookupTypes.
 */
@RestController
@RequestMapping("/api")
public class OsiLookupResource {

    private final Logger log = LoggerFactory.getLogger(OsiLookupResource.class);
        
    @Autowired
    private OsiLookupTypesService osiLookupTypesService;

    @Autowired
	private AuthTokenStore authTokenStore;
    
    @Autowired
	private Environment env;
    /**
     * POST  /lookup-types : Create a new osiLookupTypes.
     *
     * @param osiLookupTypesDTO the osiLookupTypesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new osiLookupTypesDTO, or with status 400 (Bad Request) if the osiLookupTypes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/createLookup")
    public ResponseEntity<SuccessResponse> createOsiLookupTypes(@Valid @RequestBody OsiLookupTypesDTO osiLookupTypesDTO,
    		@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws URISyntaxException, RestServiceException {
        log.debug("REST request to save OsiLookupTypes : {}", osiLookupTypesDTO);
        if (osiLookupTypesDTO.getId() != null) {
			return ResponseEntity.badRequest().body(null);
		}
		SuccessResponse successResponse = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiLookupTypesDTO.setBusinessGroupId(auth.getOsiUserDTO().getBusinessGroupId());
			OsiLookupTypesDTO result = osiLookupTypesService.save(osiLookupTypesDTO,auth.getOsiUserDTO().getId().intValue());
			if (result.getId() > 0) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1006"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(),
					e.getSystemMessage());
			// e.printStackTrace();
		}/*catch (Exception e) {
			 e.printStackTrace();
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						e.getErrorCode(), env.getProperty(e.getErrorCode()),
						e.getSystemMessage());
				// e.printStackTrace();
			}*/
		// return ResponseEntity.created(
		// new URI("/api/menus/" + result.getId())).body(result);
		return new ResponseEntity<SuccessResponse>(successResponse,
				HttpStatus.OK);
    }

    /**
     * PUT  /lookup-types : Updates an existing osiLookupTypes.
     *
     * @param osiLookupTypesDTO the osiLookupTypesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated osiLookupTypesDTO,
     * or with status 400 (Bad Request) if the osiLookupTypesDTO is not valid,
     * or with status 500 (Internal Server Error) if the osiLookupTypesDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/editLookup")
    public ResponseEntity<SuccessResponse> updateOsiLookupTypes(@Valid @RequestBody OsiLookupTypesDTO osiLookupTypesDTO,
    		@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws URISyntaxException, RestServiceException {
        log.debug("REST request to update OsiLookupTypes : {}", osiLookupTypesDTO);
        if (osiLookupTypesDTO.getId() == null) {
			return ResponseEntity.badRequest().body(null);
		}
		SuccessResponse successResponse = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiLookupTypesDTO.setBusinessGroupId(auth.getOsiUserDTO().getBusinessGroupId());
			OsiLookupTypesDTO result = osiLookupTypesService.save(osiLookupTypesDTO,auth.getOsiUserDTO().getId().intValue());
			if (result.getId() > 0) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1006"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(),
					e.getSystemMessage());
			// e.printStackTrace();
		}/*catch (Exception e) {
			 e.printStackTrace();
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
						e.getErrorCode(), env.getProperty(e.getErrorCode()),
						e.getSystemMessage());
				// e.printStackTrace();
			}*/
		// return ResponseEntity.created(
		// new URI("/api/menus/" + result.getId())).body(result);
		return new ResponseEntity<SuccessResponse>(successResponse,
				HttpStatus.OK);
    }

    /**
     * GET  /lookup-types : get all the osiLookupTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of osiLookupTypes in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/getLookup")
    public List<OsiLookupTypesDTO> getAllOsiLookupTypes(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken)
        throws URISyntaxException, RestServiceException  {
    	log.debug("REST request to get all getAllLookUps");
    	List<OsiLookupTypesDTO> result = null;
    	try{    	
    	AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    	result= osiLookupTypesService.findAll(auth.getOsiUserDTO().getBusinessGroupId());
    	}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(),
					e.getSystemMessage());			
		}/*catch (Exception e) {
			 e.printStackTrace();				
		}*/
    	return result;
    }
    
    @GetMapping("/getActiveLookups")
    public List<OsiLookupTypesDTO> getAllActiveLookupTypes(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken)
        throws URISyntaxException, RestServiceException  {
    	log.debug("REST request to get all getAllLookUps");
    	List<OsiLookupTypesDTO> result = null;
    	try{    	
    	AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    	result= osiLookupTypesService.findAllActiveLookups(auth.getOsiUserDTO().getBusinessGroupId());
    	}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(),
					e.getSystemMessage());			
		}/*catch (Exception e) {
			 e.printStackTrace();				
		}*/
    	return result;
    }

    /**
     * GET  /lookup-types/:id : get the "id" osiLookupTypes.
     *
     * @param id the id of the osiLookupTypesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the osiLookupTypesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/getLookup/{id}")
    public ResponseEntity<OsiLookupTypesDTO> getOsiLookupTypes(@PathVariable Long id,@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken)
            throws URISyntaxException, RestServiceException  {
        log.debug("REST request to get OsiLookupTypes : {}", id);
        OsiLookupTypesDTO result = null;
    	try{    	
    	AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    	result= osiLookupTypesService.findOne(id,auth.getOsiUserDTO().getBusinessGroupId());
    	}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(),
					e.getSystemMessage());			
		}/*catch (Exception e) {
			 e.printStackTrace();				
		}*/
    	return ResponseEntity.ok().body(result);
    }
    @GetMapping("/getLookupByLookupName/{lookupName}")
    public ResponseEntity<OsiLookupTypesDTO> getOsiLookupTypesByLookupName(@PathVariable String lookupName)
            throws URISyntaxException, RestServiceException  {
        log.debug("REST request to get OsiLookupTypes : {}", lookupName);
        OsiLookupTypesDTO result = null;
    	try{    	
    	result= osiLookupTypesService.findOsiLookupValuesesByLookupName(lookupName);
    	}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(),
					e.getSystemMessage());			
		}/*catch (Exception e) {
			 e.printStackTrace();				
		}*/
    	return ResponseEntity.ok().body(result);
    }

    /**
     * DELETE  /lookup-types/:id : delete the "id" osiLookupTypes.
     *
     * @param id the id of the osiLookupTypesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @PostMapping("/deleteLookups")
    public ResponseEntity<SuccessResponse> deleteOsiLookupTypes(@RequestBody OsiLookupTypesDTO osiLookupTypesDTO,
    		@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws URISyntaxException, RestServiceException  {
        log.debug("REST request to delete OsiLookupTypes : {}", osiLookupTypesDTO.getLookupTypeIds());
        SuccessResponse successResponse = null;
        int count=0;
        try{
        	if(osiLookupTypesDTO.getLookupTypeIds().size()>0){
        		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
        		count=osiLookupTypesService.delete(osiLookupTypesDTO.getLookupTypeIds(),auth.getOsiUserDTO());
        		System.out.println("Delete Count:- " + count);
				if (count > 0) {
					successResponse = new SuccessResponse();
					successResponse.setHttpStatus(HttpStatus.OK.value());
					successResponse.setMessage(env.getProperty("MSG_1006"));
				}
        	}else{
        		throw new BusinessException("ERR_1010", null);
        	}
        }
        catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(),
					e.getSystemMessage());
		}
        
        return new ResponseEntity<SuccessResponse>(successResponse,
				HttpStatus.OK);
    }
    
    //Search lookup author:btummala
    @PostMapping("/searchLookup")
	public List<OsiLookupTypesDTO> searchOsiLookup(@Valid @RequestBody OsiLookupTypesDTO osiLookupTypesDTO,@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws URISyntaxException, RestServiceException {
    	log.debug("REST request to get searched OsiLookup");
        List<OsiLookupTypesDTO> loopuptypes = null;
        try{
        	if(osiLookupTypesDTO!=null){
	        	AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
	        	loopuptypes=osiLookupTypesService.searchLookup(osiLookupTypesDTO,auth.getOsiUserDTO().getBusinessGroupId());
        	}else{
        		loopuptypes = new ArrayList<OsiLookupTypesDTO>();
        	}
        }catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(),
					e.getSystemMessage());			
		}/*catch (Exception e) {
			 e.printStackTrace();				
		}*/
        return loopuptypes;
    }
    
    
    @GetMapping("/lookup-status-flag/{id}")
	public ResponseEntity<OsiLookupTypesDTO> isLookupUsedinCategory(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken, @PathVariable Long id) throws URISyntaxException, RestServiceException {
    	if(id==null){
    		return ResponseEntity.badRequest().body(null);
    	}
    	OsiLookupTypesDTO result = null;
        try{
        	AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
	        result=osiLookupTypesService.isLookupUsedinCategory(id,auth.getOsiUserDTO().getBusinessGroupId());        	
        }catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(),
					e.getSystemMessage());
		}
        return ResponseEntity.ok().body(result);
    }
    
    @ExceptionHandler(RestServiceException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(
			RestServiceException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getErrorCode());
		error.setHttpStatus(ex.getHttpStatus());
		error.setErrorMessage(ex.getErrorMessage());
		error.setDeveloperMessage(ex.getDeveloperMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

}
