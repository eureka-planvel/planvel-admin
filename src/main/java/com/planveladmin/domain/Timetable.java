package com.planveladmin.domain;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {
  private int id;
  private String transportType;
  private int departureStationId;
  private int arrivalStationId;
  private LocalTime departureTime;
  private int durationMin;
  private int price;
  private String transportNumber;

  // JOIN
  private String departureStationName;
  private String arrivalStationName;
  private String departureRegionName;
  private String arrivalRegionName;
}
