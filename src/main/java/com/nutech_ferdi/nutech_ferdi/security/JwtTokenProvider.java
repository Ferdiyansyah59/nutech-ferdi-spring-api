package com.nutech_ferdi.nutech_ferdi.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-ms}")
    private long jwtExpirationInMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(Authentication auth) {
        String email = auth.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getEmailFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);

            return true;
        }catch (SignatureException ex) {
            log.error("Invalid JWT signature", ex);
        }catch(SecurityException ex) {
            log.error("Invalid jwt signature ", ex);
        }catch (MalformedJwtException ex) {
            log.error("JWT Token is Invalid " , ex);
        }catch (ExpiredJwtException ex) {
            log.error("JWT Token is Expired ", ex);
        }catch(UnsupportedJwtException ex) {
            log.error("Unsupported JWT token", ex);
        }catch (IllegalArgumentException ex) {
            log.error("JWT Claims String is Empty ", ex);
        }
        return false;
    }


}
