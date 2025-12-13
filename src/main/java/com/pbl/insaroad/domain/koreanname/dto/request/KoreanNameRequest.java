/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.koreanname.dto.request;

import java.time.LocalDate;

import com.pbl.insaroad.domain.koreanname.entity.Gender;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(title = "KoreanNameRequest: 한국 이름 추천 요청")
public class KoreanNameRequest {

  @Schema(description = "생년월일", example = "2002-11-25")
  private LocalDate birthDate;

  @Schema(description = "성별(MALE/FEMALE)", example = "MALE")
  private Gender gender;
}
