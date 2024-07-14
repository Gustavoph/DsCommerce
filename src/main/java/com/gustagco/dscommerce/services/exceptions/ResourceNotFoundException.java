package com.gustagco.dscommerce.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException () {
    super("Resource not found");
  }
}
