/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.animalmission.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "결과 설명의 텍스트 세그먼트")
public class ResultTextSegment {
  @Schema(description = "텍스트 내용", example = "당신은 호랑이의 기질을 지녔습니다.")
  private String text;

  @Schema(description = "텍스트 강조 여부", example = "true")
  private Boolean bold;
}
