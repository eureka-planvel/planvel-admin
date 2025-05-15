package com.planveladmin.common.resolver;

import com.planveladmin.common.annotation.LoginAdmin;
import com.planveladmin.dto.AdminDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

public class LoginAdminArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterAnnotation(LoginAdmin.class) != null &&
        parameter.getParameterType().equals(AdminDto.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
    Object admin = request.getAttribute("loginAdmin");
    if (admin == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 필요");
    }
    return admin;
  }
}