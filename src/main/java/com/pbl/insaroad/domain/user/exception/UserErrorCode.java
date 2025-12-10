/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.user.exception;

import org.springframework.http.HttpStatus;

import com.pbl.insaroad.global.exception.model.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
  EXAMPLE("USER_4000", "예시 에러코드입니다.", HttpStatus.BAD_REQUEST),

  NO_AVAILABLE_CODE("USER_4001", "사용 가능한 3자리 코드가 없습니다.", HttpStatus.BAD_REQUEST),
  ;

  private final String code;
  private final String message;
  private final HttpStatus status;
}
