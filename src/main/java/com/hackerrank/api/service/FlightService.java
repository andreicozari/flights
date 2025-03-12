package com.hackerrank.api.service;

import com.hackerrank.api.exception.FlightException;
import com.hackerrank.api.model.Flight;
import com.hackerrank.api.model.OrderBy;
import com.hackerrank.api.repository.FlightRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {

  private final FlightRepository flightRepository;

  public FlightDto createFlight(FlightDto flightDto) {
    var flight = toFlight(flightDto);
    var savedFlight = flightRepository.save(flight);
    return toFlightDto(savedFlight);
  }

  public List<FlightDto> getFlights(String origin, String orderBy) {
    List<Flight> flights = List.of();

    if (origin != null) {
      flights = flightRepository.findByOriginOrderByIdAsc(origin);
    } else if (orderBy != null) {
      flights = getFlightsByOrderBy(orderBy);
    } else {
      flights = flightRepository.findAllByOrderByIdAsc();
    }

    return toFlightsDto(flights);
  }

  public FlightDto getFlightById(Integer id) {
    var flight = flightRepository.findById(id)
        .orElseThrow(() -> new FlightException("Flight not found",
            HttpStatus.NOT_FOUND.value()));
    return toFlightDto(flight);
  }

  private List<Flight> getFlightsByOrderBy(String orderBy) {
    List<Flight> flights;

    if (OrderBy.DESTINATION_ASC.getPriority().equals(orderBy)) {
      flights = flightRepository.findAllByOrderByDestinationAsc();
    } else if (OrderBy.DESTINATION_DESC.getPriority().equals(orderBy)) {
      flights = flightRepository.findAllByOrderByDestinationDesc();
    } else {
      throw new FlightException("Invalid orderBy parameter",
          HttpStatus.BAD_REQUEST.value());
    }

    return flights;
  }

  private Flight toFlight(FlightDto flightDto) {
    return Flight.builder()
        .id(flightDto.getId())
        .flight(flightDto.getFlight())
        .origin(flightDto.getOrigin())
        .destination(flightDto.getDestination())
        .speedSeries(flightDto.getSpeedSeries())
        .build();
  }

  private FlightDto toFlightDto(Flight flight) {
    return FlightDto.builder()
        .id(flight.getId())
        .flight(flight.getFlight())
        .origin(flight.getOrigin())
        .destination(flight.getDestination())
        .speedSeries(flight.getSpeedSeries())
        .build();
  }

  private List<FlightDto> toFlightsDto(List<Flight> flights) {
    return flights.stream()
        .map(this::toFlightDto)
        .collect(Collectors.toList());
  }
}
