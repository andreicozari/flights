package com.hackerrank.api.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderBy {

  DESTINATION_ASC("destination"),
  DESTINATION_DESC("-destination");
  private final String priority;
}
