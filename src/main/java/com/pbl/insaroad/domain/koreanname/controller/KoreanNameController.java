/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.koreanname.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pbl.insaroad.domain.koreanname.dto.request.KoreanNameRequest;
import com.pbl.insaroad.domain.koreanname.dto.response.KoreanNameResponse;
import com.pbl.insaroad.domain.koreanname.service.KoreanNameService;
import com.pbl.insaroad.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "KoreanName", description = "한국 이름 추천 API")
public class KoreanNameController {

  private final KoreanNameService koreanNameService;

  @Operation(
      summary = "사주 기반 한국 이름 추천",
      description = "생년월일과 성별을 입력받아 사주명리 기반으로 오행을 분석하고, " + "부족한 기운을 보완하는 한국 이름을 추천합니다.")
  @PostMapping("/korean-names/recommend")
  public ResponseEntity<BaseResponse<KoreanNameResponse>> recommendKoreanName(
      @RequestBody KoreanNameRequest request) {
    KoreanNameResponse response = koreanNameService.recommendName(request);
    return ResponseEntity.ok(BaseResponse.success("한국 이름 추천 성공", response));
  }
}
