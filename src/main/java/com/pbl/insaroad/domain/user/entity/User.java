/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.user.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** 랜덤 고유 3자리 번호 (예: 123) */
  @Column(name = "code", length = 3, nullable = false, unique = true)
  private String code;

  /** 방문한 Location ID 리스트 - 연관관계 없이 단순 ID(Long) 목록 저장 - 방문 순서 보존 */
  @Builder.Default
  @ElementCollection
  @CollectionTable(name = "user_visited_location_ids", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "location_id", nullable = false)
  private List<Long> visitedLocationIds = new ArrayList<>();

  /** 완주 여부 */
  @Builder.Default
  @Column(name = "is_completed", nullable = false)
  private boolean completed = false;

  /** 상품 수령 여부 */
  @Builder.Default
  @Column(name = "is_reward_received", nullable = false)
  private boolean rewardReceived = false;

  /* ====== Domain Logic ====== */

  public void completeMission() {
    this.completed = true;
  }

  public void receiveReward() {
    this.rewardReceived = true;
  }

  /** 방문 LocationId 추가 - 이미 방문한 곳이면 무시(중복 방지) */
  public void addVisitedLocationId(Long locationId) {
    if (locationId == null) {
      return;
    }

    // 방문 순서를 유지하면서 "중복 방문"은 막는 정책
    if (!visitedLocationIds.contains(locationId)) {
      visitedLocationIds.add(locationId);
    }
  }
}
