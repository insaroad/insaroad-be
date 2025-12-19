/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.location.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LocationRequest {

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateLocationRequest {

    @NotBlank private String name;
    private String nameEn;

    private String description;
    private String address;
    private String imageUrl;

    private Double latitude;
    private Double longitude;
  }
}
