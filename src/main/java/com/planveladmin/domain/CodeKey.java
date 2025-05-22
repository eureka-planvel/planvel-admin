package com.planveladmin.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class CodeKey implements Serializable {
    private String groupCode;
    private String code;

    public CodeKey(String groupCode, String code) {
        this.groupCode = groupCode;
        this.code = code;
    }

    public CodeKey() {}
}