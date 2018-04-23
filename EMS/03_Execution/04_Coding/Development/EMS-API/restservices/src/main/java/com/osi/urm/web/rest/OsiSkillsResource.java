package com.osi.urm.web.rest;

import java.net.URISyntaxException;
import java.util.List;

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

import com.osi.urm.config.AppConfig;
import com.osi.urm.domain.SkillDetails;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.SkillsService;
import com.osi.urm.service.dto.OsiUserDTO;
import com.osi.urm.web.rest.util.SuccessResponse;

/**
 * REST controller for managing certifications.
 */
@RestController
@RequestMapping("/api/skills")
public class OsiSkillsResource {

	@Autowired
	private SkillsService skillsService;
	@Autowired
	private Environment env;
	@Autowired
	private AuthTokenStore authTokenStore;

	@GetMapping("/get")
	public ResponseEntity<List<SkillDetails>> getSkillDetails(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken ) throws URISyntaxException {

		List<SkillDetails> skillDetalisList = null;
		try {			
			
			skillDetalisList = skillsService.getSkillDetails();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<SkillDetails>>(skillDetalisList, HttpStatus.OK);
	}
	
	
	@PostMapping("/search")	
	public ResponseEntity<List<SkillDetails>> searchCertificationDetails(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,  @RequestBody SkillDetails skillDetails ) throws URISyntaxException {

		List<SkillDetails> certificationDetailsLst = null;
		
		try {			
			certificationDetailsLst = skillsService.searchSkillDetails(skillDetails);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<SkillDetails>>(certificationDetailsLst, HttpStatus.OK);
	}
	
	@PostMapping("/add")	
	public ResponseEntity<SuccessResponse> saveCertifications(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,  @RequestBody SkillDetails skillDetails ) throws BusinessException, RestServiceException {
		SuccessResponse successResponse = null;
		Integer result = null ;
		
		try {		
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			OsiUserDTO userDTO = auth.getOsiUserDTO();
			result = skillsService.saveSkills(skillDetails, userDTO.getOrgId());
			if (result != null) {
				successResponse = new SuccessResponse();
				successResponse.setHttpStatus(HttpStatus.OK.value());
				successResponse.setMessage(env.getProperty("MSG_1046"));
			}
		} catch (BusinessException e) {
			successResponse = new SuccessResponse();
			successResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			successResponse.setMessage(e.getSystemMessage());
			return ResponseEntity.badRequest().body(successResponse);
		}
		return ResponseEntity.ok().body(successResponse);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<SkillDetails>> getCertificationDetials(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken, @PathVariable("id") int id) throws URISyntaxException {

		List<SkillDetails> skillDetailsLst = null;
		
		try {			
			skillDetailsLst = skillsService.getSkillDetailsById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<SkillDetails>>(skillDetailsLst, HttpStatus.OK);
	}
	
	@PostMapping("/verifySkills")	
	public ResponseEntity<SuccessResponse> updateSkills(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,  @RequestBody List<SkillDetails> skillDetailsList ) throws BusinessException, RestServiceException {
		SuccessResponse successResponse = null;
		Integer result = null ;
		
		try {
			
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			
			skillsService.updateVerifiedSkills(skillDetailsList, token.getOsiUserDTO().getId());
			successResponse = new SuccessResponse();
			successResponse.setHttpStatus(HttpStatus.OK.value());
			successResponse.setMessage(env.getProperty("MSG_1046"));
			
		} catch (BusinessException e) {
			successResponse = new SuccessResponse();
			successResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			successResponse.setMessage(e.getSystemMessage());
			return ResponseEntity.badRequest().body(successResponse);
		}
		return ResponseEntity.ok().body(successResponse);
	}

}
