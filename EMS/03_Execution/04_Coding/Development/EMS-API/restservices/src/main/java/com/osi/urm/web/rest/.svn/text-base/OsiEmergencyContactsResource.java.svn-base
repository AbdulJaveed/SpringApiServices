package com.osi.urm.web.rest;

import java.net.URISyntaxException;

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
import com.osi.urm.domain.EmrContactDetais;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.service.ContactService;

/**
 * REST controller for managing certifications.
 */
@RestController
@RequestMapping("/api/emergencyContacts")
public class OsiEmergencyContactsResource {

	@Autowired
	private ContactService osiEmergencyContacts;
	@Autowired
	private Environment env;
	@Autowired
	private AuthTokenStore authTokenStore;


	
	@PostMapping("/save")	
	public ResponseEntity<Integer> saveContacts(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,  @RequestBody EmrContactDetais[] contactDetais ) throws URISyntaxException {

		int result = 0 ;
		
		try {			
			result = osiEmergencyContacts.saveContacts(contactDetais);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/get/{empId}")
	public ResponseEntity<EmrContactDetais[]> getEmergencyContacts(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,@PathVariable (value="empId") String empId ) throws URISyntaxException {

		 EmrContactDetais[] contactsList = null;
		
		try {			
			contactsList = osiEmergencyContacts.getContactDetails(empId);
			System.out.println(contactsList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<EmrContactDetais[]>(contactsList, HttpStatus.OK);
	}
	
	
}
