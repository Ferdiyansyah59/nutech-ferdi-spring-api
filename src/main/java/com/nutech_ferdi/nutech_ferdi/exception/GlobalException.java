package com.nutech_ferdi.nutech_ferdi.exception;


import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


import java.time.LocalDateTime;
import java.util.Map;



@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                104,
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> resourceAlreadyExist(ResourceAlreadyExistException ex) {
        ErrorResponse err = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse<Object>> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        ErrorResponse<Object> err = new ErrorResponse<>(
                102,
                "Ukuran file melebihi batas (Maksimal 2MB)",
                null
        );

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse<Object>> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponse<Object> err = new ErrorResponse<>(
                102,
                ex.getMessage(),
                null
        );

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String firstErrorMessage = ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(err -> err.getDefaultMessage())
                .orElse("Validation error");

        ErrorResponse<Map<String, String>> errorResponse = new ErrorResponse<>(
                102,
                firstErrorMessage,
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse<Object>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {

        ErrorResponse<Object> err = new ErrorResponse<>(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                "Method Not Allowed",
                null
        );

        return new ResponseEntity<>(err, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse<Object>> handleAuthException(AuthenticationException ex) {

        ErrorResponse<Object> err = new ErrorResponse<>(
                103,
                "Username atau password salah",
                null
        );
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<Object>> handleGenericException(Exception ex) {
        ex.printStackTrace();
        log.error("An unhandled exception occurred ", ex);

        ErrorResponse<Object> err = new ErrorResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred. Please try again later",
                null
        );

        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse<Object>> handleMalformedJson(HttpMessageNotReadableException ex) {
        ErrorResponse<Object> err = new ErrorResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Malformed JSON request body. Please Check Yout request systax.",
                null
        );

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse<Object>> handleDatabaseConflict(DataIntegrityViolationException ex) {

        String message = "Database constraint violation. Check your input data.";
        if (ex.getMostSpecificCause() != null) {
            message = ex.getMostSpecificCause().getMessage();
        }
        ErrorResponse<Object> err = new ErrorResponse<>(
                HttpStatus.CONFLICT.value(),
                message,
                null
        );

        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }
}
