package com.osi.urm.web.rest;

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
import com.osi.urm.service.OsiReportGroupService;
import com.osi.urm.service.dto.OsiRptGroupsDTO;
import com.osi.urm.web.rest.util.SuccessResponse;

@RestController
@RequestMapping("/api")
public class OsiReportGrpResource {
	private final Logger log = LoggerFactory.getLogger(OsiReportGrpResource.class);

	@Autowired
	private Environment env;
	
    @Autowired
    private OsiReportGroupService osiReportGroupService;
    
    @Autowired
	private AuthTokenStore authTokenStore;
	
    @PostMapping("/all-report-groups")
    public ResponseEntity<List<OsiRptGroupsDTO>> getAllReportGroups(@Valid @RequestBody OsiRptGroupsDTO osiRptGroupsDTO, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException{
    List<OsiRptGroupsDTO> osiRptGroupsDTOList = null;
    	try {
    		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
    		osiRptGroupsDTOList = osiReportGroupService.findAllRptGroups(auth.getOsiUserDTO().getBusinessGroupId(), osiRptGroupsDTO.getRptGrpName());
    	}catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
    	return ResponseEntity.ok().body(osiRptGroupsDTOList);
    }
    @GetMapping("/all-active-report-groups")
    public ResponseEntity<List<OsiRptGroupsDTO>> getAllActiveReportGroups(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException{
    List<OsiRptGroupsDTO> osiRptGroupsDTOList = null;
	try {
		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
		osiRptGroupsDTOList = osiReportGroupService.findAllActiveRptGroups(auth.getOsiUserDTO().getBusinessGroupId());
	}catch (BusinessException e) {
		throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
	}
	return ResponseEntity.ok().body(osiRptGroupsDTOList);
    }
    @PostMapping("/report-group")
    public ResponseEntity<SuccessResponse> saveReportGroup(@Valid @RequestBody OsiRptGroupsDTO osiRptGroupsDTO, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken)
            throws RestServiceException {
    	SuccessResponse successResponse = null;
			try {
				AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
				Integer count = osiReportGroupService.saveOrUpdate(osiRptGroupsDTO, auth.getOsiUserDTO().getId(), auth.getOsiUserDTO().getBusinessGroupId());
				if(count!=null && count>0){
					successResponse = new SuccessResponse();
					successResponse.setHttpStatus(HttpStatus.OK.value());
					successResponse.setMessage(env.getProperty("MSG_1058"));
				}
			} catch (BusinessException e) {
				throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
			}
    	return ResponseEntity.ok().body(successResponse);
    }
    @PutMapping("/report-group")
    public ResponseEntity<SuccessResponse>  createOsiCategories(@Valid @RequestBody OsiRptGroupsDTO osiRptGroupsDTO, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken ) throws RestServiceException {
    	SuccessResponse successResponse = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer count = osiReportGroupService.saveOrUpdate(osiRptGroupsDTO, auth.getOsiUserDTO().getId(), auth.getOsiUserDTO().getBusinessGroupId());
			if(count!=null && count>0){
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1059"));
			}
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}
	return ResponseEntity.ok().body(successResponse);
    }
    
    @GetMapping("/report-group/{id}")
    public ResponseEntity<OsiRptGroupsDTO> getReportGroup(@PathVariable Integer id, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException{
    OsiRptGroupsDTO osiRptGroupsDTO = null;
	try {
		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
		osiRptGroupsDTO = osiReportGroupService.findOne(id, auth.getOsiUserDTO().getBusinessGroupId());
	}catch (BusinessException e) {
		throw new RestServiceException(HttpStatus.NO_CONTENT.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
	}
	return ResponseEntity.ok().body(osiRptGroupsDTO);
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
