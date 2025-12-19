/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.hangulsign.exception;

import org.springframework.http.HttpStatus;

import com.pbl.insaroad.global.exception.model.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HangulSignQuizErrorCode implements BaseErrorCode {
  QUIZ_NOT_FOUND("QUIZ_4041", "퀴즈를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  USER_NOT_FOUND("QUIZ_4042", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  INVALID_CHOICE("QUIZ_4001", "유효하지 않은 선택지입니다. (1, 2, 3 중 선택)", HttpStatus.BAD_REQUEST),
  USER_NOT_STAGE_3("QUIZ_4003", "사용자의 스테이지가 3이 아닙니다.", HttpStatus.BAD_REQUEST);

  private final String code;
  private final String message;
  private final HttpStatus status;
}
