package com.hackerrank.api.controller;

import com.hackerrank.api.service.FlightDto;
import com.hackerrank.api.service.FlightService;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {

  private final FlightService flightService;

  public FlightController(FlightService flightService) {
    this.flightService = flightService;
  }

  @PostMapping("/flight")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto) {
    FlightDto createdFlight = flightService.createFlight(flightDto);
    URI location = URI.create("/flight/" + createdFlight.getId());
    return ResponseEntity.created(location).body(createdFlight);
  }

  @GetMapping("/flight")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<FlightDto>> getFlights(
      @RequestParam(value = "origin", required = false) String origin,
      @RequestParam(value = "orderBy", required = false) String orderBy) {

    List<FlightDto> flights = flightService.getFlights(origin, orderBy);

    return ResponseEntity.ok(flights);
  }

  @GetMapping("/flight/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<FlightDto> getFlightById(@PathVariable Integer id) {
    FlightDto flight = flightService.getFlightById(id);
    return ResponseEntity.ok(flight);
  }
}
