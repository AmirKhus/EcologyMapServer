package com.example.vkr_server.service;

import com.example.vkr_server.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class WebSocketAuthenticatorService {
    @Autowired
    UserService userService;
    @Autowired
    UserDetailsServiceImplementation userDetailsService;

    @Autowired
    JwtUtils jwtUtils;
    // This method MUST return a UsernamePasswordAuthenticationToken instance, the spring security chain is testing it with 'instanceof' later on. So don't use a subclass of it or any other class
    public UsernamePasswordAuthenticationToken getAuthenticatedOrFail(final String  jwtToken) throws AuthenticationException {

        if (jwtToken == null || jwtToken.trim().isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Token was null or empty.");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthToken = fetchUserFromDb(jwtToken);

        // Add your own logic for retrieving user in fetchUserFromDb()
        if (usernamePasswordAuthToken == null || usernamePasswordAuthToken.getPrincipal() == null) {
            throw new BadCredentialsException("Bad credentials for user");
        }

        // null credentials, we do not pass the password along
        return usernamePasswordAuthToken;

    }

    private UsernamePasswordAuthenticationToken fetchUserFromDb(String jwtToken) {
        jwtUtils.validateJwtToken(jwtToken);

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(jwtToken));
        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
    }
}