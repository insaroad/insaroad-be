/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.animalmission.service;

import com.pbl.insaroad.domain.game.dto.request.GameRequest.CompleteRequest;
import com.pbl.insaroad.domain.user.entity.User;
import com.pbl.insaroad.domain.user.service.UserService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pbl.insaroad.domain.animalmission.data.Crane;
import com.pbl.insaroad.domain.animalmission.data.Haetae;
import com.pbl.insaroad.domain.animalmission.data.Magpie;
import com.pbl.insaroad.domain.animalmission.data.Tiger;
import com.pbl.insaroad.domain.animalmission.data.Turtle;
import com.pbl.insaroad.domain.animalmission.dto.request.AnimalMissionSubmitRequest;
import com.pbl.insaroad.domain.animalmission.dto.response.AnimalResultResponse;
import com.pbl.insaroad.domain.animalmission.entity.AnimalType;
import com.pbl.insaroad.domain.animalmission.exception.AnimalMissionErrorCode;
import com.pbl.insaroad.domain.user.repository.UserRepository;
import com.pbl.insaroad.global.exception.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimalMissionService {

  // 동물 타입별 결과 응답을 저장하는 Map
  private static final Map<AnimalType, AnimalResultResponse> ANIMAL_RESULTS = new HashMap<>();

  private final UserRepository userRepository;
  private final UserService userService;

  static {
    ANIMAL_RESULTS.put(AnimalType.TIGER, Tiger.CONTENT);
    ANIMAL_RESULTS.put(AnimalType.MAGPIE, Magpie.CONTENT);
    ANIMAL_RESULTS.put(AnimalType.HAETAE, Haetae.CONTENT);
    ANIMAL_RESULTS.put(AnimalType.CRANE, Crane.CONTENT);
    ANIMAL_RESULTS.put(AnimalType.TURTLE, Turtle.CONTENT);
  }

  // 동물 미션 제출 처리 메서드
  @Transactional
  public AnimalResultResponse submitAnimalMission(AnimalMissionSubmitRequest missionRequest,  CompleteRequest completeRequest) {
    // 0. patternAnimals 중복 검증
    validateNoDuplicatePatternAnimals(missionRequest.getPatternAnimals());

    // 사용자 조회
    User user =
        userRepository
            .findByCode(completeRequest.getUserCode())
            .orElseThrow(() -> new CustomException(AnimalMissionErrorCode.USER_NOT_FOUND));

    // 사용자 스테이지 검증
    if (user.getStage() != 2) {
      throw new CustomException(AnimalMissionErrorCode.USER_NOT_STAGE_2);
    }

    // 1. 동물별 점수 집계
    Map<AnimalType, Integer> scoreMap = calculateScores(missionRequest);

    // 2. 최고 점수 계산
    int maxScore = scoreMap.values().stream().mapToInt(Integer::intValue).max().orElse(0);

    // 3. 최고 점수를 가진 동물들 찾기
    long countMaxScore = scoreMap.values().stream().filter(score -> score == maxScore).count();

    // 4. 동점 처리: 2개 이상이면 paintingAnimal 반환
    AnimalType resultAnimal;
    if (countMaxScore >= 2) {
      resultAnimal = missionRequest.getPaintingAnimal();
    } else {
      // 단일 최고 점수인 경우 해당 동물 반환
      resultAnimal =
          scoreMap.entrySet().stream()
              .filter(entry -> entry.getValue() == maxScore)
              .map(Map.Entry::getKey)
              .findFirst()
              .orElse(missionRequest.getPaintingAnimal());
    }

    userService.completeGame(completeRequest);

    return ANIMAL_RESULTS.get(resultAnimal);
  }

  // patternAnimals에 중복된 동물이 없는지 검증하는 메서드
  private void validateNoDuplicatePatternAnimals(List<AnimalType> patternAnimals) {
    Set<AnimalType> uniqueAnimals = new HashSet<>(patternAnimals);
    if (uniqueAnimals.size() != patternAnimals.size()) {
      throw new CustomException(AnimalMissionErrorCode.DUPLICATE_PATTERN_ANIMALS);
    }
  }

  // 동물별 점수를 계산하는 메서드
  private Map<AnimalType, Integer> calculateScores(AnimalMissionSubmitRequest request) {
    Map<AnimalType, Integer> scoreMap = new HashMap<>();

    // 초기화: 모든 동물 타입을 0점으로 설정
    for (AnimalType animalType : AnimalType.values()) {
      scoreMap.put(animalType, 0);
    }

    // 문양 선택 점수 (2개)
    for (AnimalType patternAnimal : request.getPatternAnimals()) {
      scoreMap.put(patternAnimal, scoreMap.get(patternAnimal) + 1);
    }

    // 입구 행동 선택 점수 (1개)
    AnimalType entranceAnimal = request.getEntranceAnimal();
    scoreMap.put(entranceAnimal, scoreMap.get(entranceAnimal) + 1);

    // 민화 그림 선택 점수 (1개)
    AnimalType paintingAnimal = request.getPaintingAnimal();
    scoreMap.put(paintingAnimal, scoreMap.get(paintingAnimal) + 1);

    return scoreMap;
  }
}
