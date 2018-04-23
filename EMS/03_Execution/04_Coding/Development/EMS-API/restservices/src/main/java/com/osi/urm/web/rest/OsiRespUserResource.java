package com.osi.urm.web.rest;

import java.net.URI;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.urm.config.AppConfig;
import com.osi.urm.domain.OsiRespUser;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;
import com.osi.urm.service.OsiRespUserService;
import com.osi.urm.service.dto.OsiRespUserDTO;
import com.osi.urm.service.impl.OsiUserServiceImpl;
import com.osi.urm.web.rest.util.SuccessResponse;

@RestController
@RequestMapping("/api")
public class OsiRespUserResource {
	 private final Logger log = LoggerFactory.getLogger(OsiRespUserResource.class);
	 
	 @Autowired
	 private OsiRespUserService osiRespUserService;
	 
	 @Autowired
	 private OsiUserServiceImpl osiUserServiceImpl;
	 
	 @Autowired
	 private AuthTokenStore authTokenStore;
	 
	 @Autowired
	 private Environment env;

	 
	 @PostMapping("/post-user-responsibilities")
     public ResponseEntity<List<OsiRespUserDTO>> createOsiResponsibility(@RequestBody List<OsiRespUser> osiRespUser,@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws URISyntaxException {
      AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
      log.debug("come to the insert rest end point");
      List<OsiRespUserDTO> resultSet = new ArrayList<>();
      //getting all existing user-responsibilities
      try {
	      if(!osiRespUser.isEmpty()) {
	    	  int empId = osiRespUser.get(0).getEmployeeId();
	    	  Integer businessGroupId = auth.getOsiUserDTO().getId();
	    	  List<OsiRespUserDTO> allUserRespList = osiRespUserService.getById(empId,businessGroupId);
	    	  List<Integer> deletedResps = new ArrayList<>();
	    	  for(OsiRespUserDTO respuser : allUserRespList ) {
	    		  for(OsiRespUser rspusr : osiRespUser) {
	    			  if(rspusr.getId() != null)
	    			  if(rspusr.getId().intValue() != respuser.getId().intValue()) {
	    				  deletedResps.add(respuser.getId());
	    				  break;
	    			  }
	    		  }
	    	  }
	    	  if(!deletedResps.isEmpty()) {
	    		  for(int delId : deletedResps)
	    			  osiRespUserService.deleteRespUser(delId);
	    	  } else {
	    		  if(osiRespUser.size() == 1 && osiRespUser.get(0).getId() == null) {
	    			  for(OsiRespUserDTO respuser : allUserRespList ) {
	    				  osiRespUserService.deleteRespUser(respuser.getId());
	    			  }
	    		  }
	    	  }
	      }
      } catch (BusinessException e) {
			e.printStackTrace();
      }
      
         
         for (OsiRespUser resp : osiRespUser) {
             resp.setBusinessGroupId(auth.getOsiUserDTO().getId());
             resp.setUpdatedBy(auth.getOsiUserDTO().getId());
             OsiRespUserDTO result = osiRespUserService.save(resp);
             resultSet.add(result);
         }
         try {
        	 osiRespUserService.deleteFuncExclusionByUserId(osiRespUser.get(0).getEmployeeId());
        	 osiRespUserService.deleteOperationsExclusionByUserId(osiRespUser.get(0).getEmployeeId());
         } catch(BusinessException e) {
        	 e.printStackTrace();
         }
         return ResponseEntity.created(new URI("/api/post-user-responsibilities")).body(resultSet);
     }

	 
	 @GetMapping("/get-all-user-responsibilities/{id}")
	    public ResponseEntity<String> getAllOsiResponsibilities(@PathVariable("id") int employeeId,@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws BusinessException {
		 AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
		 
		 String response= null;
		 Integer businessGroupId = auth.getOsiUserDTO().getId();
		 List<OsiRespUserDTO> list = osiRespUserService.getById(employeeId,businessGroupId);
		 try{
			 	response = toJsonString(list);
			
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return new ResponseEntity<String>(response, HttpStatus.OK);
	    }
	 
	 @DeleteMapping("/delete-user-responsibility/{id}")
	    public ResponseEntity<SuccessResponse> deleteOsiResponsibility(@PathVariable Integer id,@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException{
		 SuccessResponse successResponse = null;
		 	log.debug("REST request to delete OsiResponsibility : {}", id);
	        try{
	        	osiRespUserService.deleteRespUser(id);
	        	successResponse = new SuccessResponse();
	        	successResponse.setHttpStatus(HttpStatus.OK.value());
	        	successResponse.setMessage(env.getProperty("MSG_1039"));
	        }
	        catch(BusinessException e){
	        	throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
						e.getErrorCode(), e.getSystemMessage());
	        }
	        return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
	    }
	 
	 public static String toJsonString(Object object) throws Exception{
			String response = null;
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.writeValueAsString(object);
			return response;
		}

}
