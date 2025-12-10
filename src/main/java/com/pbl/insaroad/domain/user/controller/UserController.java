/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbl.insaroad.domain.user.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Pay", description = "사용자 관련 API")
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
}
