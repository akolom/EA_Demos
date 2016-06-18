package edu.mum.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import edu.mum.weather.AuthenticationServiceImpl;


public class AuthenticateUser {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticateUser.class);

  public  Authentication authenticate(Authentication user, AuthenticationManager authenticationManager) throws Exception {
  
	  Authentication result = null;
	  
	  String userName = user.getName();
	  String password = (String) user.getCredentials();
      try {
        Authentication request = new UsernamePasswordAuthenticationToken(userName,password);
        result = authenticationManager.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);
 
      } catch(AuthenticationException e) {
        System.out.println("Authentication failed: " + e.getMessage());
        logger.info("*******  Authentication failed ************" );	
      return null;
      }
    
      logger.info("*******  Successfully authenticated. ************" );	

      System.out.println("Successfully authenticated. Security context contains: " +
              SecurityContextHolder.getContext().getAuthentication());
    
    return result;
  }
  
  public void logout() {
 
	  // Clears the context for the current user/thread
	  SecurityContextHolder.clearContext();
 }
}
