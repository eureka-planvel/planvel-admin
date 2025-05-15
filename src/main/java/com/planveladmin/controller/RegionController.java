package com.planveladmin.controller;

import com.planveladmin.domain.Region;
import com.planveladmin.dto.CommonResponse;
import com.planveladmin.service.RegionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/region")
@RequiredArgsConstructor
public class RegionController {

  private final RegionService regionService;

  @GetMapping
  public ResponseEntity<CommonResponse<List<Region>>> getAllRegion() {
    return ResponseEntity.ok(regionService.getAllRegions());
  }
}
