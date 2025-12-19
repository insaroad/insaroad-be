/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.location.dto;

import com.pbl.insaroad.domain.location.entity.Location;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LocationResponse {

  private Long id;
  private String name;
  private String description;
  private String address;
  private String imageUrl;

  private Double latitude;
  private Double longitude;

  /** 카카오맵 링크(위경도 기반) - 위경도가 없으면 null */
  private String kakaoMapUrl;

  public static LocationResponse from(Location location) {
    Double lat = location.getLatitude();
    Double lng = location.getLongitude();

    return LocationResponse.builder()
        .id(location.getId())
        .name(location.getName())
        .description(location.getDescription())
        .address(location.getAddress())
        .imageUrl(location.getImageUrl())
        .latitude(lat)
        .longitude(lng)
        .kakaoMapUrl(buildKakaoMapUrl(lat, lng))
        .build();
  }

  /** 카카오맵: 좌표로 지도 열기 - 모바일/웹 모두 동작하는 "map.kakao.com" 방식 - 좌표가 없으면 null */
  private static String buildKakaoMapUrl(Double latitude, Double longitude) {
    if (latitude == null || longitude == null) {
      return null;
    }
    // 예: https://map.kakao.com/link/map/37.5665,126.9780
    return "https://map.kakao.com/link/map/" + latitude + "," + longitude;
  }
}
