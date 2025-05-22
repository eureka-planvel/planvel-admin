package com.planveladmin.domain;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="accommodations")
@Setter
@Getter
@ToString
public class Accommodation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer regionId;
  private String name;
  private String address;
  private BigDecimal pricePerNight;
  private String imageUrl;
  private String thumbnailUrl;

  @Column(name = "type_code")
  private String typeCode;
}