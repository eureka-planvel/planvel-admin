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

	@GetMapping("/groupcodes")
	public CodeResultDto listGroupCode(
		@RequestParam("pageNumber") int pageNumber,
		@RequestParam("pageSize") int pageSize
	) {
		return groupCodeService.listGroupCode(pageNumber, pageSize);
	}

	@GetMapping("/groupcodes/{groupCode}")
	public CodeResultDto detailGroupCode(@PathVariable("groupCode") String groupCode) {
		return groupCodeService.detailGroupCode(groupCode);
	}

	@PostMapping("/groupcodes")
	public CodeResultDto insertGroupCode(GroupCode groupCode) {
		return groupCodeService.insertGroupCode(groupCode);
	}

	@PutMapping("/groupcodes")
	public CodeResultDto updateGroupCode(GroupCode groupCode) {
		return groupCodeService.updateGroupCode(groupCode);
	}

	@DeleteMapping("/groupcodes/{groupCode}")
	public CodeResultDto deleteGroupCode(@PathVariable("groupCode") String groupCode) {
		return groupCodeService.deleteGroupCode(groupCode);
	}

	@GetMapping("/groupcodes/count")
	public CodeResultDto countGroupCode() {
		return groupCodeService.countGroupCode();
	}
}
