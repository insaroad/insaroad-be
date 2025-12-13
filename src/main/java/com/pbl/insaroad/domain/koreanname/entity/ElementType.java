/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.koreanname.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ElementType {
  WATER(
      "물",
      "https://insaroad-bucket.s3.ap-northeast-2.amazonaws.com/element/27213481-43b1-4f15-adc3-9be0dfc23d9c"),
  FIRE(
      "불",
      "https://insaroad-bucket.s3.ap-northeast-2.amazonaws.com/element/91a2b2b4-68db-490f-940a-a86e1cf510fe"),
  METAL(
      "쇠",
      "https://insaroad-bucket.s3.ap-northeast-2.amazonaws.com/element/1d9709eb-9af8-443a-991a-168faea28d7a"),
  WOOD(
      "나무",
      "https://insaroad-bucket.s3.ap-northeast-2.amazonaws.com/element/c68a8cfc-14bd-4652-8713-cb72e756d196"),
  EARTH(
      "흙",
      "https://insaroad-bucket.s3.ap-northeast-2.amazonaws.com/element/f026677c-9218-499a-93ae-fb7d16ec9ba3");

  private final String koreanName;
  private final String imageFileName; // 나중에 URL로 교체해서 사용
}
