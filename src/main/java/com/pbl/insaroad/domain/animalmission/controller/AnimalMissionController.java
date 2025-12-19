/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.animalmission.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbl.insaroad.domain.animalmission.dto.request.AnimalMissionSubmitRequest;
import com.pbl.insaroad.domain.animalmission.dto.response.AnimalResultResponse;
import com.pbl.insaroad.domain.animalmission.service.AnimalMissionService;
import com.pbl.insaroad.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/animal-mission")
@RequiredArgsConstructor
@Tag(name = "AnimalMission", description = "동물 미션 테스트 API")
public class AnimalMissionController {

  private final AnimalMissionService animalMissionService;

  @PostMapping("/submit")
  @Operation(
      summary = "동물 미션 테스트 결과 제출",
      description =
          "사용자의 선택(문양 2개, 입구 1개, 그림 1개)을 받아 최종 동물을 반환합니다. 동점일 경우 마지막 문항(그림) 선택을 결과로 반환합니다.")
  public BaseResponse<AnimalResultResponse> submitAnimalMission(
      @Valid @RequestBody AnimalMissionSubmitRequest request) {
    AnimalResultResponse result = animalMissionService.submitAnimalMission(request);
    return BaseResponse.success(result);
  }
}
