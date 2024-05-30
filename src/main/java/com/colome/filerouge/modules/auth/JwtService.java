package com.colome.filerouge.modules.auth;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.stream.Collectors;

@Component
public class JwtService implements JwtServiceInterface {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public JwtService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }
    
    @Override
    public String generateToken(UserDetails userDetails) {
        Instant now = Instant.now();
        // create a a
        JwtClaimsSet claims = JwtClaimsSet
                .builder()
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(60*60))
                .claim(
                        "scope", userDetails
                                .getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.joining(","))
                )
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    @Override
    public Jwt extractClaims(String token) {
        return jwtDecoder.decode(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiresAt().isBefore(Instant.now());
    }

    @Override
    public boolean isValidateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
