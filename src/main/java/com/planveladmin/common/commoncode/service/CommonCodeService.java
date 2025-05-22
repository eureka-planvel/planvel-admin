package com.planveladmin.common.commoncode.service;

import com.planveladmin.common.commoncode.dto.CommonCodeResultDto;

import java.util.List;

public interface CommonCodeService {
	CommonCodeResultDto getCommonCodeList(List<String> goupCodes);
}
