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

import com.pbl.insaroad.domain.game.dto.request.GameRequest.CompleteRequest;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.CompleteResponse;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.FinishResponse;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.GameProgressResponse;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.UnvisitedResponse;
import com.pbl.insaroad.domain.game.exception.GameErrorCode;
import com.pbl.insaroad.domain.location.dto.LocationResponse;
import com.pbl.insaroad.domain.location.entity.Location;
import com.pbl.insaroad.domain.location.repository.LocationRepository;
import com.pbl.insaroad.domain.ticket.entity.Ticket;
import com.pbl.insaroad.domain.ticket.service.TicketService;
import com.pbl.insaroad.domain.ticket.util.TicketQrPayloadFactory;
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

  private final LocationRepository locationRepository;

  private final TicketService ticketService;
  private final TicketQrPayloadFactory qrPayloadFactory;

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

  public UnvisitedResponse getUnvisitedLocations(String userCode) {
    User user = getUserByCodeOrThrow(userCode);

    List<Location> unvisited = findUnvisitedLocations(user);

    return UnvisitedResponse.builder()
        .unvisitedLocations(unvisited.stream().map(LocationResponse::from).toList())
        .build();
  }

  /* =========================
   * 게임 완료 처리
   * - 첫 시작이면 사용자 생성
   * - 현재 locationId 방문 처리
   * - 응답: userCode + 미방문 Location 전체
   * - 이번 요청으로 전체 미션 완료됨 → finish 로직 수행
   * ========================= */
  @Transactional
  public GameProgressResponse completeGame(CompleteRequest request) {

    // 현재 위치 존재 확인
    Location current =
        locationRepository
            .findById(request.getCurrentLocationId())
            .orElseThrow(() -> new CustomException(GameErrorCode.LOCATION_NOT_FOUND));

    // 첫 시작이면 생성
    User user =
        (request.getUserCode() == null || request.getUserCode().isBlank())
            ? createUser()
            : getUserByCodeOrThrow(request.getUserCode());

    // 방문 처리 (중복이면 무시)
    user.addVisitedLocationId(current.getId());

    // 남은 곳 전체 조회
    List<Location> unvisited = locationRepository.findAllUnvisitedByUserId(user.getId());

    // 미션 완료가 아니라면: CompleteResponse 반환
    if (!unvisited.isEmpty()) {
      CompleteResponse complete =
          CompleteResponse.builder()
              .userCode(user.getCode())
              .unvisitedLocations(unvisited.stream().map(LocationResponse::from).toList())
              .build();

      return GameProgressResponse.builder()
          .completed(false)
          .complete(complete)
          .finish(null)
          .build();
    }

    // 이번 요청으로 전체 미션 완료됨 → finish 로직 수행
    if (!user.isCompleted()) {
      user.completeMission();
    }

    Ticket ticket = ticketService.issueNewTicket(user.getId());

    FinishResponse finish =
        FinishResponse.builder()
            .exchangeUrl(qrPayloadFactory.create(ticket.getToken()))
            .issuedAt(ticket.getCreatedAt())
            .build();

    return GameProgressResponse.builder().completed(true).complete(null).finish(finish).build();
  }

  /* =========================
   * Private Helpers
   * ========================= */

  private User getUserByCodeOrThrow(String userCode) {
    if (userCode == null || userCode.isBlank()) {
      throw new CustomException(GameErrorCode.USER_CODE_REQUIRED);
    }
    return userRepository
        .findByCode(userCode)
        .orElseThrow(() -> new CustomException(GameErrorCode.USER_NOT_FOUND));
  }

  private List<Location> findUnvisitedLocations(User user) {
    List<Long> visitedIds = user.getVisitedLocationIds();

    if (visitedIds == null || visitedIds.isEmpty()) {
      return locationRepository.findAll();
    }
    return locationRepository.findAllUnvisitedByUserId(user.getId());
  }
}
