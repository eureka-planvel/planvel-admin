package com.planveladmin.controller;

import com.planveladmin.dto.CommonResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accommodation")
@RequiredArgsConstructor
public class AccommodationController {

  @PostMapping
  public ResponseEntity<CommonResponse<Void>> createAccommodation(@RequestBody AccommodationCreateRequestDto, HttpSession session) {

  }
}
