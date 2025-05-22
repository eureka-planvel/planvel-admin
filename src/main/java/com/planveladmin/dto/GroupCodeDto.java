package com.planveladmin.dto;

import com.planveladmin.domain.GroupCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupCodeDto {
    private String groupCode;
    private String groupCodeName;
    private String groupCodeDesc;
    private List<CodeDto> codes;

    public static GroupCodeDto from(GroupCode groupCode) {
        return GroupCodeDto.builder()
                .groupCode(groupCode.getGroupCode())
                .groupCodeName(groupCode.getGroupCodeName())
                .groupCodeDesc(groupCode.getGroupCodeDesc())
                .build();
    }
}

