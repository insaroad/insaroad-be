package com.pbl.insaroad.domain.animalmission.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AnimalType {
  CRANE("Crane", "학"),
  HAETAE("Haetae", "해태"),
  TURTLE("Turtle", "거북이"),
  TIGER("Tiger", "호랑이"),
  MAGPIE("Magpie", "까치");

  private final String englishName;
  private final String koreanName;
}
