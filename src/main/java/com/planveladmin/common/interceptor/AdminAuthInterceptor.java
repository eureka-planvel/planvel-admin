package com.planveladmin.common.interceptor;

import com.planveladmin.common.util.SessionUtil;
import com.planveladmin.dto.AdminDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminAuthInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession(false);
    AdminDto admin = (session != null) ? SessionUtil.getLoginAdmin(session) : null;

    if (admin == null) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setContentType("application/json; charset=UTF-8");
      response.getWriter().write("{\"success\":false,\"msg\":\"로그인 필요\"}");
      return false;
    }

    request.setAttribute("loginAdmin", admin);
    return true;
  }
}