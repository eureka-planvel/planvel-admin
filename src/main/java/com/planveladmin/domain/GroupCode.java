package com.planveladmin.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Persistable;

@Entity
@Data
@Table(name = "group_code")
public class GroupCode implements Persistable<String> {
    @Id
    @Column(name = "group_code")
    private String groupCode;

    @Column(name = "group_code_name")
    private String groupCodeName;

    @Column(name = "group_code_desc")
    private String groupCodeDesc;

    @Transient
    private boolean isNew = false;

    @Override
    public String getId() {
        return groupCode;
    }
}
