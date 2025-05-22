package com.planveladmin.service;

import com.planveladmin.dto.CodeDto;
import com.planveladmin.dto.GroupCodeDto;
import com.planveladmin.repository.CodeRepository;
import com.planveladmin.repository.GroupCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;
    private final GroupCodeRepository groupCodeRepository;

    public List<CodeDto> getTourismCodes() {
        return codeRepository.findByGroupCodeOrderByOrderNo("030")
                .stream()
                .map(CodeDto::from)
                .collect(Collectors.toList());
    }

    public List<CodeDto> getCodesByGroup(String groupcode) {
        return codeRepository.findByGroupCodeOrderByOrderNo(groupcode)
                .stream()
                .map(CodeDto::from)
                .collect(Collectors.toList());
    }

    public List<GroupCodeDto> getAllGroupCodes() {
        return groupCodeRepository.findAll()
                .stream()
                .map(GroupCodeDto::from)
                .collect(Collectors.toList());
    }

    public CodeDto getCode(String groupCode, String code) {
        return codeRepository.findByGroupCodeAndCode(groupCode, code)
                .map(CodeDto::from)
                .orElseThrow(() -> new RuntimeException("코드를 찾을 수 없습니다: " + groupCode + "-" + code));
    }

    public boolean existsCode(String groupCode, String code) {
        return codeRepository.findByGroupCodeAndCode(groupCode, code).isPresent();
    }

    public String getCodeByName(String groupCode, String codeName) {
        return codeRepository.findByGroupCodeOrderByOrderNo(groupCode)
                .stream()
                .filter(code -> code.getCodeName().equals(codeName))
                .map(code -> code.getCodeKey().getCode())
                .findFirst()
                .orElse(null);
    }
}
