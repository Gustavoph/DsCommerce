package com.gustagco.dscommerce.controllers.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class ValidationException extends CustomException {
  private List<FieldMessage> errors;

  public ValidationException(
      Instant timestamp,
      HttpStatus status,
      String error,
      String path) {
    super(timestamp, status, error, path);
  }

  public void addError(String fieldName, String message) {
    this.errors.add(new FieldMessage(fieldName, message));
  }
}
