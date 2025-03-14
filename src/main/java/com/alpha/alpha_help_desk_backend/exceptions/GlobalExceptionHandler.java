package com.alpha.alpha_help_desk_backend.exceptions;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.FieldErrorDto;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.security.SignatureException;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    Map<String, Object> body = new HashMap<>();

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseApiResponse handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<FieldErrorDto> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.add(new FieldErrorDto(fieldName, errorMessage));
        });
        return new BaseApiResponse(null,400,"Bad Request",errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseApiResponse handleMissingParams(MissingServletRequestParameterException ex) {
        String fieldName = ex.getParameterName();
        List<FieldErrorDto> errors = new ArrayList<>();
        errors.add(new FieldErrorDto(fieldName, "Missing field "+fieldName));
        return new BaseApiResponse(null,400,"Bad Request",errors);

    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public BaseApiResponse handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {

        String errorMessage = ex.getName() + " should be of type " + Objects.requireNonNull(ex.getRequiredType()).getSimpleName();
        List<FieldErrorDto> errors = new ArrayList<>();
        errors.add(new FieldErrorDto(ex.getName(),errorMessage));
        return new BaseApiResponse(null,400,"Bad Request",errors);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    protected BaseApiResponse handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpStatus status, WebRequest request) {
        String error = "Invalid Request Body. Malformed JSON";
        return new BaseApiResponse(null,400,error,null);
    }


    @ExceptionHandler(JsonMappingException.class)
    public BaseApiResponse handleJsonMappingException(JsonMappingException e){
        String error = "Invalid Request Body. Malformed JSON";
        return new BaseApiResponse(null,400,error,null);
    }


    @ExceptionHandler(UserExistException.class)
    public BaseApiResponse handleJsonMappingException(UserExistException e){
        String error = e.getMessage();
        return new BaseApiResponse(null,400,error,null);
    }

}
