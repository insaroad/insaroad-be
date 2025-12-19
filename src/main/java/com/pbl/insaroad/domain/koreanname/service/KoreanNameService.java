/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.koreanname.service;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pbl.insaroad.domain.game.dto.request.GameRequest.CompleteRequest;
import com.pbl.insaroad.domain.koreanname.dto.request.KoreanNameRequest;
import com.pbl.insaroad.domain.koreanname.dto.response.KoreanNameAiResponse;
import com.pbl.insaroad.domain.koreanname.dto.response.KoreanNameResponse;
import com.pbl.insaroad.domain.koreanname.entity.Gender;
import com.pbl.insaroad.domain.koreanname.mapper.KoreanNameMapper;
import com.pbl.insaroad.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KoreanNameService {

  private final KoreanNameAiClient koreanNameAiClient;
  private final UserService userService;

  @Transactional(readOnly = true)
  public KoreanNameResponse recommendName(
      KoreanNameRequest request, CompleteRequest completeRequest) {
    String systemPrompt =
        """
            당신은 한국의 사주명리 전문가이자 이름 작명가입니다.
            사용자 생년월일과 성별을 바탕으로 오행(목,화,토,금,수)의 분포를 분석합니다.

            - mainElement는 '추천 이름의 오행'이 아니라, '사용자의 가장 강한(우세한) 오행'입니다.
            - elementDescription은 사용자의 mainElement 성향/강점을 자연스럽게 설명합니다.
            - overallExplanation은 반드시 다음 흐름을 자연스럽게 한 문단으로 작성하세요:
              사용자의 우세 오행 성향/강점 → 보완이 필요한 오행 → 왜 이 이름이 그 보완에 도움이 되는지
            - 금지: 숫자 목록(1), 2), 3)), 화살표(→), "첫째/둘째/셋째", "순서대로", "다음과 같이" 같은 메타 표현
            - 자연어 톤 예시(형식만 참고, 그대로 복붙 금지):
              "당신은 수의 기운이 강해 직관과 감수성이 돋보입니다. 다만 목의 기운이 보완되면 ... 그래서 ..."

            반드시 아래 JSON 스키마를 따르세요. JSON 외의 텍스트는 절대 출력하지 마세요.

            {
              "koreanName": "한글 이름",
              "romanizedName": "로마자 표기",
              "mainElement": "WATER | FIRE | METAL | WOOD | EARTH",
              "elementDescription": "사용자의 우세 오행 설명",
              "overallExplanation": "자연어 한 문단 설명",
              "syllables": [
                {
                  "syllable": "슬",
                  "romanization": "Seul",
                  "element": "WATER",
                  "description": "이 글자가 보완하는 기운에 대한 짧은 설명"
                }
              ]
            }
            """;

    String birth = request.getBirthDate().format(DateTimeFormatter.ISO_DATE);
    String gender = (request.getGender() == Gender.MALE) ? "남자" : "여자";

    String userPrompt =
        """
            생년월일: %s
            성별: %s

            아래 요구사항을 반드시 지키세요.

            - overallExplanation은 "당신은 ~의 기운이 강해 ..."로 시작하는 자연스러운 문단으로 작성하세요.
            - 숫자/화살표/메타표현(순서, 단계, 첫째/둘째 등)은 절대 쓰지 마세요.
            - '이름의 각 글자 설명(syllables)'는 지금처럼 유지하세요.

            JSON 형식으로만 답변하세요.
            """
            .formatted(birth, gender);

    KoreanNameAiResponse aiResponse =
        koreanNameAiClient.requestKoreanName(systemPrompt, userPrompt);

    int stage = userService.getStageByUserCode(completeRequest.getUserCode());
    if (stage == 1) {
      userService.completeGame(
          new CompleteRequest(
              completeRequest.getUserCode(), completeRequest.getCurrentLocationId()));
    } else {
      log.info(
          "[KoreanName] userCode={} stage={} -> completeGame skip",
          completeRequest.getUserCode(),
          stage);
    }
    return KoreanNameMapper.toResponse(aiResponse);
  }
}
