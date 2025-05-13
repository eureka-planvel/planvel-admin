package com.planveladmin.domain;

import com.planveladmin.dto.request.AdminRegisterRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
  private int id;
  private String username;
  private String password;
  private String name;

  public static Admin of(String username, String encodedPassword, String name) {
    Admin admin = new Admin();
    admin.setUsername(username);
    admin.setPassword(encodedPassword);
    admin.setName(name);
    return admin;
  }
}
