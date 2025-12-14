package com.pbl.insaroad.domain.animalmission.data;

import java.util.Arrays;

import com.pbl.insaroad.domain.animalmission.dto.response.AnimalResultResponse;
import com.pbl.insaroad.domain.animalmission.dto.response.DescriptionResponse;
import com.pbl.insaroad.domain.animalmission.dto.response.ResultTextSegment;
import com.pbl.insaroad.domain.animalmission.entity.AnimalType;

public class Haetae {
  public static final AnimalResultResponse CONTENT =
      AnimalResultResponse.from(
          AnimalType.HAETAE,
          "[옳고 그름을 가리는 수호자(守護者)]",
          "[해치도(獬豸圖)_민화풍(출처 미상)]",
          new DescriptionResponse(
              Arrays.asList(
                  new ResultTextSegment("당신은 정의·이성·균형의 상징인 ", false),
                  new ResultTextSegment("해치(獬豸)의 기질", true),
                  new ResultTextSegment("을 지녔습니다.\n\n", false),
                  new ResultTextSegment("해치는 조선시대 궁궐에서 화재를 막고 질서를 지키는 ", false),
                  new ResultTextSegment("방화·수호의 상징", true),
                  new ResultTextSegment("이었습니다. 고대 기록들과 민간전승에서 해태는 ", false),
                  new ResultTextSegment("\"죄인을 단번에 가려내는 공정한 신수\"", true),
                  new ResultTextSegment(
                      "로 기록되어있으며, 악을 물어뜯어 벌을 주고, 나쁜 행동을 한 사람은 뿔로 들이 받아버리는 신수로 등장합니다.\n해치는 순우리말 고어(古語)로, ",
                      false),
                  new ResultTextSegment("\"해님이 파견한 벼슬아치\"", true),
                  new ResultTextSegment("의 줄임말입니다.", false)),
              Arrays.asList(
                  new ResultTextSegment("[당신의 성향은...]\n", true),
                  new ResultTextSegment("1. 상황을 ", false),
                  new ResultTextSegment("차분히 관찰", true),
                  new ResultTextSegment("하고 ", false),
                  new ResultTextSegment("균형 있게 판단", true),
                  new ResultTextSegment("합니다.\n", false),
                  new ResultTextSegment("2. 감정보다 ", false),
                  new ResultTextSegment("이성과 논리를 우선시", true),
                  new ResultTextSegment("합니다.\n", false),
                  new ResultTextSegment("3. 타고난 기질이 신중하여 판단하는데에 시간이 걸리지만, ", false),
                  new ResultTextSegment("결단한 이후에는 빠르고 신속하게 일을 처리합니다", true),
                  new ResultTextSegment(".", false))));
}
