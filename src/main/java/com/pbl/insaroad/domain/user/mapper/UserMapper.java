/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.user.mapper;

import org.springframework.stereotype.Component;

import com.pbl.insaroad.domain.user.entity.User;

@Component
public class UserMapper {

  /** 랜덤 코드만 받아 User 엔티티 생성 (stage = 1, completed = false, rewardReceived = false 기본값 자동 적용) */
  public User toUser(String code) {
    return User.builder().code(code).build();
  }
}
