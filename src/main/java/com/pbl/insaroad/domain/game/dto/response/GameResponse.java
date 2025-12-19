/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.game.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.pbl.insaroad.domain.location.dto.response.LocationResponse;

import lombok.Builder;
import lombok.Getter;

/** Game 관련 응답 DTO 모음 */
public class GameResponse {

  @Getter
  @Builder
  public static class StartResponse {

    private String userCode;

    /** 사용자가 지금 시작해야 하는 스테이지 */
    private int startStage;
  }

  /** 미방문 Location 전체 조회 응답 */
  @Getter
  @Builder
  public static class UnvisitedResponse {

    private List<LocationResponse> unvisitedLocations;
  }

  /** 게임 1회 완료 처리 응답 - 사용자 코드 - 아직 방문하지 않은 Location 전체 */
  @Getter
  @Builder
  public static class CompleteResponse {

    private String userCode;
    private List<LocationResponse> unvisitedLocations;
  }

  /** 전체 미션 완료 응답 (교환권 발급) */
  @Getter
  @Builder
  public static class FinishResponse {

    /** QR로 사용할 교환권 URL */
    private String exchangeUrl;

    /** 교환권 발급 시각 */
    private LocalDateTime issuedAt;
  }

  @Getter
  @Builder
  public static class GameProgressResponse {

    /** 이번 complete 처리로 전체 미션이 완료되었는지 여부 */
    private boolean completed;

    /** 미완료 시 채움 */
    private CompleteResponse complete;

    /** 완료 시 채움 (교환권 발급 정보) */
    private FinishResponse finish;
  }
}
