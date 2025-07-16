package com.injob.back.service.impl;

import com.injob.back.exception.InjobBackEndServerApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityService {

    public boolean isAuthorizedClient(String clientId) {
        // Retrieve the client ID from the authentication token
        String tokenClientId = getClientId();
        if (clientId != null && !clientId.equals(tokenClientId)) {
            throw new InjobBackEndServerApplicationException("403", "Access Denied, you must to connect with client " + clientId, HttpStatus.FORBIDDEN);
        }
        return true;
    }

    private String getClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Jwt jwtToken) {
                return jwtToken.getClaimAsString("clientId");
            }
        }
        return null;
    }
}