/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.game.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbl.insaroad.domain.game.dto.request.GameRequest.CompleteRequest;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.GameProgressResponse;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.StartResponse;
import com.pbl.insaroad.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Game", description = "게임 관련 API")
@RequestMapping("/api/games")
public interface GameController {

  @Operation(
      summary = "새로 시작하기",
      description = "새 사용자 코드를 발급하고 시작 스테이지(기본 1)를 반환합니다. - LocalStorage에 저장 필요")
  @PostMapping("/start")
  BaseResponse<StartResponse> startNew();

  @Operation(summary = "이어서 시작하기", description = "사용자 코드를 기반으로 현재 진행 스테이지를 조회하여 반환합니다.")
  @GetMapping("/start")
  BaseResponse<StartResponse> resume(@RequestParam("userCode") String userCode);

  @PostMapping("/complete")
  @Operation(
      summary = "[개발자] 게임 진행/완료 처리",
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

  @Operation(summary = "[Web] 정보 조회", description = "사용자 코드로 완주 여부에 따라 다른 화면 정보를 반환합니다.")
  @GetMapping("/progress")
  BaseResponse<GameProgressResponse> getProgress(@RequestParam String userCode);
}
