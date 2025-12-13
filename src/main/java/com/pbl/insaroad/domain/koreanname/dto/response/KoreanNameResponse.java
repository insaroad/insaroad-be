/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.koreanname.dto.response;

import java.util.List;

import com.pbl.insaroad.domain.koreanname.entity.ElementType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "KoreanNameResponse: 한국 이름 추천 응답")
public class KoreanNameResponse {

  @Schema(description = "추천 한글 이름", example = "슬설")
  private String koreanName;

  @Schema(description = "추천 이름의 로마자 표기", example = "Seul-seol")
  private String romanizedName;

  @Schema(description = "부족한 기운을 보완하는 핵심 오행 타입", example = "WATER")
  private ElementType mainElementType;

  @Schema(description = "오행 타입 설명", example = "물의 기운은 유연함과 감수성을 상징합니다.")
  private String elementDescription;

  @Schema(description = "오행 타입에 대응하는 이미지 URL", example = "https://.../water.png")
  private String elementImageUrl;

  @Schema(description = "이름 전체를 추천한 이유/해설")
  private String overallExplanation;

  @Schema(description = "각 글자(음절)별 해설 리스트")
  private List<SyllableExplanationResponse> syllables;
}
