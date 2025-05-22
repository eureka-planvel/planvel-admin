package com.planveladmin.controller;

import com.planveladmin.dto.CodeDto;
import com.planveladmin.dto.GroupCodeDto;
import com.planveladmin.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/codes")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @GetMapping
    public ResponseEntity<List<CodeDto>> getTourismCodes() {
        List<CodeDto> codes = codeService.getTourismCodes();
        return ResponseEntity.ok(codes);
    }

    @GetMapping("/group/{groupCode}")
    public ResponseEntity<List<CodeDto>> getCodesByGroup(@PathVariable String groupCode) {
        List<CodeDto> codes = codeService.getCodesByGroup(groupCode);
        return ResponseEntity.ok(codes);
    }

    @GetMapping("/groups")
    public ResponseEntity<List<GroupCodeDto>> getAllGroupCodes() {
        List<GroupCodeDto> groupCodes = codeService.getAllGroupCodes();
        return ResponseEntity.ok(groupCodes);
    }

    @GetMapping("/{groupCode}/{code}")
    public ResponseEntity<CodeDto> getCode(@PathVariable String groupCode,
                                           @PathVariable String code) {
        CodeDto codeDto = codeService.getCode(groupCode, code);
        return ResponseEntity.ok(codeDto);
    }

    @GetMapping("/exists/{groupCode}/{code}")
    public ResponseEntity<Boolean> existsCode(@PathVariable String groupCode,
                                              @PathVariable String code) {
        boolean exists = codeService.existsCode(groupCode, code);
        return ResponseEntity.ok(exists);
    }


}
