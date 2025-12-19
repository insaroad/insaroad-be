/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.location.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pbl.insaroad.domain.game.dto.request.GameRequest;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.UnvisitedResponse;
import com.pbl.insaroad.domain.location.dto.request.LocationRequest.CreateLocationRequest;
import com.pbl.insaroad.domain.location.dto.response.LocationResponse;
import com.pbl.insaroad.domain.location.service.LocationService;
import com.pbl.insaroad.domain.user.service.UserService;
import com.pbl.insaroad.global.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LocationControllerImpl implements LocationController {

  private final LocationService locationService;
  private final UserService userService;

  @Override
  public ResponseEntity<BaseResponse<LocationResponse>> create(
      @RequestBody @Valid CreateLocationRequest request) {
    return ResponseEntity.ok(BaseResponse.success(locationService.create(request)));
  }

  @Override
  public ResponseEntity<BaseResponse<UnvisitedResponse>> unvisited(
      @RequestBody @Valid GameRequest.UnvisitedRequest request) {
    return ResponseEntity.ok(
        BaseResponse.success(userService.getUnvisitedLocations(request.getUserCode())));
  }

  @Override
  public ResponseEntity<BaseResponse<Void>> deleteById(@PathVariable("id") Long locationId) {
    locationService.deleteById(locationId);
    return ResponseEntity.ok(BaseResponse.success("Location이 성공적으로 삭제되었습니다.", null));
  }
}
