package com.planveladmin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotUpdateDto {
    private Integer regionId;
    private String spotName;
    private String address;
    private String type;
    private String imageUrl;
    private String thumbnailUrl;
}
