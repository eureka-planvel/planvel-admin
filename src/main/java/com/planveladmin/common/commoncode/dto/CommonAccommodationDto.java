package com.planveladmin.common.commoncode.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonAccommodationDto {
    private Integer id;
    private String name;
    private String address;
    private BigDecimal pricePerNight;
    private String typeCode;

}
