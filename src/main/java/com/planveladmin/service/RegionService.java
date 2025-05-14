package com.planveladmin.service;

import com.planveladmin.domain.Region;
import com.planveladmin.dto.CommonResponse;
import com.planveladmin.mapper.RegionMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionService {

  private final RegionMapper regionMapper;

  public CommonResponse<List<Region>> getAllRegions() {
    List<Region> regions = regionMapper.selectAllRegions();
    return CommonResponse.success(regions, "지역 목록 조회 성공");
  }
}
