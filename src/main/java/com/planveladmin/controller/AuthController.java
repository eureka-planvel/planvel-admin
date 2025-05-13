package com.planveladmin.controller;

import com.planveladmin.common.util.SessionUtil;
import com.planveladmin.dto.AdminDto;
import com.planveladmin.dto.CommonResponse;
import com.planveladmin.dto.request.AdminLoginRequestDto;
import com.planveladmin.dto.request.AdminRegisterRequestDto;
import com.planveladmin.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<CommonResponse<Void>> register ( @RequestBody AdminRegisterRequestDto request) {
    CommonResponse<Void> response = authService.register(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
  public ResponseEntity<CommonResponse<Void>> login (@RequestBody AdminLoginRequestDto request,  HttpSession session) {
    return ResponseEntity.ok(authService.login(request, session));
  }

  @GetMapping("/me")
  public ResponseEntity<CommonResponse<AdminDto>> getMyInfo(HttpSession session) {
    AdminDto admin = SessionUtil.getLoginAdmin(session);
    if(admin == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.fail("로그인 필요"));
    return ResponseEntity.ok(CommonResponse.success(admin, "조회 성공"));
  }

  @PostMapping("/logout")
  public ResponseEntity<CommonResponse<Void>> logout(HttpSession session) {
    SessionUtil.logout(session);
    return ResponseEntity.ok(CommonResponse.success("로그아웃 완료"));
  }

}
