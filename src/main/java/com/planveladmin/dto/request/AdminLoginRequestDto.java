package com.planveladmin.dto.request;

import lombok.Data;

@Data
public class AdminLoginRequestDto {
  private String username;
  private String password;
}