package com.alpha.alpha_help_desk_backend.security;

import com.alpha.alpha_help_desk_backend.configs.ApplicationConfigs;
import com.alpha.alpha_help_desk_backend.dto.response.AuthResponseDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private static final String SECRET_KEY = "TheBestFamillyEver@12!TheBestFamillyEver@12!TheBestFamillyEver@12!TheBestFamillyEver@12!"; // Change this to a secure key
    private static final long EXPIRATION_TIME = 3600; // 1 hour
    private final ApplicationConfigs applicationConfigs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // ✅ Generate JWT Token
    public AuthResponseDto generateToken(String username) {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + EXPIRATION_TIME);

        // Set Expiry (e.g., 1 hour from now)
        Instant expiryInstant = Instant.now().plusSeconds(applicationConfigs.getExpiryDuration());
        Date expiryDate = Date.from(expiryInstant);

        // Format Expiry Date
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        String formattedExpiry = formatter.format(expiryInstant);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(issuedAt)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        // Return token and expiry time
        return  AuthResponseDto.builder()
                .token(token)
                .duration(String.valueOf(applicationConfigs.getExpiryDuration()))
                .expiresAt(formattedExpiry)
                .build();
    }

    // ✅ Extract Username from JWT
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ✅ Check if Token is Expired
    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    // ✅ Validate Token
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }
}