package com.planveladmin.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spots")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "region_id")
  private Integer regionId;

  @Column(name = "spot_name", nullable = false, length = 100)
  private String spotName;

  @Column(name = "address", length = 200)
  private String address;

  @Column(name = "type", length = 3)
  private String type;

  @Column(name = "image_url", length = 500)
  private String imageUrl;

  @Column(name = "thumbnail_url", length = 500)
  private String thumbnailUrl;
}
