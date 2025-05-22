package com.planveladmin.common.commoncode.controller;

import com.planveladmin.common.commoncode.dto.CodeResultDto;
import com.planveladmin.common.commoncode.entity.GroupCode;
import com.planveladmin.common.commoncode.service.GroupCodeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GroupCodeController {

	private final GroupCodeService groupCodeService;
	
	// 페이징된 목록
	@GetMapping("/groupcodes")
	public CodeResultDto listGroupCode(
		@RequestParam("pageNumber") int pageNumber,
		@RequestParam("pageSize") int pageSize
	) {
		return groupCodeService.listGroupCode(pageNumber, pageSize);
	}
	
	// 상세
	@GetMapping("/groupcodes/{groupCode}")
	public CodeResultDto detailGroupCode(@PathVariable("groupCode") String groupCode) {
		return groupCodeService.detailGroupCode(groupCode);
	}
	
	// 등록
	@PostMapping("/groupcodes")
	public CodeResultDto insertGroupCode(GroupCode groupCode) {
		return groupCodeService.insertGroupCode(groupCode);
	}
	
	// 수정
	@PutMapping("/groupcodes")
	public CodeResultDto updateGroupCode(GroupCode groupCode) {
		return groupCodeService.updateGroupCode(groupCode);
	}
	
	// 삭제
	@DeleteMapping("/groupcodes/{groupCode}")
	public CodeResultDto deleteGroupCode(@PathVariable("groupCode") String groupCode) {
		return groupCodeService.deleteGroupCode(groupCode);
	}
	
	// count
	@GetMapping("/groupcodes/count")
	public CodeResultDto countGroupCode() {
		return groupCodeService.countGroupCode();
	}
}
