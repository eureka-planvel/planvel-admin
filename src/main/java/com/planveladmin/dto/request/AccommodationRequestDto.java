package com.planveladmin.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccommodationRequestDto {
  private Integer regionId;
  private String name;
  private String address;
  private String pricePerNight;
  private String type;
}