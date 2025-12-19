/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.location.exception;

import org.springframework.http.HttpStatus;

import com.pbl.insaroad.global.exception.model.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LocationErrorCode implements BaseErrorCode {
  LOCATION_BAD_REQUEST("LOC001", "요청 값이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
  LOCATION_NOT_FOUND("LOC002", "위치 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  LOCATION_NUMBER_DUPLICATED("LOC003", "이미 존재하는 위치 번호입니다.", HttpStatus.CONFLICT);

  private final String code;
  private final String message;
  private final HttpStatus status;
}
