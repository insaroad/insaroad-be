package com.pbl.insaroad.domain.animalmission.data;

import java.util.Arrays;

import com.pbl.insaroad.domain.animalmission.dto.response.AnimalResultResponse;
import com.pbl.insaroad.domain.animalmission.dto.response.DescriptionResponse;
import com.pbl.insaroad.domain.animalmission.dto.response.ResultTextSegment;
import com.pbl.insaroad.domain.animalmission.entity.AnimalType;

public class Crane {
  public static final AnimalResultResponse CONTENT =
      AnimalResultResponse.from(
          AnimalType.CRANE,
          "[고요함(寂寞)과 고결함(高潔)의 상징]",
          "[전조지운필송학도(傳趙之耘筆松鶴圖)]",
          new DescriptionResponse(
              Arrays.asList(
                  new ResultTextSegment("당신은 고결함·평온·격조의 미덕을 갖춘 신선의 상징, ", false),
                  new ResultTextSegment("학(鶴)의 기질", true),
                  new ResultTextSegment("을 지녔습니다.\n\n", false),
                  new ResultTextSegment("학은 동아시아에서 신비롭고 영적인 존재로, ", false),
                  new ResultTextSegment("신선이 타는 새", true),
                  new ResultTextSegment("로 알려져있습니다.\n학은 ", false),
                  new ResultTextSegment("깨끗하고 고귀한 기상을 상징", true),
                  new ResultTextSegment("하여, 선비의 덕목(학식과 인품)을 나타내며, 조선시대 문관 흉배(운학흉배)에 사용되어 ", false),
                  new ResultTextSegment("청백함을 상징", true),
                  new ResultTextSegment("했습니다.\n한국 민담 및 문학에서 ", false),
                  new ResultTextSegment("잡음을 멀리하고 깨끗한 기운을 지닌 존재", true),
                  new ResultTextSegment("로 그려집니다.", false)),
              Arrays.asList(
                  new ResultTextSegment("[당신의 성향은...]\n", true),
                  new ResultTextSegment("1. 감정의 요동보다는 ", false),
                  new ResultTextSegment("깊고 조용한 내면을 지향합니다", true),
                  new ResultTextSegment(".\n", false),
                  new ResultTextSegment("2. ", false),
                  new ResultTextSegment("섬세한 관찰력", true),
                  new ResultTextSegment("과 ", false),
                  new ResultTextSegment("맑은 감수성", true),
                  new ResultTextSegment("을 지녔으며, 호자만의 시간, 조용한 공간에서 몰입하는 능력을 지녔습니다.\n", false),
                  new ResultTextSegment("3. 관계에서는 과하지 않고, ", false),
                  new ResultTextSegment("품격 있는 태도로 신뢰를 주는 타입입니다", true),
                  new ResultTextSegment(".\n", false),
                  new ResultTextSegment("4. 예술·문학·고요한 자연을 좋아하는 경향이 있습니다.", false))));
}
