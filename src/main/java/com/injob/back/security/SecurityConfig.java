package com.injob.back.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.injob.back.exception.ErrorWrapper;
import com.injob.back.exception.InjobBackEndServerApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.util.Enumeration;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Slf4j
class SecurityConfig {
    private final JwtAuthConverter jwtAuthConverter;

    private final ObjectMapper mapper;
    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/v3/api-docs",
            "/v3/api-docs.yml",
            "/v3/api-docs/mobile",
            "/v3/api-docs2/**",
            "/api-docs/**",
            "/api-docs2/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/version/**"
    };



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  {

        try {
            log.info("HTTP SECURITY " + http);
            log.info("Configuring security filters...");
http.csrf(csrf -> csrf
    .ignoringRequestMatchers("/chat")
);
            http.authorizeHttpRequests()
                    .requestMatchers(SWAGGER_WHITELIST).permitAll()
                    .anyRequest()
                    .authenticated();
            http.oauth2ResourceServer()
                    .jwt()
                    .jwtAuthenticationConverter(jwtAuthConverter);
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler());
            http.cors();
            return http.build();
        } catch (Exception e) {
            log.info("ERROR {}", e);
            log.debug("ERROR " + e);
            throw new InjobBackEndServerApplicationException("500", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            // Get all request headers
            Enumeration<String> requestHeaderNames = request.getHeaderNames();
            while (requestHeaderNames.hasMoreElements()) {
                String headerName = requestHeaderNames.nextElement();
                String headerValue = request.getHeader(headerName);
                log.error("Request Header - " + headerName + ": " + headerValue);
            }
            log.error("Error " + accessDeniedException);
            // Customize the handling of the 403 Forbidden error
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            ErrorWrapper errorWrapper = ErrorWrapper.builder()
                    .success(Boolean.FALSE)
                    .message(accessDeniedException.getMessage())
                    .code(HttpStatus.FORBIDDEN.toString())
                    .build();
            response.getWriter().write(mapper.writeValueAsString(errorWrapper));
        };
    }


}