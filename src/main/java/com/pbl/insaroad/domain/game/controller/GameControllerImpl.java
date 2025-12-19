/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.game.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pbl.insaroad.domain.game.dto.request.GameRequest.CompleteRequest;
import com.pbl.insaroad.domain.game.dto.request.GameRequest.UnvisitedRequest;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.GameProgressResponse;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.StartResponse;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.UnvisitedResponse;
import com.pbl.insaroad.domain.user.service.UserService;
import com.pbl.insaroad.global.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GameControllerImpl implements GameController {

  private final UserService userService;

  @Override
  public BaseResponse<StartResponse> startNew() {
    return BaseResponse.success(userService.startNewGame());
  }

  @Override
  public BaseResponse<StartResponse> resume(String userCode) {
    return BaseResponse.success(userService.startResumeGame(userCode));
  }

  @Override
  public ResponseEntity<BaseResponse<UnvisitedResponse>> unvisited(
      @RequestBody @Valid UnvisitedRequest request) {
    return ResponseEntity.ok(
        BaseResponse.success(userService.getUnvisitedLocations(request.getUserCode())));
  }

  @Override
  public ResponseEntity<BaseResponse<GameProgressResponse>> complete(
      @RequestBody @Valid CompleteRequest request) {
    return ResponseEntity.ok(BaseResponse.success(userService.completeGame(request)));
  }
}
