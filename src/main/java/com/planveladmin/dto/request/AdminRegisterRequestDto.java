package com.planveladmin.dto.request;

import lombok.Data;

@Data
public class AdminRegisterRequestDto {
  private String username;
  private String password;
  private String name;
}