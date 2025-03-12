package com.hackerrank.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {

  @ExceptionHandler(value = {FlightException.class})
  public ResponseEntity<Object> handleFlightException(final FlightException ex,
      final ServletWebRequest request) {
    log.error(ex.getMessage(), ex);
    return ResponseEntity.status(ex.getHttpStatus()).body(toDto(ex));
  }

  private ExceptionDto toDto(Exception ex) {
    return new ExceptionDto(ex.getClass().getName(), ex.getMessage());
  }

}
