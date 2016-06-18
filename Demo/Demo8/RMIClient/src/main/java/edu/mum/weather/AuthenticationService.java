package edu.mum.weather;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    public Authentication authenticate(Authentication clientSideAuthentication);
}
