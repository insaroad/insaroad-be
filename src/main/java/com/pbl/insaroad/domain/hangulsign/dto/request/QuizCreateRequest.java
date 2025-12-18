/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.hangulsign.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizCreateRequest {

  @NotBlank(message = "문제 이미지 URL은 필수입니다.")
  @Schema(description = "문제 이미지 URL", example = "https://example.com/question.jpg")
  private String questionImageUrl;

  @NotBlank(message = "정답 이미지 URL은 필수입니다.")
  @Schema(description = "정답 이미지 URL", example = "https://example.com/answer.jpg")
  private String answerImageUrl;

  @NotBlank(message = "선택지 1은 필수입니다.")
  @Schema(description = "선택지 1", example = "스타벅수")
  private String choice1;

  @NotBlank(message = "선택지 2는 필수입니다.")
  @Schema(description = "선택지 2", example = "스타벅스")
  private String choice2;

  @NotBlank(message = "선택지 3은 필수입니다.")
  @Schema(description = "선택지 3", example = "스타벜스")
  private String choice3;

  @NotNull(message = "정답 선택지는 필수입니다.") @Min(value = 1, message = "정답 선택지는 1, 2, 3 중 하나여야 합니다.")
  @Max(value = 3, message = "정답 선택지는 1, 2, 3 중 하나여야 합니다.")
  @Schema(description = "정답 선택지 번호", example = "2")
  private Integer correctChoice;
}
