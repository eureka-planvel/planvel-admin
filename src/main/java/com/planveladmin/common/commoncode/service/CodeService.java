package com.planveladmin.common.commoncode.service;

import com.planveladmin.common.commoncode.dto.CodeResultDto;
import com.planveladmin.common.commoncode.entity.Code;
import com.planveladmin.common.commoncode.entity.key.CodeKey;

public interface CodeService {
	CodeResultDto insertCode(Code code);
	CodeResultDto updateCode(Code code);
	CodeResultDto deleteCode(CodeKey codeKey);
	
	CodeResultDto listCode(String groupCode, int pageNumber, int pageSize);
	CodeResultDto detailCode(CodeKey codeKey);
	CodeResultDto countCode();
}
