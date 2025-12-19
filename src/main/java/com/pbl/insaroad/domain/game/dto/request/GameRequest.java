/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.game.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Game 관련 요청 DTO 모음 */
public class GameRequest {

  /** 미방문 Location 전체 조회 요청 */
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class UnvisitedRequest {

    @NotBlank private String userCode;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CompleteRequest {

    @NotBlank private String userCode;

    @NotNull private Long currentLocationId;
  }
}
