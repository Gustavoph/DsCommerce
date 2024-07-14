package com.gustagco.dscommerce.services.exceptions;

public class DependencyConflictException extends RuntimeException {
  public DependencyConflictException () {
    super("Dependency conflict exception");
  }
}
