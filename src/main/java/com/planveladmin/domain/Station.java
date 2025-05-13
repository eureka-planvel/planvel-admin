package com.planveladmin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {
  private int id;
  private String name;
  private int regionId;
  private String type;

  // JOIN
  private String regionName;

}
