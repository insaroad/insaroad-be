/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.koreanname.exception;

import org.springframework.http.HttpStatus;

import com.pbl.insaroad.global.exception.model.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KoreanNameErrorCode implements BaseErrorCode {
  OPENAI_REQUEST_FAILED("KOREAN_NAME_001", "이름 추천 생성 중 오류가 발생했습니다.", HttpStatus.BAD_GATEWAY),
  INVALID_AI_RESPONSE(
      "KOREAN_NAME_002", "AI로부터 받은 이름 추천 응답이 올바르지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

  private final String code;
  private final String message;
  private final HttpStatus status;
}
