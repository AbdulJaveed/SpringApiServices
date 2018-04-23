package com.osi.urm.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import org.springframework.beans.factory.annotation.Autowired;

@Component
public class TokenValidation {
	
	@Autowired
	private InMemoryTokenStore tokenStore;
	
	public boolean validateToken(String token){
		boolean isValidToken=false;
		try{
			
				if(tokenStore.retrieveToken(token)!=null){
					isValidToken=true;
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return isValidToken;
	}

}
