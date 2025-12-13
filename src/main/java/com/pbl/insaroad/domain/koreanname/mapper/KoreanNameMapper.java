/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.koreanname.mapper;

import java.util.List;

import com.pbl.insaroad.domain.koreanname.dto.response.KoreanNameAiResponse;
import com.pbl.insaroad.domain.koreanname.dto.response.KoreanNameResponse;
import com.pbl.insaroad.domain.koreanname.dto.response.SyllableExplanationResponse;
import com.pbl.insaroad.domain.koreanname.entity.ElementType;
import com.pbl.insaroad.domain.koreanname.exception.KoreanNameErrorCode;
import com.pbl.insaroad.global.exception.CustomException;

public class KoreanNameMapper {

  private KoreanNameMapper() {}

  public static KoreanNameResponse toResponse(KoreanNameAiResponse aiResponse) {
    ElementType mainElement = parseElement(aiResponse.mainElement());

    List<SyllableExplanationResponse> syllables =
        aiResponse.syllables().stream()
            .map(
                s ->
                    SyllableExplanationResponse.builder()
                        .syllable(s.syllable())
                        .romanization(s.romanization())
                        .elementType(parseElement(s.element()))
                        .description(s.description())
                        .build())
            .toList();

    String imageUrl = mainElement.getImageFileName();

    return KoreanNameResponse.builder()
        .koreanName(aiResponse.koreanName())
        .romanizedName(aiResponse.romanizedName())
        .mainElementType(mainElement)
        .elementDescription(aiResponse.elementDescription())
        .elementImageUrl(imageUrl)
        .overallExplanation(aiResponse.overallExplanation())
        .syllables(syllables)
        .build();
  }

  private static ElementType parseElement(String elementName) {
    try {
      return ElementType.valueOf(elementName.toUpperCase());
    } catch (Exception e) {
      throw new CustomException(KoreanNameErrorCode.INVALID_AI_RESPONSE);
    }
  }
}
