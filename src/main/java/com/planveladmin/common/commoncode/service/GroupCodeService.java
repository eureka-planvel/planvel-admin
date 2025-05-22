package com.planveladmin.common.commoncode.service;

import com.planveladmin.common.commoncode.dto.CodeResultDto;
import com.planveladmin.common.commoncode.entity.GroupCode;

public interface GroupCodeService {
	CodeResultDto insertGroupCode(GroupCode groupCode);
	CodeResultDto updateGroupCode(GroupCode groupCode);
	CodeResultDto deleteGroupCode(String groupCode);
	
	CodeResultDto listGroupCode(int pageNumber, int pageSize);
	CodeResultDto detailGroupCode(String groupCode);
	CodeResultDto countGroupCode();
}
