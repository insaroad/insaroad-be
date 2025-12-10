/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.user.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pbl.insaroad.domain.user.entity.User;
import com.pbl.insaroad.domain.user.exception.UserErrorCode;
import com.pbl.insaroad.domain.user.mapper.UserMapper;
import com.pbl.insaroad.domain.user.repository.UserRepository;
import com.pbl.insaroad.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public User createUser() {
    String code = generateUniqueCode();

    User user = userMapper.toUser(code);

    try {
      return userRepository.save(user);
    } catch (DataIntegrityViolationException e) {
      throw new CustomException(UserErrorCode.NO_AVAILABLE_CODE);
    }
  }

  /** 3자리 랜덤 코드 중 미사용 코드 1개 생성 */
  public String generateUniqueCode() {
    Set<String> usedCodes = userRepository.findAllCodes();

    if (usedCodes.size() >= 1000) {
      throw new CustomException(UserErrorCode.NO_AVAILABLE_CODE);
    }

    List<String> all = new ArrayList<>(1000);
    for (int i = 0; i < 1000; i++) {
      all.add(String.format("%03d", i));
    }

    all.removeAll(usedCodes);
    Collections.shuffle(all);
    return all.getFirst();
  }
}
