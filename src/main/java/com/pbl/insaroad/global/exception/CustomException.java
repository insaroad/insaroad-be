/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.global.exception;

import com.pbl.insaroad.global.exception.model.BaseErrorCode;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private final BaseErrorCode errorCode;

  public CustomException(BaseErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
