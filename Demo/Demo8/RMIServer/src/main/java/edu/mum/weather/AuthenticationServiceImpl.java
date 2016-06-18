package edu.mum.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import edu.mum.security.AuthenticateUser;

public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	   AuthenticationManager authenticationManager ;

    public Authentication authenticate(Authentication clientAuthentication) {
 
    	System.out.println("GOT TO SERVER");
    	
        AuthenticateUser authenticateUser = new AuthenticateUser();
        try {
        	clientAuthentication = authenticateUser.authenticate(clientAuthentication,authenticationManager);
     		
     	} catch (Exception e) {
     		clientAuthentication =null;
     	}

        return clientAuthentication;
    }
}
