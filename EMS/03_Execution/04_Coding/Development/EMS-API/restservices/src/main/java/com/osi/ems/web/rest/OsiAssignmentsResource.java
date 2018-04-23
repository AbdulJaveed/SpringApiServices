package com.osi.ems.web.rest;

import java.net.URISyntaxException;
import java.util.List;

import org.json.JSONArray;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.ems.common.CommonService;
import com.osi.ems.service.OsiAssignmentsService;
import com.osi.ems.service.dto.OsiAssignmentsDTO;
import com.osi.ems.service.dto.OsiEmployeesDTO;
import com.osi.ems.service.dto.OsiGradesDTO;
import com.osi.ems.service.dto.OsiJobCodesDTO;
import com.osi.ems.service.dto.OsiLocationsDTO;
import com.osi.ems.service.dto.OsiRollUpsDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.exception.ErrorResponse;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.dto.OsiLookupTypesDTO;

@RestController
@RequestMapping(value = "/api/assignments")
public class OsiAssignmentsResource {

	private final Logger log = LoggerFactory.getLogger(OsiAssignmentsResource.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private OsiAssignmentsService osiAssignmentsService;
	
	@Autowired
	private AuthTokenStore authTokenStore;
	
	@Autowired
	private CommonService commonService;
	
	@GetMapping("/getInitialAssignmentsList")
    public ResponseEntity<List<OsiAssignmentsDTO>> getInitialAssignmentsList(
			@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException, BusinessException {
		log.info("getInitialAssignmentsList : Begin");
    	log.debug("START:::  REST request to getting initial list of osi assignments.");
    	List<OsiAssignmentsDTO> osiAssignmentsDTO = null;
    
        try {
        	osiAssignmentsDTO = osiAssignmentsService.getInitialAssignmentsList();
			
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting assignments list");
		}
		log.info("getInitialAssignmentsList : End");
       
        log.debug("END:::  REST request to getting initial list of osi assignments.");
        return ResponseEntity.ok().body(osiAssignmentsDTO);
    }
	
	@GetMapping("/findAssignmentById/{employeeId}")
	public ResponseEntity<OsiAssignmentsDTO> getAssignmentById(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken, 
			@PathVariable("employeeId") Integer id) throws RestServiceException, DataAccessException {
		log.info("getAssignmentById : Begin");
		
		log.debug("START:::  REST request to getting assignment by employee id.");
		OsiAssignmentsDTO osiAssignmentsDTO = null;
		
		try {
			osiAssignmentsDTO = osiAssignmentsService.findByAssignmentId(id);
						
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting assignments list");
		}
		log.info("getAssignmentById : End");
		
		log.debug("END:::  REST request to getting assignment by employee id.");
		return new ResponseEntity<OsiAssignmentsDTO>(osiAssignmentsDTO, HttpStatus.OK);
	}
	
	@PostMapping("/findAssignmentById")
	public ResponseEntity<OsiAssignmentsDTO> getAssignmentByIdAndDate(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken, 
			@RequestBody String inputObject) throws RestServiceException, DataAccessException {
		
		log.debug("START:::  REST request to getting assignment by employee id.");
		OsiAssignmentsDTO osiAssignmentsDTO = null;
		log.info("getAssignmentByIdAndDate : Begin");
		try {
			osiAssignmentsDTO = osiAssignmentsService.findByAssignmentIdAndSerachDate(inputObject);
						
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting assignments list");
		}
		log.info("getAssignmentByIdAndDate : End");
		log.debug("END:::  REST request to getting assignment by employee id.");
		return new ResponseEntity<OsiAssignmentsDTO>(osiAssignmentsDTO, HttpStatus.OK);
	}
  
	@GetMapping("/findRollUpsById/{rollUpId}")
    public ResponseEntity<OsiRollUpsDTO> findRollUpsById(
    		@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,
			@PathVariable("rollUpId") Integer rollUpId)
			throws RestServiceException {
		
    	log.debug("START:::  REST request to getting list of all jobs.");
    	OsiRollUpsDTO osiRollUpsDTO = null;
    	log.info("findRollUpsById : Begin");
        try {
        	osiRollUpsDTO = osiAssignmentsService.findRollUpsById(rollUpId);
			
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting roll ups");
		}
		log.info("findRollUpsById : End");
        return ResponseEntity.ok().body(osiRollUpsDTO);
    }
	
	@GetMapping("/getAllJobCodes/{empId}")
    public ResponseEntity<List<OsiJobCodesDTO>> getAllJobCodes(
    		@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,
			@PathVariable("empId") Integer empId)
			throws RestServiceException {
		
    	log.debug("START:::  REST request to getting list of all jobs.");
    	List<OsiJobCodesDTO> osiJobCodesDTO = null;
    	log.info("getAllJobCodes : Begin");
        try {
        	osiJobCodesDTO = osiAssignmentsService.getAllJobCodes(empId);
			
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting all job codes");
		}
		log.info("getAllJobCodes : End");
       
        log.debug("END:::  REST request to getting initial list of osi jobs.");
        return ResponseEntity.ok().body(osiJobCodesDTO);
    }
	
	@GetMapping("/findJobCodesById/{jobCodeId}")
    public ResponseEntity<OsiJobCodesDTO> findJobCodesById(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,@PathVariable("jobCodeId") Integer id)
			throws RestServiceException {
		
    	log.debug("START:::  REST request to getting list of all jobs by Id");
    	OsiJobCodesDTO osiJobCodesDTO = null;
    	log.info("findJobCodesById : Begin");
        try {
        	osiJobCodesDTO = osiAssignmentsService.getJobCodesById(id);
			
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting job codes");
		}
		log.info("findJobCodesById : End");
       
        log.debug("END:::  REST request to getting initial list of osi jobs.");
        return ResponseEntity.ok().body(osiJobCodesDTO);
    }
	
	@GetMapping("/findGradesById/{gradeId}")
    public ResponseEntity<OsiGradesDTO> findGradesById(
			@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,@PathVariable("gradeId") Integer id)
			throws RestServiceException {
		
    	log.debug("START:::  REST request to getting list of all grades By id.");
    	OsiGradesDTO osiGradesDTO = null;
    	log.info("findGradesById : Begin");
        try {
        	osiGradesDTO = osiAssignmentsService.getGradesById(id);
			
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting grades");
		}
		log.info("findGradesById : End");
       
        log.debug("END:::  REST request to getting initial list of osi jobs.");
        return ResponseEntity.ok().body(osiGradesDTO);
    }
	
	@GetMapping("/findOsiLocationsById/{locationId}")
    public ResponseEntity<OsiLocationsDTO> findOsiLocationsById(
			@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,@PathVariable("locationId") Integer id)
			throws RestServiceException {
		
    	log.debug("START:::  REST request to getting list of all osi locations By id.");
    	OsiLocationsDTO osiLocationsDTO = null;
    	log.info("findOsiLocationsById : Begin");
        try {
        	osiLocationsDTO = osiAssignmentsService.getOsiLocatonById(id);
			
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting locations");
		}
		log.info("findOsiLocationsById : End");
       
        log.debug("END:::  REST request to getting initial list of osi jobs.");
        return ResponseEntity.ok().body(osiLocationsDTO);
    }
	@GetMapping("/getAllGrades/{empId}")
    public ResponseEntity<List<OsiGradesDTO>> getAllGrades(
    		@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken,
			@PathVariable("empId") Integer empId)
			throws RestServiceException {
		
    	log.debug("START:::  REST request to getting list of all grades.");
    	List<OsiGradesDTO> osiJobCodesDTO = null;
    	log.info("getAllGrades : Begin");
        try {
        	osiJobCodesDTO = osiAssignmentsService.getAllGrades(empId);
			
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting all grades");
		}
		log.info("getAllGrades : End");
       
        log.debug("END:::  REST request to getting initial list of osi grades.");
        return ResponseEntity.ok().body(osiJobCodesDTO);
    }
	
	
	@GetMapping("/getAllLocations/{empId}")
    public ResponseEntity<List<OsiLocationsDTO>> getAllLocations(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken, 
    		@PathVariable("empId") Integer empId)
			throws RestServiceException {
		
    	log.debug("START:::  REST request to getting list of all locations.");
    	List<OsiLocationsDTO> osiLocationsDTO = null;
    	log.info("getAllLocations : Begin");
        try {
        	
        	osiLocationsDTO = osiAssignmentsService.getAllLocations(empId);
			
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting all locations");
		}
		log.info("getAllLocations : End");
       
        log.debug("END:::  REST request to getting initial list of osi locations.");
        return ResponseEntity.ok().body(osiLocationsDTO);
    }
	
	@GetMapping("/getAllDepartment")
    public ResponseEntity<List<OsiLookupTypesDTO>> getAllDepartment(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException {
		
    	log.debug("START:::  REST request to getting list of all departments.");
    	List<OsiLookupTypesDTO> osiLookupTypesDTO = null;
    	log.info("getAllDepartment : Begin");
        try {
        	osiLookupTypesDTO = osiAssignmentsService.getAllDepartment();
			
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting all department data");
		}
		log.info("getAllDepartment : End");
		
        log.debug("END:::  REST request to getting list of osi departments.");
        return ResponseEntity.ok().body(osiLookupTypesDTO);
    }
	
	@PostMapping("/searchEmployeeByEmpName/{empName}")
    public ResponseEntity<List<OsiEmployeesDTO>> searchEmployeeByEmpName(@PathVariable("empName") String empName, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException {
		
    	log.debug("START:::  REST request to getting employee by employee name.");
    	List<OsiEmployeesDTO> osiEmployeeDTO = null;
    	log.info("searchEmployeeByEmpName : Begin");
        try {
        	if(null != empName){
        		
        		osiEmployeeDTO = osiAssignmentsService.searchEmployeeByNumber(empName);
        	}
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching employee");
		}
		log.info("searchEmployeeByEmpName : End");
       
        log.debug("END:::  REST request to getting employee by employee name.");
        return ResponseEntity.ok().body(osiEmployeeDTO);
    }
	
	@PostMapping("/searchSupervisorByNumber/{empNumber}")
    public ResponseEntity<OsiEmployeesDTO> searchSupervisorByNumber(@PathVariable("empNumber") String empNumber, @RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken)
			throws RestServiceException {
		
    	log.debug("START:::  REST request to getting supervisor by emp number.");
    	OsiEmployeesDTO osiEmployeeDTO = null;
    	log.info("searchSupervisorByNumber : Begin");
        try {	
        	if(null != empNumber){
        		osiEmployeeDTO = osiAssignmentsService.searchSupervisorByNumber(empNumber);
        	}
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while searching supervisor");
		}
		log.info("searchSupervisorByNumber : End");
       
        log.debug("END:::  REST request to getting supervisor by emp number.");
        return ResponseEntity.ok().body(osiEmployeeDTO);
    }
	

	@PostMapping("/saveAssignments/{action}")
	public ResponseEntity<OsiAssignmentsDTO> saveAssignments(
			@RequestBody OsiAssignmentsDTO osiAssignmentsDTO,
			@PathVariable("action") String action,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
					throws URISyntaxException, RestServiceException {
		log.info("saveAssignments : Begin");
		log.debug("START:::  REST request in saving assignments.");
			OsiAssignmentsDTO saveAssignments = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			osiAssignmentsDTO.setUpdatedBy(auth.getOsiUserDTO().getId());
			//osiAssignmentsDTO.setLastUpdateDate(new Date());
			osiAssignmentsDTO.setLastUpdateDate(commonService.getCurrentDateinUTC());
			
			if(action.equals("update")){
				osiAssignmentsDTO.setCreatedBy(auth.getOsiUserDTO().getId());
				//osiAssignmentsDTO.setCreatedDate(new Date());
				osiAssignmentsDTO.setCreatedDate(commonService.getCurrentDateinUTC());
				saveAssignments = osiAssignmentsService.saveAssignments(osiAssignmentsDTO);
			}else{
				saveAssignments = osiAssignmentsService.updateAssignments(osiAssignmentsDTO);
			}
			
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while saving assignments");
		}
		log.info("saveAssignments : End");
		
		log.debug("END:::  REST request in saving assignments.");
		return new ResponseEntity<OsiAssignmentsDTO>(saveAssignments, HttpStatus.OK);

	}

	@PostMapping("/saveRollups")
	public ResponseEntity<OsiRollUpsDTO> saveRollups(
			@RequestBody OsiRollUpsDTO osiRollupsDTO,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
					throws URISyntaxException, RestServiceException {
		log.info("saveRollups : Begin");
		log.debug("START:::  REST request in saving assignments.");
		OsiRollUpsDTO saveRollups = null;
		try {
			AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
			
			osiRollupsDTO.setUpdatedBy(auth.getOsiUserDTO().getId());
			osiRollupsDTO.setCreatedBy(auth.getOsiUserDTO().getId());
			saveRollups = osiAssignmentsService.saveEmployeeRollups(osiRollupsDTO);
			
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while saving roll ups");
		}
		log.info("saveRollups : End");
		log.debug("END:::  REST request in saving assignments.");
		return new ResponseEntity<OsiRollUpsDTO>(saveRollups, HttpStatus.OK);
	}
	
	@PostMapping("/loadRollupsPopup")
	public ResponseEntity<String> loadRollupsPopup(
			@RequestBody String inputObject,
			@RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken)
					throws URISyntaxException, RestServiceException, DataAccessException {
		
		log.debug("START:::  REST request in saving assignments.");
		JSONArray result = null;
		log.info("loadRollupsPopup : Begin");
		try {
			System.out.println(inputObject);
			result = osiAssignmentsService.loadRollupsPopup(inputObject);
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		} catch (DataAccessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while loading roll ups");
		}
		log.info("loadRollupsPopup : End");
		log.debug("END:::  REST request in saving assignments.");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
	
	@GetMapping("/findSuperviosrId/{employeeId}")
	public Integer getSuperviosrId(@RequestHeader(value=AppConfig.AUTH_TOKEN) String authToken, 
			@PathVariable("employeeId") Integer id) throws RestServiceException, DataAccessException {
		log.info("getSuperviosrId : Begin");
		
		log.debug("START:::  REST request to getting superviosr by employee id.");
		Integer supervisorId = null;
		try {
			supervisorId = osiAssignmentsService.findSuperviosrId(id);
						
		}catch (BusinessException e) {
			log.error("Error Occured : "+e.getSystemMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty(e.getErrorCode()),e.getErrorCode(), e.getSystemMessage());
		}catch (Exception e) {
			log.error("Error Occured : "+e.getMessage());
			throw new RestServiceException(HttpStatus.NO_CONTENT.value(),
					env.getProperty("ERR_1000"),"ERR_1000", "Error occured while getting assignments list");
		}
		log.info("getSuperviosrId : End");
		
		log.debug("END:::  REST request to getting assignment by employee id.");
		return supervisorId;
	}
	
	@ExceptionHandler(RestServiceException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(RestServiceException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getErrorCode());
		error.setHttpStatus(ex.getHttpStatus());
		error.setErrorMessage(ex.getErrorMessage());
		error.setDeveloperMessage(ex.getDeveloperMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
