package com.planveladmin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spot {
  private Integer id;
  private Integer regionId;
  private String spotName;
  private String address;
  private String type;
  private String imageUrl;
  private String thumbnailUrl;
}
