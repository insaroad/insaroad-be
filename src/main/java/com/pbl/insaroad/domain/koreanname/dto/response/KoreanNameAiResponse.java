/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.koreanname.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KoreanNameAiResponse(
    @JsonProperty("koreanName") String koreanName,
    @JsonProperty("romanizedName") String romanizedName,
    @JsonProperty("mainElement") String mainElement, // "WATER", "FIRE" 등
    @JsonProperty("elementDescription") String elementDescription,
    @JsonProperty("overallExplanation") String overallExplanation,
    @JsonProperty("syllables") List<SyllableInfo> syllables) {
  public record SyllableInfo(
      @JsonProperty("syllable") String syllable,
      @JsonProperty("romanization") String romanization,
      @JsonProperty("element") String element, // 각 글자의 보완 오행
      @JsonProperty("description") String description) {}
}
