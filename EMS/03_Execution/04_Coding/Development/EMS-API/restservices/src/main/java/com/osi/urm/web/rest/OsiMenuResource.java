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
import com.osi.urm.domain.OsiMenus;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.OsiMenuService;
import com.osi.urm.service.dto.OsiMenusDTO;
import com.osi.urm.web.rest.util.SuccessResponse;

/**
 * REST controller for managing OsiMenu.
 */
@RestController
@RequestMapping("/api/menus")
public class OsiMenuResource {

    private final Logger log = LoggerFactory.getLogger(OsiMenuResource.class);
        
    @Autowired
    private OsiMenuService osiMenuService;
    
	@Autowired
	private AuthTokenStore authTokenStore;
	
	@Autowired
	private Environment env;

    /**
     * POST  /osi-menus : Create a new osiMenu.
     *
     * @param osiMenusDTO the osiMenusDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new osiMenusDTO, or with status 400 (Bad Request) if the osiMenu has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws RestServiceException 
     */
    @PostMapping
    public ResponseEntity<SuccessResponse> createOsiMenu(@Valid @RequestBody OsiMenusDTO osiMenusDTO, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws URISyntaxException, RestServiceException {
    	SuccessResponse successResponse = null;
    	log.debug("REST request to save OsiMenu : {}", osiMenusDTO);
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer count = osiMenuService.save(osiMenusDTO, auth.getOsiUserDTO().getId(), auth.getOsiUserDTO().getBusinessGroupId());
			if(count!=null && count>0){
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1001"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
			//e.printStackTrace();
		}
        return new ResponseEntity<SuccessResponse>(successResponse,
				HttpStatus.OK);
    }

    /**
     * PUT  /osi-menus : Updates an existing osiMenu.
     *
     * @param osiMenusDTO the osiMenusDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated osiMenusDTO,
     * or with status 400 (Bad Request) if the osiMenusDTO is not valid,
     * or with status 500 (Internal Server Error) if the osiMenusDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws RestServiceException 
     */
    @PutMapping
    public ResponseEntity<SuccessResponse> updateOsiMenu(@Valid @RequestBody OsiMenusDTO osiMenusDTO, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws URISyntaxException, RestServiceException {
        log.debug("REST request to update OsiMenu : {}", osiMenusDTO);
        
        SuccessResponse successResponse = null;
    	try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer count =  osiMenuService.save(osiMenusDTO, auth.getOsiUserDTO().getId(), auth.getOsiUserDTO().getBusinessGroupId());
			if(count!=null && count>0){
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1002"));
			}
		}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return new ResponseEntity<SuccessResponse>(successResponse,
				HttpStatus.OK);
        
    }
    
    /**
     * GET  /osi-menus : get 10 Menus initially.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of osiMenus in body
     * @throws RestServiceException 
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */

    @GetMapping
    @RequestMapping("/listInitially")
    public ResponseEntity <List<OsiMenusDTO>> getMenusListInitially(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
        log.debug("REST request to get all getAllOsiMenus");
        
        List<OsiMenusDTO> osiMenusList = null;
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		osiMenusList  = osiMenuService.findMenusInitially(auth.getOsiUserDTO().getBusinessGroupId());
    	}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return ResponseEntity.ok().body(osiMenusList);
        
    }

    /**
     * GET  /osi-menus : get all the osiMenus.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of osiMenus in body
     * @throws RestServiceException 
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping
    public ResponseEntity <List<OsiMenusDTO>> getAllOsiMenus(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
        log.debug("REST request to get all getAllOsiMenus");
        
        List<OsiMenusDTO> osiMenusList = null;
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		osiMenusList  = osiMenuService.findAll(auth.getOsiUserDTO().getBusinessGroupId());
    	}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return ResponseEntity.ok().body(osiMenusList);
        
    }

    @GetMapping
    @RequestMapping("/list")
    public ResponseEntity <List<OsiMenusDTO>> getAllOsiMenusForList(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
        log.debug("REST request to get all getAllOsiMenus");
        
        List<OsiMenusDTO> osiMenusList = null;
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		osiMenusList  = osiMenuService.findAllForList(auth.getOsiUserDTO().getBusinessGroupId());
    	}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return ResponseEntity.ok().body(osiMenusList);
        
    }
    /**
     * GET  /osi-menus/:id : get the "id" osiMenu.
     *
     * @param id the id of the osiMenusDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the osiMenusDTO, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<OsiMenusDTO>  getOsiMenu(@PathVariable Integer id, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
    	OsiMenusDTO osiMenusDTO = null;
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		osiMenusDTO = osiMenuService.findOne(id, auth.getOsiUserDTO().getBusinessGroupId());
    	}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return ResponseEntity.ok().body(osiMenusDTO);
    }


    /**
     * DELETE  /osi-menus/:id : delete the "id" osiMenu.
     *
     * @param id the id of the osiMenusDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    
    @PostMapping("/deleteMenu")
    public ResponseEntity<SuccessResponse> deleteOsiMenu(@Valid @RequestBody List<OsiMenusDTO> osiMenusDTOList, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws URISyntaxException, RestServiceException {
    	int count = 0;
    	SuccessResponse successResponse = null;
    	
    	
    	
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		List<Integer> menuIdList=new ArrayList<Integer>();
        	for(OsiMenusDTO OsiMenusDTO_:osiMenusDTOList)
        	{
        		menuIdList.add(OsiMenusDTO_.getId());
        	
        	}
    		
				count = osiMenuService.deleteMenus(menuIdList, auth.getOsiUserDTO().getBusinessGroupId(), auth.getOsiUserDTO().getId());
									
				if (count > 0) {
					successResponse = new SuccessResponse();
					successResponse.setHttpStatus(HttpStatus.OK.value());
					successResponse.setMessage(env.getProperty("MSG_1003"));
				}
		
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(),
					e.getErrorCode(), env.getProperty(e.getErrorCode()),
					e.getSystemMessage());
		}
    	return new ResponseEntity<SuccessResponse>(successResponse,
				HttpStatus.OK);
    	
    }
    
    @PostMapping("/searchMenu")
    public ResponseEntity<List<OsiMenusDTO>> searchOsiMenus(@Valid @RequestBody OsiMenus osiMenus, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws URISyntaxException, RestServiceException {
    
    	log.debug("REST request to save OsiMenu : {}", osiMenus);
    	List<OsiMenusDTO> searchMenuList=new ArrayList<OsiMenusDTO>();
		try {
			
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			if(auth!=null)
			{
				searchMenuList =osiMenuService.findOsiMenusByMenuNameOrDescription(osiMenus.getMenuName(), osiMenus.getDescription());
						
			}
			
					
			
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
			//e.printStackTrace();
		}
        return  new ResponseEntity<List<OsiMenusDTO>>(searchMenuList,HttpStatus.OK);
    }
    
    @ExceptionHandler({RestServiceException.class,BusinessException.class})
	public ResponseEntity<ErrorResponse> exceptionHandler(RestServiceException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getErrorCode());
		error.setHttpStatus(ex.getHttpStatus());
		error.setErrorMessage(ex.getErrorMessage());
		error.setDeveloperMessage(ex.getDeveloperMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.valueOf(ex.getHttpStatus()));
	}
    
}
 