/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.user.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pbl.insaroad.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("select u.code from User u")
  Set<String> findAllCodes();

  Optional<User> findByCode(String code);
}
