/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.game.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pbl.insaroad.domain.game.dto.request.GameRequest.CompleteRequest;
import com.pbl.insaroad.domain.game.dto.request.GameRequest.UnvisitedRequest;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.GameProgressResponse;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.UnvisitedResponse;
import com.pbl.insaroad.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Game", description = "게임 관련 API")
@RequestMapping("/api/games")
public interface GameController {

  @PostMapping("/unvisited")
  @Operation(
      summary = "미방문 Location 전체 조회",
      description = "사용자 기준으로 아직 방문하지 않은 Location 목록 전체를 반환합니다.")
  ResponseEntity<BaseResponse<UnvisitedResponse>> unvisited(
      @RequestBody @Valid UnvisitedRequest request);

  @PostMapping("/complete")
  @Operation(
      summary = "게임 진행/완료 처리",
      description =
          """
              현재 Location 방문을 처리합니다.

              - 미션이 아직 남아있는 경우:
                사용자 방문 이력을 갱신하고, 아직 방문하지 않은 Location 목록을 반환합니다.

              - 이번 방문으로 모든 Location을 방문한 경우:
                미션을 완료 처리하고, 교환권 발급 정보를 함께 반환합니다.
              """)
  ResponseEntity<BaseResponse<GameProgressResponse>> complete(
      @RequestBody @Valid CompleteRequest request);
}
