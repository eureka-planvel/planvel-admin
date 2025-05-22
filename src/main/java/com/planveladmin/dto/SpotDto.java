package com.planveladmin.dto;

import com.planveladmin.domain.Spot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotDto {
    private Integer id;
    private Integer regionId;
    private String regionName;
    private String spotName;
    private String address;
    private String type;
    private String typeName;
    private String imageUrl;
    private String thumbnailUrl;

    public static SpotDto from(Spot spot) {
        return SpotDto.builder()
                .id(spot.getId())
                .regionId(spot.getRegionId())
                .spotName(spot.getSpotName())
                .address(spot.getAddress())
                .type(spot.getType())
                .imageUrl(spot.getImageUrl())
                .thumbnailUrl(spot.getThumbnailUrl())
                .build();
    }
}