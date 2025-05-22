package com.planveladmin.common.commoncode.controller;

import com.planveladmin.common.commoncode.dto.CodeResultDto;
import com.planveladmin.common.commoncode.entity.Code;
import com.planveladmin.common.commoncode.entity.key.CodeKey;
import com.planveladmin.common.commoncode.service.CodeService;
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
public class CodeController {

	private final CodeService codeService;
	
	// 페이징된 목록
	@GetMapping("/codes")
	public CodeResultDto listCode(
		@RequestParam("groupCode") String groupCode,
		@RequestParam("pageNumber") int pageNumber,
		@RequestParam("pageSize") int pageSize
	) {
		return codeService.listCode(groupCode, pageNumber, pageSize);
	}
	
	// 상세
	@GetMapping("/codes/{groupCode}/{code}")
	public CodeResultDto detailCode(@PathVariable("groupCode") String groupCode, @PathVariable("code") String code) {
		CodeKey codeKey = new CodeKey(groupCode, code);
		return codeService.detailCode(codeKey);
	}
	
	// 등록
//	@PostMapping("/codes")
//	public CodeResultDto insertCode(
//		// Code 는 key 부분이 CodeKey로 되어 있어서 바로 받지 못하고 분리해서 받는데 필수이므로
//		// 아래 두 항목이 누락되지 않도록
//		@RequestParam("groupCode") String groupCode,
//		@RequestParam("code") String code,
//		Code codeParam
//	) {
//		CodeKey codeKey = new CodeKey(groupCode, code);
//		codeParam.setCodeKey(codeKey);
//		return codeService.insertCode(codeParam);
//	}
	// 등록
    @PostMapping("/codes")
    public CodeResultDto insertCode(
        // Code 는 key 부분이 CodeKey 로 되어 있어서 바로 받지 못하고 분리해서 받는데 필수이므로
        // 아래 두 항목이 누락되지 않도록
        @RequestParam("groupCode") String groupCode,
        @RequestParam("code") String code,
        @RequestParam("codeName") String codeName,
        @RequestParam("codeNameBrief") String codeNameBrief,
        @RequestParam("orderNo") int orderNo
    ) {
        CodeKey codeKey = new CodeKey(groupCode, code);
        Code codeEntity = new Code();
        codeEntity.setCodeKey(codeKey);
        codeEntity.setCodeName(codeName);
        codeEntity.setCodeNameBrief(codeNameBrief);
        codeEntity.setOrderNo(orderNo);
        return codeService.insertCode(codeEntity);
    }
	
	// 수정
	@PutMapping("/codes")
	public CodeResultDto updateCode( 
		@RequestParam("groupCode") String groupCode,
		@RequestParam("code") String code,
		Code codeParam
	) {
		CodeKey codeKey = new CodeKey(groupCode, code);
		codeParam.setCodeKey(codeKey);
		return codeService.updateCode(codeParam);
	}
	
	// 삭제
	@DeleteMapping("/codes/{groupCode}/{code}")
	public CodeResultDto deleteCode(@PathVariable("groupCode") String groupCode, @PathVariable("code") String code) {
		CodeKey codeKey = new CodeKey(groupCode, code);
		return codeService.deleteCode(codeKey);
	}
	
	// count
	@GetMapping("/codes/count")
	public CodeResultDto countCode() {
		return codeService.countCode();
	}
}
