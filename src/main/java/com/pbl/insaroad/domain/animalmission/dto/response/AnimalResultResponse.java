/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.animalmission.dto.response;

import com.pbl.insaroad.domain.animalmission.entity.AnimalType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "동물 미션 테스트 결과 응답")
public class AnimalResultResponse {
  @Schema(description = "동물 한글명", example = "호랑이")
  private String animal;

  @Schema(description = "동물의 상징 제목", example = "[기세(氣勢)와 용맹(勇猛)의 상징]")
  private String title;

  @Schema(description = "이미지 캡션", example = "[작호도(鵲虎圖), 민화풍(출처 미상)]")
  private String caption;

  @Schema(description = "동물에 대한 상세 설명")
  private DescriptionResponse description;

  public static AnimalResultResponse from(
      AnimalType animalType, String title, String caption, DescriptionResponse description) {
    return new AnimalResultResponse(animalType.name(), title, caption, description);
  }
}
