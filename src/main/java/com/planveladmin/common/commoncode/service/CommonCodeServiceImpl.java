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

            for (Code code : codeList) {
                String groupCode = code.getCodeKey().getGroupCode();
                
                if(! currGroupCode.equals(groupCode)) {

                    if( Strings.isNotEmpty(currGroupCode) ) {  
                        commonCodeListMap.put(currGroupCode, codeDtoList);
                    }
                    currGroupCode = groupCode;
                    codeDtoList = new ArrayList<>();
                }
                
                codeDtoList.add(CodeDto.fromCode(code));                    
            }
            
            commonCodeListMap.put(currGroupCode, codeDtoList);
            
            
            commonCodeResultDto.setCommonCodeDtoListMap(commonCodeListMap);
            commonCodeResultDto.setResult("success");
        }catch(Exception e) {
            e.printStackTrace();
            commonCodeResultDto.setResult("fail");
        }
        return commonCodeResultDto;
    }   
}