package com.osi.urm.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.urm.config.AppConfig;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.TokenValidation;

@Component
public class TokenAuthFilter implements Filter {
	private final String tokenHeader=AppConfig.AUTH_TOKEN;
	@Autowired
    private TokenValidation tokenValidation;
	@Autowired
	private AuthTokenStore authTokenStore;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
	  FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res; 
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "DELETE, HEAD, GET, POST, PUT, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Auth_Token, Content-Type, Accept");
		try {
			String url = request.getRequestURL().toString();
			String authToken = request.getHeader(this.tokenHeader);
			if(!request.getMethod().equals("OPTIONS")){
				if(url!=null && (!url.contains("validate-login") && !url.contains("unauthorized") )){
				    if (authToken == null || !tokenValidation.validateToken(authToken)) {
				    	response.sendRedirect("/api/unauthorized");
				       // throw new AuthException(HttpStatus.UNAUTHORIZED.value(), "ERR_2000", env.getProperty("ERR_2000") ,null);
				    }
				}
			}
		} catch (Exception ex) {
		//	response.sendRedirect("/api/unauthorized");
			//throw new AuthException(HttpStatus.UNAUTHORIZED.value(), "ERR_2001", env.getProperty("ERR_2001") ,null);
		}
        
	chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		authTokenStore.removeAllTokens();
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}
