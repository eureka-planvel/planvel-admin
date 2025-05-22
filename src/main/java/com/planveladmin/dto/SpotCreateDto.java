package com.planveladmin.dto;

import com.planveladmin.domain.Spot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotCreateDto {
    private Integer regionId;
    private String spotName;
    private String address;
    private String type;
    private String imageUrl;
    private String thumbnailUrl;

    public Spot toEntity() {
        return Spot.builder()
                .regionId(this.regionId)
                .spotName(this.spotName)
                .address(this.address)
                .type(this.type)
                .imageUrl(this.imageUrl)
                .thumbnailUrl(this.thumbnailUrl)
                .build();
    }
}
