package com.planveladmin.common.commoncode.dto;

import java.util.List;

import lombok.Data;

@Data
public class CodeResultDto {

	private String result;
	private CodeDto codeDto;
	private GroupCodeDto groupCodeDto;
	private List<CodeDto> codeDtoList;
	private List<GroupCodeDto> groupCodeDtoList;
	private long count;
}
