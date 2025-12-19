/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.location.dto.response;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.pbl.insaroad.domain.location.entity.Location;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LocationResponse {

  private Long id;
  private String name;
  private String nameEn;

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
        .kakaoMapUrl(buildGoogleMapUrl(location.getName(), lat, lng))
        .build();
  }

  /**
   * 구글맵: 이름 + 좌표로 지도 열기 - 모바일/웹 모두 동작 - 좌표가 없으면 null
   *
   * <p>예: https://www.google.com/maps/search/?api=1&query=서경대학교&query_place_id=... 또는
   * https://www.google.com/maps/search/?api=1&query=서경대학교,37.6149,127.0116
   */
  private static String buildGoogleMapUrl(String name, Double latitude, Double longitude) {
    if (latitude == null || longitude == null) {
      return null;
    }

    String encodedName = name != null ? URLEncoder.encode(name, StandardCharsets.UTF_8) : "";

    return String.format(
        "https://www.google.com/maps/search/?api=1&query=%s,%.6f,%.6f",
        encodedName, latitude, longitude);
  }
}
