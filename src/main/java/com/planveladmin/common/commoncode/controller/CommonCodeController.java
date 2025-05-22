package com.planveladmin.common.commoncode.controller;

import java.util.List;

import com.planveladmin.common.commoncode.dto.CommonCodeResultDto;
import com.planveladmin.common.commoncode.service.CommonCodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommonCodeController {
    private final CommonCodeService commonCodeService;
    
    @PostMapping("/commoncodes")
    public CommonCodeResultDto getCommonCodeList(@RequestBody List<String> groupCodes) {
        return commonCodeService.getCommonCodeList(groupCodes);
    }
}
