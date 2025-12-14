package com.pbl.insaroad.domain.animalmission.data;

import java.util.Arrays;

import com.pbl.insaroad.domain.animalmission.dto.response.AnimalResultResponse;
import com.pbl.insaroad.domain.animalmission.dto.response.DescriptionResponse;
import com.pbl.insaroad.domain.animalmission.dto.response.ResultTextSegment;
import com.pbl.insaroad.domain.animalmission.entity.AnimalType;

public class Magpie {
  public static final AnimalResultResponse CONTENT =
      AnimalResultResponse.from(
          AnimalType.MAGPIE,
          "[희보(喜報)와 화합(和合)의 상징]",
          "[단원 김홍도_작도]",
          new DescriptionResponse(
              Arrays.asList(
                  new ResultTextSegment("당신은 좋은 소식을 가져오는 \"길조(吉兆)\"와 \"복(福)\"의 상징인 ", false),
                  new ResultTextSegment("까치(鵲)의 기질", true),
                  new ResultTextSegment("을 지녔습니다.\n\n", false),
                  new ResultTextSegment("민속 신앙에서 까치는 ", false),
                  new ResultTextSegment("복·소식·손님·축하", true),
                  new ResultTextSegment("를 알리는 새로 여겨지며, 꽃과 새를 같이 그린 \"화조도\"에서도 까치는 ", false),
                  new ResultTextSegment("밝음·조화·사회적 관계", true),
                  new ResultTextSegment("를 의미합니다. 까치는 무리를 짓는 성향이 있어, ", false),
                  new ResultTextSegment("관계와 소통의 상징", true),
                  new ResultTextSegment("으로 해석되기도 합니다!", false)),
              Arrays.asList(
                  new ResultTextSegment("[당신의 성향은...]\n", true),
                  new ResultTextSegment("1. 주변을 ", false),
                  new ResultTextSegment("따뜻하게", true),
                  new ResultTextSegment(" 만들고 ", false),
                  new ResultTextSegment("분위기를 밝히는 능력", true),
                  new ResultTextSegment("이 있습니다.\n", false),
                  new ResultTextSegment("2. 새로운 만남과 대화를 즐기는 ", false),
                  new ResultTextSegment("사교적인 스타일의 사람", true),
                  new ResultTextSegment("입니다.\n", false),
                  new ResultTextSegment("3. 남을 ", false),
                  new ResultTextSegment("배려", true),
                  new ResultTextSegment("하고 관계를 순조롭게 이어가는 능력을 지니고 있습니다.\n", false),
                  new ResultTextSegment("4. 자신도 모르게 ", false),
                  new ResultTextSegment("주변 사람들을 자연스럽게 하나로 모으는 잠재력", true),
                  new ResultTextSegment("을 갖고 있습니다.", false))));
}
