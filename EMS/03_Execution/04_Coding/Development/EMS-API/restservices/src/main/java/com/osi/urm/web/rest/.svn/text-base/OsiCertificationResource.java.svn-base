package com.osi.urm.web.rest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.osi.urm.config.AppConfig;
import com.osi.urm.domain.OsiCertificationDetails;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.OsiCertifactionService;
import com.osi.urm.web.rest.util.SuccessResponse;

/**
 * REST controller for managing certifications.
 */
@RestController
@RequestMapping("/api/certification")
public class OsiCertificationResource {

	@Autowired
	private OsiCertifactionService osiCertifactionService;
	@Autowired
	private Environment env;
	@Autowired
	private AuthTokenStore authTokenStore;
	
	List<String> files = new ArrayList<String>();

	@GetMapping("/get")
	public ResponseEntity<List<OsiCertificationDetails>> getCertificationDetials(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken ) throws URISyntaxException {

		List<OsiCertificationDetails> certificationDetailsLst = null;
		
		try {			
			certificationDetailsLst = osiCertifactionService.getCertificationDetails();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<OsiCertificationDetails>>(certificationDetailsLst, HttpStatus.OK);
	}
	
	
	@PostMapping("/search")	
	public ResponseEntity<List<OsiCertificationDetails>> searchCertificationDetails(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,  @RequestBody OsiCertificationDetails osiCertificationDetails ) throws URISyntaxException {

		List<OsiCertificationDetails> certificationDetailsLst = null;
		
		try {			
			certificationDetailsLst = osiCertifactionService.searchCertificationDetails(osiCertificationDetails);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<OsiCertificationDetails>>(certificationDetailsLst, HttpStatus.OK);
	}
	
	@PostMapping("/add")	
	public ResponseEntity<SuccessResponse> saveCertifications(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,  @RequestBody OsiCertificationDetails osiCertificationDetails ) throws BusinessException {
		SuccessResponse successResponse = null;
		Integer result = null ;
		
		try { 
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			if(osiCertificationDetails.getAttachmentId() == null)
				osiCertificationDetails.setCreatedBy(token.getOsiUserDTO().getId());
			else
				osiCertificationDetails.setUpdatedBy(token.getOsiUserDTO().getId());
			result = osiCertifactionService.saveCertifications(osiCertificationDetails, token.getOsiUserDTO().getOrgId());
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
	
	// Multiple file upload
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> uploadFile(@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken,
			@RequestParam("uploadfile") MultipartFile uploadfile) throws Exception {
		SuccessResponse successResponse = null;
    	try {
			
			files.add(uploadfile.getOriginalFilename());
			
			successResponse = new SuccessResponse();
			successResponse.setHttpStatus(HttpStatus.OK.value());
			successResponse.setMessage(env.getProperty("MSG_1033"));
		//	return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new Exception("FAIL! Maybe You had uploaded the file before or the file's size > 500KB");
		}
    	return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
    }
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<OsiCertificationDetails>> getCertificationDetials(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken, @PathVariable("id") int id) throws URISyntaxException {

		List<OsiCertificationDetails> certificationDetailsLst = null;
		
		try {			
			certificationDetailsLst = osiCertifactionService.getCertificationDetailsById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<OsiCertificationDetails>>(certificationDetailsLst, HttpStatus.OK);
	}
	
	@PostMapping("/verifyCertifications")	
	public ResponseEntity<SuccessResponse> updateSkills(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,  @RequestBody List<OsiCertificationDetails> certDetailsList ) throws BusinessException, RestServiceException {
		SuccessResponse successResponse = null;
		Integer result = null ;
		
		try {
			
			AuthorizationToken token = authTokenStore.retrieveToken(authToken);
			
			osiCertifactionService.updateVerifiedSkills(certDetailsList, token.getOsiUserDTO().getId());
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
