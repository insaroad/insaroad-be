/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.animalmission.exception;

import org.springframework.http.HttpStatus;

import com.pbl.insaroad.global.exception.model.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnimalMissionErrorCode implements BaseErrorCode {
  DUPLICATE_PATTERN_ANIMALS("ANIMAL_4001", "patternAnimals에 중복된 동물이 있습니다.", HttpStatus.BAD_REQUEST),
  USER_NOT_FOUND("ANIMAL_4002", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  USER_NOT_STAGE_2("ANIMAL_4003", "사용자의 스테이지가 2가 아닙니다.", HttpStatus.BAD_REQUEST),
  ;

  private final String code;
  private final String message;
  private final HttpStatus status;
}
