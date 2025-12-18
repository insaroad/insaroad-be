/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.hangulsign.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {

  @Schema(description = "정답 여부", example = "true")
  private Boolean isCorrect;

  @Schema(description = "정답 이미지 URL (정답인 경우에만 제공)", example = "https://example.com/answer.jpg")
  private String answerImageUrl;

  @Schema(description = "현재 스테이지", example = "3")
  private Integer currentStage;
}
