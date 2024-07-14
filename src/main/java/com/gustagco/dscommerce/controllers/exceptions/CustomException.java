package com.gustagco.dscommerce.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomException {
  private Instant timestamp;
  private HttpStatus status;
  private String error;
  private String path;
}
