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
public class AnswerRequest {

  @NotBlank(message = "사용자 코드는 필수입니다.")
  @Schema(description = "사용자 코드 (3자리)", example = "123")
  private String userCode;

  @NotNull(message = "현재 Location ID는 필수입니다.") @Schema(description = "현재 Location ID", example = "1")
  private Long currentLocationId;

  @NotNull(message = "선택지는 필수입니다.") @Min(value = 1, message = "선택지는 1, 2, 3 중 하나여야 합니다.")
  @Max(value = 3, message = "선택지는 1, 2, 3 중 하나여야 합니다.")
  @Schema(description = "사용자가 선택한 답 (1, 2, 3)", example = "1")
  private Integer userAnswer;
}
