/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.koreanname.dto.response;

import com.pbl.insaroad.domain.koreanname.entity.ElementType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "GPT가 반환하는 이름 한 글자 정보 (내부용)")
public class LetterMeaningGptResponse {

  @Schema(description = "이름 한 글자", example = "세")
  private String letter;

  @Schema(description = "해당 글자의 오행 (WATER/FIRE/METAL/WOOD/EARTH)", example = "METAL")
  private ElementType element;

  @Schema(description = "해당 글자의 기운 해설")
  private String description;
}
