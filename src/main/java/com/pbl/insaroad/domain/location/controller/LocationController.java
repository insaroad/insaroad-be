/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.location.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pbl.insaroad.domain.game.dto.request.GameRequest;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.UnvisitedResponse;
import com.pbl.insaroad.domain.location.dto.LocationResponse;
import com.pbl.insaroad.domain.location.dto.request.LocationRequest;
import com.pbl.insaroad.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Location", description = "Location 관련 API")
@RequestMapping("/api/locations")
public interface LocationController {

  @PostMapping
  @Operation(summary = "Location 생성", description = "Location을 생성합니다.")
  ResponseEntity<BaseResponse<LocationResponse>> create(
      @RequestBody @Valid LocationRequest.CreateLocationRequest request);

  @DeleteMapping("/{id}")
  @Operation(summary = "Location 삭제", description = "Location을 삭제합니다.")
  ResponseEntity<BaseResponse<Void>> deleteById(@PathVariable("id") Long locationId);

  @PostMapping("/unvisited")
  @Operation(
      summary = "미방문 Location 전체 조회",
      description = "사용자 기준으로 아직 방문하지 않은 Location 목록 전체를 반환합니다.")
  ResponseEntity<BaseResponse<UnvisitedResponse>> unvisited(
      @RequestBody @Valid GameRequest.UnvisitedRequest request);
}
