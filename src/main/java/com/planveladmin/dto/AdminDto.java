package com.planveladmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminDto {
  private int id;
  private String username;
  private String name;
}
