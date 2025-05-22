package com.planveladmin.common.commoncode.entity;

import com.planveladmin.common.commoncode.entity.key.CodeKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Code {

    @EmbeddedId
    CodeKey codeKey;

    @Column(name="code_name")
    private String codeName;

    @Column(name="code_name_brief")
    private String codeNameBrief;

    @Column(name="order_no")
    private int orderNo;
}
