package com.pbl.insaroad.domain.animalmission.exception;

import org.springframework.http.HttpStatus;
import com.pbl.insaroad.global.exception.model.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnimalMissionErrorCode implements BaseErrorCode {
  DUPLICATE_PATTERN_ANIMALS("ANIMAL_4001", "patternAnimals에 중복된 동물이 있습니다.", HttpStatus.BAD_REQUEST),
  ;

  private final String code;
  private final String message;
  private final HttpStatus status;
}
