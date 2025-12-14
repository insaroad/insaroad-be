package com.pbl.insaroad.domain.animalmission.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "동물에 대한 상세 설명 정보")
public class DescriptionResponse {
  @Schema(description = "동물에 대한 소개 설명")
  private List<ResultTextSegment> intro;

  @Schema(description = "사용자의 성향 및 특징")
  private List<ResultTextSegment> traits;
}
