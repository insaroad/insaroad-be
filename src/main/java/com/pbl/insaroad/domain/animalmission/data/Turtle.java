package com.pbl.insaroad.domain.animalmission.data;

import java.util.Arrays;

import com.pbl.insaroad.domain.animalmission.dto.response.AnimalResultResponse;
import com.pbl.insaroad.domain.animalmission.dto.response.DescriptionResponse;
import com.pbl.insaroad.domain.animalmission.dto.response.ResultTextSegment;
import com.pbl.insaroad.domain.animalmission.entity.AnimalType;

public class Turtle {
  public static final AnimalResultResponse CONTENT =
      AnimalResultResponse.from(
          AnimalType.TURTLE,
          "[인내(忍耐)와 신뢰(信賴)의 상징]",
          "[효현왕후경릉산릉도감의궤(孝顯王后景陵山陵都監儀軌), 거북]",
          new DescriptionResponse(
              Arrays.asList(
                  new ResultTextSegment("당신은 장수·인내·신뢰의 상징인 ", false),
                  new ResultTextSegment("거북(龜)의 기질", true),
                  new ResultTextSegment("을 지녔습니다.\n\n", false),
                  new ResultTextSegment("거북은 십장생도에서 \"", false),
                  new ResultTextSegment("변하지 않는 생명력", true),
                  new ResultTextSegment("\"의 상징으로 등장합니다. 해당 십장생도(해·산·물·거북 등)에서 오래 이어지는 삶과 ", false),
                  new ResultTextSegment("신중함을 대표", true),
                  new ResultTextSegment("하며, 각종 민담에서 거북은 ", false),
                  new ResultTextSegment("지혜로운 조언자, 인내심이 강한 존재", true),
                  new ResultTextSegment(
                      "로 등장합니다.\n조선시대 무덤과 궁궐에도 거북 모양의 귀부(비석 받침)이 사용되었으며, 이는 ", false),
                  new ResultTextSegment("나라의 안정·근본을 지키는 상징", true),
                  new ResultTextSegment("으로 쓰였습니다.", false)),
              Arrays.asList(
                  new ResultTextSegment("[당신의 성향은...]\n", true),
                  new ResultTextSegment("1. 한 번 시작한 일은 ", false),
                  new ResultTextSegment("꾸준히 끝까지 해내는 스타일", true),
                  new ResultTextSegment("입니다.\n", false),
                  new ResultTextSegment("2. 말보다 ", false),
                  new ResultTextSegment("행동과 실천이 강합니다", true),
                  new ResultTextSegment(".\n", false),
                  new ResultTextSegment("3. 주변에 ", false),
                  new ResultTextSegment("안정감", true),
                  new ResultTextSegment("을 주고 ", false),
                  new ResultTextSegment("신뢰", true),
                  new ResultTextSegment("를 얻는 타입이며, 조용하지만 ", false),
                  new ResultTextSegment("속이 단단하고 흔들림이 없습니다", true),
                  new ResultTextSegment(".", false))));
}
