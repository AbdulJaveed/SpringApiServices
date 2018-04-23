package com.osi.urm.web.rest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.common.CommonService;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.OsiFunctionService;
import com.osi.urm.service.dto.OsiFunctionsDTO;
import com.osi.urm.service.dto.OsiRespUserDTO;
import com.osi.urm.web.rest.util.SuccessResponse;


/**
 * REST controller for managing OsiFunction.
 */
@RestController
@RequestMapping("/api")
public class OsiFunctionResource {

    private final Logger log = LoggerFactory.getLogger(OsiFunctionResource.class);
        
    @Autowired
    private OsiFunctionService osiFunctionService;
    
    @Autowired
	private AuthTokenStore authTokenStore;
   
    @Autowired
	private Environment env;
    
    @Autowired
	private CommonService commonService;

    /**
     * POST  /functions : Create a new osiFunction.
     *
     * @param osiFunctionsDTO the osiFunctionsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new osiFunctionsDTO, or with status 400 (Bad Request) if the osiFunction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    
    @PostMapping("/createFunction")
	public ResponseEntity<OsiFunctionsDTO> createOsiFunction(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiFunctionsDTO osiFunctionsDTO) throws RestServiceException {
		log.debug("REST request to update OsiResponsibility : {}", osiFunctionsDTO);
		SuccessResponse successResponse = null;
		OsiFunctionsDTO result = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			//osiFunctionsDTO.setCreatedDate(new Date());
			osiFunctionsDTO.setCreatedDate(commonService.getCurrentDateinUTC());
			osiFunctionsDTO.setCreatedBy(auth.getOsiUserDTO().getId());
			osiFunctionsDTO.setUpdatedBy(auth.getOsiUserDTO().getId());
			//osiFunctionsDTO.setUpdatedDate(new Date());
			osiFunctionsDTO.setUpdatedDate(commonService.getCurrentDateinUTC());
			result = osiFunctionService.save(osiFunctionsDTO, auth.getOsiUserDTO().getId(), auth.getOsiUserDTO().getBusinessGroupId());
			if (result != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1046"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(result);
	}

    /**
     * PUT  /functions : Updates an existing osiFunction.
     *
     * @param osiFunctionsDTO the osiFunctionsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated osiFunctionsDTO,
     * or with status 400 (Bad Request) if the osiFunctionsDTO is not valid,
     * or with status 500 (Internal Server Error) if the osiFunctionsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */


    @PutMapping("/updateFunction")
	public ResponseEntity<OsiFunctionsDTO> updateOsiFunction(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiFunctionsDTO osiFunctionsDTO) throws URISyntaxException, RestServiceException, BusinessException {
		SuccessResponse successResponse = null;
		OsiFunctionsDTO result = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			//osiFunctionsDTO.setUpdatedDate(new Date());
			osiFunctionsDTO.setUpdatedDate(commonService.getCurrentDateinUTC());
			osiFunctionsDTO.setUpdatedBy(auth.getOsiUserDTO().getId());
			result = osiFunctionService.save(osiFunctionsDTO,  auth.getOsiUserDTO().getId(), auth.getOsiUserDTO().getBusinessGroupId());
			if (result != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1046"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(result);
	}

 
    /**
     * GET  /functions : get all the osiFunctions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of osiFunctions in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/functions")
    public ResponseEntity<List<OsiFunctionsDTO>> getAllOsiFunctions(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
    		 throws RestServiceException {
        log.debug("REST request to get a page of OsiFunctions");
        /*Page<OsiFunctionsDTO> page = osiFunctionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/functions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);*/
        List<OsiFunctionsDTO> osiFunctionDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiFunctionDTOList = osiFunctionService.findAll(auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiFunctionDTOList);
    }
    
    @GetMapping("/functionsInitially/list")
    public ResponseEntity<List<OsiFunctionsDTO>> getOsiFunctionsInitially(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
    		 throws RestServiceException {
        log.debug("REST request to get a page of OsiFunctions");
        
        List<OsiFunctionsDTO> osiFunctionDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiFunctionDTOList = osiFunctionService.findFunctionsInitially(auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiFunctionDTOList);
    }
    
    @GetMapping("/functions/list")
    public ResponseEntity<List<OsiFunctionsDTO>> getAllOsiFunctionsForList(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
    		 throws RestServiceException {
        log.debug("REST request to get a page of OsiFunctions");
        /*Page<OsiFunctionsDTO> page = osiFunctionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/functions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);*/
        List<OsiFunctionsDTO> osiFunctionDTOList = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiFunctionDTOList = osiFunctionService.findAllList(auth.getOsiUserDTO().getBusinessGroupId());
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return ResponseEntity.ok().body(osiFunctionDTOList);
    }
    /*@GetMapping("/functionsByRespIds")
    public ResponseEntity<List<OsiFunctionsDTO>> getOsiFunctionsByRespIds(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException, BusinessException {
        List<OsiFunctionsDTO> osiFunctionDTOList = null;
        List<Integer> userRespIds = new ArrayList<Integer>();
        userRespIds.add(1350);
        //userRespIds.add(1252);
        AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
		osiFunctionDTOList = osiFunctionService.findAllByRespIds(auth.getOsiUserDTO().getBusinessGroupId(),userRespIds);
		return ResponseEntity.ok().body(osiFunctionDTOList);
    }*/
    
    @PostMapping("/functionsByRespIds")
    public ResponseEntity<List<OsiFunctionsDTO>> getOsiFunctionsByRespIds(@Valid  @RequestBody OsiRespUserDTO osiRespUserDTO, @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException, BusinessException {
        List<OsiFunctionsDTO> osiFunctionDTOList = new ArrayList<OsiFunctionsDTO>();
        AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
        if(osiRespUserDTO.getUserRespIds().isEmpty()) {
        	return ResponseEntity.ok().body(osiFunctionDTOList);
        }
		osiFunctionDTOList = osiFunctionService.findAllByRespIds(auth.getOsiUserDTO().getBusinessGroupId(),osiRespUserDTO.getUserRespIds());
		return ResponseEntity.ok().body(osiFunctionDTOList);
    }
    

    /**
     * GET  /functions/:id : get the "id" osiFunction.
     *
     * @param id the id of the osiFunctionsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the osiFunctionsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/functions/{id}")
    public ResponseEntity<OsiFunctionsDTO> getOsiFunction(@PathVariable Integer id) {
        log.debug("REST request to get OsiFunction : {}", id);
        OsiFunctionsDTO osiFunctionsDTO = osiFunctionService.findOne(id);
       /* return Optional.ofNullable(osiFunctionsDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));*/
        return null;
    }

    /**
     * DELETE  /functions/:id : delete the "id" osiFunction.
     *
     * @param id the id of the osiFunctionsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/functions/{id}")
    public ResponseEntity<Void> deleteOsiFunction(@PathVariable Integer id) {
        log.debug("REST request to delete OsiFunction : {}", id);
        osiFunctionService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/deleteFunction")
    public ResponseEntity<SuccessResponse> deleteFunction(@Valid @RequestBody Integer functionId, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws URISyntaxException, RestServiceException {
    	int count = 0;
    	System.out.println("In deleteMenuEntry Rest==========================>"+functionId);
    	SuccessResponse successResponse = null;
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			if (functionId !=null) {
				count = osiFunctionService.deleteFunction(functionId, auth.getOsiUserDTO().getBusinessGroupId(), auth.getOsiUserDTO().getId());
				if (count > 0) {
					successResponse = new SuccessResponse();
					successResponse.setHttpStatus(HttpStatus.OK.value());
					successResponse.setMessage(env.getProperty("MSG_1036"));
				}
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					e.getErrorCode(), env.getProperty(e.getErrorCode()),
					e.getSystemMessage());
		}
    	return new ResponseEntity<SuccessResponse>(successResponse,
				HttpStatus.OK);
    	
    }
    
    @PostMapping("/searchFunction")
   	public ResponseEntity<List<OsiFunctionsDTO>> searchFunction(
   			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
   			@RequestBody OsiFunctionsDTO osiFunctionsDTO) throws RestServiceException {
   		log.debug("REST request to update OsiResponsibility : {}", osiFunctionsDTO);
   		List<OsiFunctionsDTO> osiFunctionsDTOs=new ArrayList<OsiFunctionsDTO>();
   		String funcName=osiFunctionsDTO.getFuncName();
   		String funcValue= osiFunctionsDTO.getFuncValue();
   		try {
   			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
   			osiFunctionsDTO.setCreatedDate(new Date());
   			osiFunctionsDTO.setCreatedBy(auth.getOsiUserDTO().getId());
   			
   			osiFunctionsDTOs = osiFunctionService.search(funcName,funcValue);
   			
   		} catch (BusinessException e) {
   			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
   					e.getErrorCode(), e.getSystemMessage());
   		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		return ResponseEntity.ok().body(osiFunctionsDTOs);
   	}
    
    @GetMapping("/userFunctions/{userId}")
    public ResponseEntity<List<OsiFunctionsDTO>> getUserFunctionsList(@PathVariable("userId") Integer userId) throws RestServiceException {
    	log.info(" ## getUserFunctionsList : Begin");
    	List<OsiFunctionsDTO> osiFunctionsDTOs=new ArrayList<OsiFunctionsDTO>();
    	try {
    		osiFunctionsDTOs = osiFunctionService.findUserFunctionsList(userId);
    	} catch (BusinessException e) {
   			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
   					e.getErrorCode(), e.getSystemMessage());
   		}
    	log.info(" ## getUserFunctionsList : End");
    	return ResponseEntity.ok().body(osiFunctionsDTOs);
    }
    @ExceptionHandler({RestServiceException.class,DataAccessException.class, BusinessException.class})
   	public ResponseEntity<ErrorResponse> exceptionHandler(RestServiceException ex) {
   		ErrorResponse error = new ErrorResponse();
   		error.setErrorCode(ex.getErrorCode());
   		error.setHttpStatus(ex.getHttpStatus());
   		error.setErrorMessage(ex.getErrorMessage());
   		error.setDeveloperMessage(ex.getDeveloperMessage());
   		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
   	}

}
