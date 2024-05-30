package com.colome.filerouge.modules.auth;

import ch.qos.logback.classic.spi.LoggingEventVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

public interface JwtServiceInterface {
    String generateToken(UserDetails userDetails);
    String extractUsername(String token);
    Jwt extractClaims(String token);
    boolean isTokenExpired(String token);
    boolean isValidateToken(String token, UserDetails userDetails);
}
