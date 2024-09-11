package com.ayed.gestionstock.exception;

import lombok.Getter;

import java.util.List;

public class invalidEntityException extends RuntimeException {

  @Getter
  private errorCodes errorCode;
  @Getter
  private List<String> errors;

  public invalidEntityException(String message) {
    super(message);
  }

  public invalidEntityException(String message, Throwable cause) {
    super(message, cause);
  }

  public invalidEntityException(String message, Throwable cause, errorCodes errorCode) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public invalidEntityException(String message, errorCodes errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public invalidEntityException(String message, errorCodes errorCode, List<String> errors) {
    super(message);
    this.errorCode = errorCode;
    this.errors = errors;
  }

}
