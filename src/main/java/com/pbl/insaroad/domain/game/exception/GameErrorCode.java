/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.game.exception;

import org.springframework.http.HttpStatus;

import com.pbl.insaroad.global.exception.model.BaseErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameErrorCode implements BaseErrorCode {
  USER_CODE_REQUIRED("GAME001", "사용자 번호가 필요합니다.", HttpStatus.BAD_REQUEST),
  USER_NOT_FOUND("GAME002", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

  LOCATION_NOT_FOUND("GAME010", "위치 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

  NOT_ALL_LOCATIONS_VISITED("GAME020", "아직 방문하지 않은 위치가 남아있습니다.", HttpStatus.CONFLICT);

  private final String code;
  private final String message;
  private final HttpStatus status;
}
