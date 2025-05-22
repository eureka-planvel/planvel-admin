package com.planveladmin.common.commoncode.service;

import com.planveladmin.common.commoncode.dto.CommonAccommodationDto;

import java.util.List;

public interface CommonAccommodationService {
    List<CommonAccommodationDto> getAccommodations(String typeCode);
}
