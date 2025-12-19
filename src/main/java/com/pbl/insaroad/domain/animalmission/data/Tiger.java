/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.animalmission.data;

import java.util.Arrays;

import com.pbl.insaroad.domain.animalmission.dto.response.AnimalResultResponse;
import com.pbl.insaroad.domain.animalmission.dto.response.DescriptionResponse;
import com.pbl.insaroad.domain.animalmission.dto.response.ResultTextSegment;
import com.pbl.insaroad.domain.animalmission.entity.AnimalType;

public class Tiger {
  public static final AnimalResultResponse CONTENT =
      AnimalResultResponse.from(
          AnimalType.TIGER,
          "[기세(氣勢)와 용맹(勇猛)의 상징]",
          "[작호도(鵲虎圖), 민화풍(출처 미상)]",
          new DescriptionResponse(
              Arrays.asList(
                  new ResultTextSegment("당신은 악귀를 쫓고 가정을 지키는 수호자이자, 산의 왕, 기세와 용맹의 상징인 ", false),
                  new ResultTextSegment("범(虎)의 기질", true),
                  new ResultTextSegment("을 지녔습니다.\n\n", false),
                  new ResultTextSegment("「삼국유사」와 여러 민담에서 호랑이는 ", false),
                  new ResultTextSegment("용맹·결단·기운의 상징", true),
                  new ResultTextSegment(
                      "으로 등장합니다. 민화 「까치호랑이」에서처럼 익살스러운 모습을 보이곤 하지만, 본질적으로는 ", false),
                  new ResultTextSegment("강한 에너지와 추진력을 지닌 존재입니다", true),
                  new ResultTextSegment(".", false)),
              Arrays.asList(
                  new ResultTextSegment("[당신의 성향은...]\n", true),
                  new ResultTextSegment("1. 목표가 생기면 바로 ", false),
                  new ResultTextSegment("행동하는 추진력", true),
                  new ResultTextSegment("을 갖고 있습니다.\n", false),
                  new ResultTextSegment("2. 앞길이 막히더라도, 고난을 뚫고 가려는 ", false),
                  new ResultTextSegment("기백", true),
                  new ResultTextSegment("을 지녔습니다.\n", false),
                  new ResultTextSegment("3. 사람들에게 강한 인상과 에너지를 주곤 합니다.\n", false),
                  new ResultTextSegment("4. 한번 \"해야겠다\"는 결정을 하면, ", false),
                  new ResultTextSegment("결코 흔들리지 않습니다", true),
                  new ResultTextSegment(".", false))));
}
