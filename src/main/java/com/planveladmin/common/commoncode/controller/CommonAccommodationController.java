package com.planveladmin.common.commoncode.controller;

import com.planveladmin.common.commoncode.dto.CommonAccommodationDto;
import com.planveladmin.common.commoncode.service.CommonAccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommonAccommodationController {

    private final CommonAccommodationService accommodationService;

    @GetMapping("/accommodations")
    public Map<String, Object> listAccommodations(@RequestParam(value = "type", required = false) String typeCode) {
        List<CommonAccommodationDto> list = accommodationService.getAccommodations(typeCode);
        Map<String, Object> response = new HashMap<>();
        response.put("result", "success");
        response.put("accommodations", list);
        return response;
    }
}
