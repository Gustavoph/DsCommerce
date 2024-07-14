package com.gustagco.dscommerce.controllers.exceptions;

import java.time.Instant;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gustagco.dscommerce.services.exceptions.DependencyConflictException;
import com.gustagco.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomException> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    var exception = CustomException.builder()
      .status(HttpStatus.NOT_FOUND)
      .timestamp(Instant.now())
      .error(e.getMessage())
      .path(request.getRequestURI())
      .build();
   
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
  }

  @ExceptionHandler(DependencyConflictException.class)
  public ResponseEntity<CustomException> dependencyConflict(DependencyConflictException e, HttpServletRequest request) {
    var exception = CustomException.builder()
      .status(HttpStatus.FAILED_DEPENDENCY)
      .timestamp(Instant.now())
      .error(e.getMessage())
      .path(request.getRequestURI())
      .build();
   
    return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(exception);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CustomException> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
    var exception = ValidationException.builder()
      .status(HttpStatus.UNPROCESSABLE_ENTITY)
      .timestamp(Instant.now())
      .error("Validation Error")
      .errors(new ArrayList<>())
      .path(request.getRequestURI())
      .build();
      
      e.getBindingResult().getFieldErrors()
        .forEach(fieldError -> {
          exception.addError(fieldError.getField(), fieldError.getDefaultMessage());
        });
   
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception);
  }
}
