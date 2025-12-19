/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.location.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "locations")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "nameEn", length = 100)
  private String nameEn;

  @Column(name = "description", length = 500)
  private String description;

  @Column(name = "address", length = 200)
  private String address;

  @Column(name = "image_url", length = 500)
  private String imageUrl;

  /** 위도 (latitude) - 예: 37.5665 */
  @Column(name = "latitude")
  private Double latitude;

  /** 경도 (longitude) - 예: 126.9780 */
  @Column(name = "longitude")
  private Double longitude;
}
