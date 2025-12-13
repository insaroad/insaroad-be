/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.koreanname.dto.response;

import com.pbl.insaroad.domain.koreanname.entity.ElementType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "SyllableExplanationResponse: 이름 한 글자에 대한 해설")
public class SyllableExplanationResponse {

  @Schema(description = "한글 글자", example = "슬")
  private String syllable;

  @Schema(description = "로마자 표기", example = "Seul")
  private String romanization;

  @Schema(description = "이 글자가 보완하는 오행", example = "WATER")
  private ElementType elementType;

  @Schema(description = "글자에 대한 간단한 해설")
  private String description;
}
