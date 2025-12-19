/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.animalmission.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.pbl.insaroad.domain.animalmission.entity.AnimalType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "동물 미션 테스트 결과 제출 요청")
public class AnimalMissionSubmitRequest {

  @NotNull @Size(min = 2, max = 2, message = "문양은 2개를 선택해야 합니다.")
  @Schema(description = "문양 선택 (2개)", example = "[\"TIGER\", \"CRANE\"]")
  private List<AnimalType> patternAnimals;

  @NotNull(message = "입구 행동 선택은 필수입니다.") @Schema(description = "입구 행동 선택 (1개)", example = "MAGPIE")
  private AnimalType entranceAnimal;

  @NotNull(message = "민화 그림 선택은 필수입니다.") @Schema(description = "민화 그림 선택 (1개, 동점 시 최종 결과 결정용)", example = "TURTLE")
  private AnimalType paintingAnimal;
}
