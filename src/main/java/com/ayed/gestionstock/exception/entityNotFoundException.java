package com.ayed.gestionstock.exception;

import lombok.Getter;

public class entityNotFoundException extends RuntimeException {

  @Getter
  private errorCodes errorCode;

  public entityNotFoundException(String message) {

    super(message);
  }

  public entityNotFoundException(String message, Throwable cause) {

    super(message, cause);
  }

  public entityNotFoundException(String message, Throwable cause, errorCodes errorCode) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public entityNotFoundException(String message, errorCodes errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

}
