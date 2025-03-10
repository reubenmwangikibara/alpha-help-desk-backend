package com.alpha.alpha_help_desk_backend.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.security.SignatureException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    Map<String, Object> body = new HashMap<>();

    // Handle Specific Exception
    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<Object> handleUserExistException(UserExistException ex, WebRequest request) {
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", ex.getMessage());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    // Handle Expired JWT Exception
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "JWT Expired");
        body.put("message", "Your session has expired. Please log in again.");

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    // Handle Invalid JWT Signature
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleInvalidJwtSignature(SignatureException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "Invalid Token Signature");
        body.put("message", "The token signature is invalid.");

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    // Handle Malformed JWT
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Malformed JWT");
        body.put("message", "The provided JWT is malformed.");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Handle Unsupported JWT Exception
    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<Object> handleUnsupportedJwtException(UnsupportedJwtException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Unsupported JWT");
        body.put("message", "This type of JWT is not supported.");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Handle General Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
