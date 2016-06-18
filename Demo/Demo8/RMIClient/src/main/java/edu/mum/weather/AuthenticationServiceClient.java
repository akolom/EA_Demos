package edu.mum.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationServiceClient {

     @Autowired
    private AuthenticationService authenticationService;


	public Authentication authenticate(Authentication clientAuthentication)  {
         
       clientAuthentication = authenticationService.authenticate(clientAuthentication);
		SecurityContextHolder.getContext().setAuthentication(clientAuthentication);

		return clientAuthentication;
	}
}
