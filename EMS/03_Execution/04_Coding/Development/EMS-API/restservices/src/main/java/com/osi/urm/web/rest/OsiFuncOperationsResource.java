package com.osi.urm.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.ems.common.CommonService;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.OsiFuncOperationsService;
import com.osi.urm.service.dto.OsiFuncOperationsDTO;
import com.osi.urm.service.impl.OsiFuncOperationsServiceImpl;
import com.osi.urm.web.rest.util.SuccessResponse;

@RestController
@RequestMapping("/api")
public class OsiFuncOperationsResource {

	
	@Autowired
	private OsiFuncOperationsService osiFuncOperationsService;
	
	@Autowired
	private OsiFuncOperationsServiceImpl osiFuncOperationsServiceImpl; 
	 
    @Autowired
	private AuthTokenStore authTokenStore;
    
    @Autowired
	private CommonService commonService;
    
    @Autowired
	private Environment env;
	
	 
	 @PostMapping("/funcOperations")
		public ResponseEntity<OsiFuncOperationsDTO> createOsiFuncOperation(
				@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
				@RequestBody OsiFuncOperationsDTO osiFuncOperationsDTO) throws RestServiceException {
			try {
				AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
				OsiFuncOperationsDTO result = null;
				osiFuncOperationsDTO.setCreatedBy(auth.getOsiUserDTO().getId());
				//osiFuncOperationsDTO.setCreatedDate(new Date());
				osiFuncOperationsDTO.setCreatedDate(commonService.getCurrentDateinUTC());
				osiFuncOperationsDTO.setUpdatedBy(auth.getOsiUserDTO().getId());
				osiFuncOperationsDTO.setUpdatedDate(commonService.getCurrentDateinUTC());
				osiFuncOperationsDTO = osiFuncOperationsService.save(osiFuncOperationsDTO, auth.getOsiUserDTO().getBusinessGroupId());
				
			} catch (BusinessException e) {
				throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
						e.getErrorCode(), e.getSystemMessage());
			}
			return ResponseEntity.ok().body(osiFuncOperationsDTO);
		}

	 
	 @PutMapping("/updateFuncOperations")
		public ResponseEntity<SuccessResponse> updateOsiFunction(
				@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
				@RequestBody OsiFuncOperationsDTO osiFuncOperationsDTO) throws RestServiceException {
			SuccessResponse successResponse = null;
			try {
				AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
				OsiFuncOperationsDTO result = null;
				osiFuncOperationsDTO.setUpdatedBy(auth.getOsiUserDTO().getId());
				//osiFuncOperationsDTO.setUpdatedDate(new Date());
				osiFuncOperationsDTO.setUpdatedDate(commonService.getCurrentDateinUTC());
				if (osiFuncOperationsDTO.getId() == null) {
					result = osiFuncOperationsService.save(osiFuncOperationsDTO,
							auth.getOsiUserDTO().getBusinessGroupId());
				}
				result = osiFuncOperationsService.save(osiFuncOperationsDTO, auth.getOsiUserDTO().getBusinessGroupId());
				if (result != null) {
					successResponse = new SuccessResponse();
					successResponse.setHttpStatus(HttpStatus.OK.value());
					successResponse.setMessage(env.getProperty("MSG_1028"));
				}
			} catch (BusinessException e) {
				throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
						e.getErrorCode(), e.getSystemMessage());
			}
			return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
		}

	 

	    @GetMapping("/getFuncOperationsByFunctionId/{funcId}")
	    public ResponseEntity<String> getFuncOperationsByFunctionId(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,@PathVariable Integer funcId )
	        throws Exception {
	        
	        List<OsiFuncOperationsDTO> osiFuncOperationsDTOs = null;
			try {
				AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
				osiFuncOperationsDTOs = osiFuncOperationsService.findByFunctionId(funcId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(toJsonString(osiFuncOperationsDTOs));
			
			return new ResponseEntity<String>(toJsonString(osiFuncOperationsDTOs), HttpStatus.OK);
	    }
	    public static String toJsonString(Object object) throws Exception{
			String response = null;
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.writeValueAsString(object);
			return response;
		}
		
	
}
