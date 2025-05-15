package com.planveladmin.domain;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Accommodation {
  private Integer id;
  private Integer regionId;
  private String name;
  private String address;
  private BigDecimal pricePerNight;
  private String imageUrl;
  private String thumbnailUrl;
  private Boolean isHotel;
}