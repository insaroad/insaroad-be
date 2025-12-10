/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.global.exception.model;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

  String getCode();

  String getMessage();

  HttpStatus getStatus();
}
