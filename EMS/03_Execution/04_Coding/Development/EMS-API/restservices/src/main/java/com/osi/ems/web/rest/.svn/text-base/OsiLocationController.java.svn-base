/*package com.osi.ems.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.service.OsiLocationService;
import com.osi.ems.service.dto.OsiLocationsDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.web.rest.util.SuccessResponse;


@RestController
@RequestMapping("/api")
public class OsiLocationController {

	private final Logger log = LoggerFactory.getLogger(OsiLocationController.class);

	@Autowired
	private OsiLocationService OsiLocationService;

	@Autowired
	private Environment env;

	@Autowired
	private AuthTokenStore authTokenStore;

	@PostMapping("/locations")
	public ResponseEntity<SuccessResponse> createOsiLocation(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestBody OsiLocationsDTO osiLocationsDTO) throws RestServiceException {

		SuccessResponse successResponse = null;

		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			Integer userId = auth.getOsiUserDTO().getId();
			osiLocationsDTO=OsiLocationService.createLocation(osiLocationsDTO, userId);
			if (osiLocationsDTO!=null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(osiLocationsDTO.getLocationId().toString());
			}

		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (DataAccessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	}

	@GetMapping("/locations/{orgId}")
	public ResponseEntity<List<OsiLocationsDTO>> getOsiLocationByOrgId(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable("orgId") int orgId)
			throws Exception {
		List<OsiLocationsDTO> result = new ArrayList<OsiLocationsDTO>();
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			result = OsiLocationService.locationbyOrgId(orgId);
			
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (DataAccessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		
		return  ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/locationsById/{locId}")
	public ResponseEntity getOsiLocationByLocId(
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken, @PathVariable("locId") int locId)
			throws Exception {
		OsiLocationsDTO result = new OsiLocationsDTO();
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			result = OsiLocationService.locationbyLocId(locId);
			
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		} catch (DataAccessException e) {
			return new ResponseEntity(e.getSystemMessage(), HttpStatus.NOT_FOUND);
		}
		
		return  ResponseEntity.ok().body(result);
	}

}
*/
