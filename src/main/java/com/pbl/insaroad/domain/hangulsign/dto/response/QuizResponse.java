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
public class QuizResponse {

  @Schema(description = "퀴즈 ID", example = "1")
  private Long id;

  @Schema(description = "문제 이미지 URL", example = "https://example.com/question.jpg")
  private String questionImageUrl;

  @Schema(description = "선택지 1", example = "김밥천국")
  private String choice1;

  @Schema(description = "선택지 2", example = "김밥나라")
  private String choice2;

  @Schema(description = "선택지 3", example = "김밥왕국")
  private String choice3;

  @Schema(description = "정답 선택지 번호", example = "2")
  private Integer correctChoice;
}
