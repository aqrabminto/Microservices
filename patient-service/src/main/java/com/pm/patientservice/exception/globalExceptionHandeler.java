package com.pm.patientservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class globalExceptionHandeler {

    private static final Logger log = LoggerFactory.getLogger(globalExceptionHandeler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(emailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleemailAlreadyExistsException(emailAlreadyExistsException ex) {
        log.warn("Email already exists {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "email already exists");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(patientNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlepatientNotFoundException(patientNotFoundException ex) {
        log.warn("Patient Not Found {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "patient not found");
        return ResponseEntity.badRequest().body(errors);
    }

}
