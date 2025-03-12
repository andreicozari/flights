package com.hackerrank.api.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

  private Integer id;

  private String flight;

  private String origin;

  private String destination;

  //@JsonProperty("speed_series")
  private List<Integer> speedSeries;
}
