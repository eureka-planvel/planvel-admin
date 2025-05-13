package com.planveladmin.common.config;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class PasswordConfig {

  @Bean
  public PlanvelPasswordEncoder passwordEncoder() {
    return new CustomPlanvelPasswordEncoder();
  }

  public interface PlanvelPasswordEncoder {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
  }

  public static class CustomPlanvelPasswordEncoder implements PlanvelPasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
      if (rawPassword == null || rawPassword.toString().trim().isEmpty()) {
        throw new IllegalArgumentException();
      }
      return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
      return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
  }

}
