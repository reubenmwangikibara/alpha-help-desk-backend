package com.alpha.alpha_help_desk_backend.security;

import com.alpha.alpha_help_desk_backend.configs.ApplicationConfigs;
import com.google.gson.Gson;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final ApplicationConfigs applicationConfigs;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7); // Remove "Bearer " prefix

        try {
            username = extractUsername(jwt);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(username);
                System.out.println("User Details: " + new Gson().toJson(userDetails.getUsername()));

                if (validateToken(jwt, userDetails.getUsername())) {
                    System.out.println("Token Validated");
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    System.out.println("Authentication Successful");
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    System.out.println("INVALID JWT TOKEN " + new Gson().toJson(userDetails));
                    sendErrorResponse(response, "Invalid token", HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            sendErrorResponse(response, "Token expired. Please log in again.", HttpServletResponse.SC_UNAUTHORIZED);
        } catch (SignatureException e) {
            sendErrorResponse(response, "Invalid JWT signature.", HttpServletResponse.SC_UNAUTHORIZED);
        } catch (MalformedJwtException e) {
            sendErrorResponse(response, "Malformed JWT token.", HttpServletResponse.SC_BAD_REQUEST);
        } catch (UnsupportedJwtException e) {
            sendErrorResponse(response, "Unsupported JWT token.", HttpServletResponse.SC_BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            sendErrorResponse(response, "JWT claims string is empty.", HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            sendErrorResponse(response, "Invalid token.", HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean validateToken(String token, String username) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return (claims.getSubject().equals(username)) && !claims.getExpiration().before(new Date());
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(applicationConfigs.getSecretKey().getBytes());
    }

    private void sendErrorResponse(HttpServletResponse response, String message, int statusCode) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.getWriter().write("{ \"error\": \"" + message + "\" }");
    }
}