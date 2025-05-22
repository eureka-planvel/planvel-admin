package com.planveladmin.common.commoncode.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.planveladmin.common.commoncode.dto.CodeDto;
import com.planveladmin.common.commoncode.dto.CommonCodeResultDto;
import com.planveladmin.common.commoncode.entity.Code;
import com.planveladmin.common.commoncode.repository.CommonCodeRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonCodeServiceImpl implements CommonCodeService{
    private final CommonCodeRepository commonCodeRepository;
    
    @Override
    public CommonCodeResultDto getCommonCodeList(List<String> groupCodes) {
        CommonCodeResultDto commonCodeResultDto = new CommonCodeResultDto();
        try {
            List<Code> codeList = commonCodeRepository.findByGroupCodes(groupCodes);
            Map<String, List<CodeDto>> commonCodeListMap = new HashMap<>();
            String currGroupCode = "";
            List<CodeDto> codeDtoList = null;
            
            // 주의
            // codeList를 순회 처리시 lambda를 사용 x
            // 먼저 정의된 local 변수 사용 x
            for (Code code : codeList) {
                String groupCode = code.getCodeKey().getGroupCode();
                
                if(! currGroupCode.equals(groupCode)) { // 두 그룹코드가 다르면
                    
                    // 최초가 아닌 currGroupCode 가 유효한 상황에서 변경되었다면
                    // 현재까지의 codeDtoList 를 commonCodeListMap 에 담는다.
                    if( Strings.isNotEmpty(currGroupCode) ) {  
                        commonCodeListMap.put(currGroupCode, codeDtoList);
                    }
                    // 새로운 코드를 받을 준비를 한다.
                    currGroupCode = groupCode;
                    codeDtoList = new ArrayList<>();
                }
                
                codeDtoList.add(CodeDto.fromCode(code));                    
            }
            
            commonCodeListMap.put(currGroupCode, codeDtoList); // 마지막 currGroupCode
            
            
            commonCodeResultDto.setCommonCodeDtoListMap(commonCodeListMap);
            commonCodeResultDto.setResult("success");
        }catch(Exception e) {
            e.printStackTrace();
            commonCodeResultDto.setResult("fail");
        }
        return commonCodeResultDto;
    }   
}