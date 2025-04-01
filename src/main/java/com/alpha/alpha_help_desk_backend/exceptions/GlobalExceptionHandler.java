package com.alpha.alpha_help_desk_backend.exceptions;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.FieldErrorDto;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import javax.naming.AuthenticationException;
import java.util.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public BaseApiResponse handleException(Exception ex) {
        String error = ex.getMessage();
        log.info(error);
        return new BaseApiResponse(400,error,null,null);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseApiResponse handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<FieldErrorDto> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            log.info(ex.getMessage());

            errors.add(new FieldErrorDto(fieldName, errorMessage));
        });
        return new BaseApiResponse(400,"Bad Request",errors,null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseApiResponse handleMissingParams(MissingServletRequestParameterException ex) {
        String fieldName = ex.getParameterName();
        List<FieldErrorDto> errors = new ArrayList<>();
        log.info(ex.getMessage());

        errors.add(new FieldErrorDto(fieldName, "Missing field "+fieldName));
        return new BaseApiResponse(400, ex.getLocalizedMessage(),errors,null);

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseApiResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String error = ex.getLocalizedMessage();
        log.info(error);
        return new BaseApiResponse(400,error,null,null);

    }


    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public BaseApiResponse handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        log.info(ex.getMessage());

        String errorMessage = ex.getName() + " should be of type " + Objects.requireNonNull(ex.getRequiredType()).getSimpleName();
        List<FieldErrorDto> errors = new ArrayList<>();
        errors.add(new FieldErrorDto(ex.getName(),errorMessage));
        return new BaseApiResponse(400,ex.getLocalizedMessage(),errors,errors);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    protected BaseApiResponse handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpStatus status, WebRequest request) {
        String error = "Invalid Request Body. Malformed JSON";
        log.info(error);

        return new BaseApiResponse(400,error,null,null);
    }

    @ExceptionHandler(JsonMappingException.class)
    public BaseApiResponse handleJsonMappingException(JsonMappingException e){

        String error = e.getMessage();
        log.info(error);

        return new BaseApiResponse(400,error,null,null);
    }

    @ExceptionHandler(UserExistException.class)
    public BaseApiResponse handleJsonMappingException(UserExistException e){
        String error = e.getMessage();
        log.info(error);
        return new BaseApiResponse(400,error,null,null);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public BaseApiResponse handleJsonMappingException(EmployeeNotFoundException e){
        String error = e.getMessage();
        log.info(error);
        return new BaseApiResponse(400,error,null,null);
    }

    @ExceptionHandler(InvoiceDetailsExistException.class)
    public BaseApiResponse handleInvoiceExistException(InvoiceDetailsExistException e){
        String error = e.getMessage();
        log.info(error);
        return new BaseApiResponse(400,error,null,null);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public BaseApiResponse handleAuthenticationException(InvalidTokenException e) {
        String error = e.getMessage();
        log.info(error);
        return new BaseApiResponse(400,error,null,null);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public BaseApiResponse handleAuthenticationException(UsernameNotFoundException e) {
        String error = e.getMessage();
        log.info(error);
        return new BaseApiResponse(400,error,null,null);
    }

}
