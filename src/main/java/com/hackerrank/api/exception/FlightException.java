package com.hackerrank.api.exception;

import lombok.Getter;

@Getter
public class FlightException extends RuntimeException {

  private final int httpStatus;

  public FlightException(final String message, int httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
