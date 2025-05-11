package com.planveladmin.service;

import com.planveladmin.common.config.PasswordConfig.PlanvelPasswordEncoder;
import com.planveladmin.common.util.SessionUtil;
import com.planveladmin.domain.Admin;
import com.planveladmin.dto.AdminDto;
import com.planveladmin.dto.CommonResponse;
import com.planveladmin.dto.request.AdminLoginRequestDto;
import com.planveladmin.dto.request.AdminRegisterRequestDto;
import com.planveladmin.mapper.AdminMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final AdminMapper adminMapper;
  private final PlanvelPasswordEncoder passwordEncoder;


  public CommonResponse<Void> register(AdminRegisterRequestDto request) {
    if (adminMapper.existsByUsername(request.getUsername())) {
      return CommonResponse.fail("이미 존재하는 ID");
    }

    Admin admin = Admin.of(request.getUsername(), passwordEncoder.encode(request.getPassword()),
        request.getName());
    adminMapper.insertAdmin(admin);

    return CommonResponse.success("회원 가입 성공");

  }

  public CommonResponse<Void> login(AdminLoginRequestDto request, HttpSession session) {
    Admin admin = adminMapper.findByUsername(request.getUsername());

    if (admin == null || !passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
      return CommonResponse.fail("아이디 또는 비밀번호가 올바르지 않습니다.");
    }

    AdminDto adminDto = new AdminDto(
        admin.getId(),
        admin.getUsername(),
        admin.getName()
    );

    SessionUtil.login(session, adminDto);

    return CommonResponse.success("로그인 성공");
    }}

