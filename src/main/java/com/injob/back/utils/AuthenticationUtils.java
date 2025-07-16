package com.injob.back.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthenticationUtils {

    public  static String getEmailFromCurrentAuthentication() {
        Authentication authentication = getCurrentAuthentication();
        return ((Jwt) authentication.getPrincipal()).getClaims().get("email").toString();
    }

    public static   Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static boolean isAdmin() {
        Authentication authentication = getCurrentAuthentication();
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_admin"));
    }


}
