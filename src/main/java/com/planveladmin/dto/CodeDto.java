package com.planveladmin.dto;

import com.planveladmin.domain.Code;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeDto {
    private String groupCode;
    private String code;
    private String codeName;
    private String codeNameBrief;
    private String orderNo;

    public static CodeDto from(Code code) {
        return CodeDto.builder()
                .groupCode(code.getCodeKey().getGroupCode())
                .code(code.getCodeKey().getCode())
                .codeName(code.getCodeName())
                .codeNameBrief(code.getCodeNameBrief())
                .orderNo(code.getOrderNo())
                .build();
    }
}
