package com.hackerrank.api.repository;

import com.hackerrank.api.model.Flight;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

  List<Flight> findByOriginOrderByIdAsc(String origin);
  List<Flight> findAllByOrderByIdAsc();
  List<Flight> findAllByOrderByDestinationAsc();
  List<Flight> findAllByOrderByDestinationDesc();
}
