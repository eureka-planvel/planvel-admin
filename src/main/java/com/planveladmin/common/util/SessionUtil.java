package com.planveladmin.common.util;

import com.planveladmin.domain.Admin;
import com.planveladmin.dto.AdminDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SessionUtil {
  private static final String LOGIN_ADMIN_KEY = "LOGIN_ADMIN";

  public static void login(HttpSession session, AdminDto dto) {
    session.setAttribute(LOGIN_ADMIN_KEY, dto);
  }

  public static AdminDto getLoginAdmin(HttpSession session) {
    Object value = session.getAttribute(LOGIN_ADMIN_KEY);
    return (value instanceof AdminDto) ? (AdminDto) value : null;
  }

  public static boolean isLoggedIn(HttpSession session) {
    return getLoginAdmin(session) != null;
  }

  public static void logout(HttpSession session) {
    session.invalidate();
  }
}
