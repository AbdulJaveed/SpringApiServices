package com.osi.urm.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.osi.urm.config.AppConfig;
import com.osi.urm.security.util.AuthTokenStore;

@Component
public class ClearToken {
	  @Autowired
	  private AuthTokenStore authTokenStore;
	  
		    @Scheduled(fixedRate = AppConfig.TOKEN_CLEAR_TIME_IN_MS)
		    public void clearExpiredTokens() {
		    	authTokenStore.clearExpiredTokens();
		    }
}
