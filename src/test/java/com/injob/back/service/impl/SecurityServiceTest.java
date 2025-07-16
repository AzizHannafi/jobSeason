package com.injob.back.service.impl;

import com.injob.back.exception.InjobBackEndServerApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SecurityServiceTest {
    private  SecurityService securityService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        securityService = new SecurityService();
    }

    @Test
    void testGetClientId_NoAuthentication_ReturnsNull() {
        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);

        Jwt jwtToken = createJwtToken(null);
        when(authentication.getPrincipal()).thenReturn(jwtToken);

        boolean result = securityService.isAuthorizedClient(null);

        assertTrue(result);

    }

    @Test
    void testIsAuthorizedClient_ValidClientId_ReturnsTrue() {
        String clientId = "client1";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Jwt jwtToken = createJwtToken(clientId);
        when(authentication.getPrincipal()).thenReturn(jwtToken);

        boolean result = securityService.isAuthorizedClient(clientId);

        assertTrue(result);
    }

    @Test
    void testIsAuthorizedClient_InvalidClientId_ThrowsApplicationException() {
        String clientId = "client1";
        String invalidClientId = "client2";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Jwt jwtToken = createJwtToken(invalidClientId);
        when(authentication.getPrincipal()).thenReturn(jwtToken);

        InjobBackEndServerApplicationException exception = assertThrows(InjobBackEndServerApplicationException.class, () -> securityService.isAuthorizedClient(clientId));
        assertEquals("403", exception.getErrorCode());
        assertEquals("Access Denied, you must to connect with client " + clientId, exception.getMessage());
        assertEquals(HttpStatus.FORBIDDEN, exception.getHttpStatus());
    }

    @Test
    void testIsAuthorizedClient_NullClientId_ReturnsTrue() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Jwt jwtToken = createJwtToken(null);
        when(authentication.getPrincipal()).thenReturn(jwtToken);

        boolean result = securityService.isAuthorizedClient(null);

        assertTrue(result);
    }

    private Jwt createJwtToken(String clientId) {
        Jwt jwtToken = Mockito.mock(Jwt.class);
        when(jwtToken.getClaimAsString("clientId"))
                .thenReturn(clientId);
        return jwtToken;
    }
}
