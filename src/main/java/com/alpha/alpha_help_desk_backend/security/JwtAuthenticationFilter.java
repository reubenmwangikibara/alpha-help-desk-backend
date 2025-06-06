package com.alpha.alpha_help_desk_backend.security;

import com.alpha.alpha_help_desk_backend.configs.ApplicationConfigs;
import com.google.gson.Gson;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

                if (validateToken(jwt, userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    System.out.println("INVALID JWT TOKEN " + new Gson().toJson(userDetails));
                    sendErrorResponse(response, "Invalid token", HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            log.info("EXPIRED JWT TOKEN {}", new Gson().toJson(e));
            sendErrorResponse(response, "Token expired. Please log in again.", HttpServletResponse.SC_UNAUTHORIZED);
        } catch (SignatureException e) {
            log.info("INVALID JWT TOKEN {}", new Gson().toJson(e));
            sendErrorResponse(response, "Invalid JWT signature.", HttpServletResponse.SC_UNAUTHORIZED);
        } catch (MalformedJwtException e) {
            log.info("Malformed  JWT TOKEN {}", new Gson().toJson(e));
            sendErrorResponse(response, "Malformed JWT token.", HttpServletResponse.SC_BAD_REQUEST);
        } catch (UnsupportedJwtException e) {
            log.info("UNSUPPORTED JWT TOKEN {}", new Gson().toJson(e));
            sendErrorResponse(response, "Unsupported JWT token.", HttpServletResponse.SC_BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            log.info("Illegal JWT TOKEN {}", new Gson().toJson(e));
            sendErrorResponse(response, "JWT claims string is empty.", HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            sendErrorResponse(response, e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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