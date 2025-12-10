/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

  /** 랜덤 고유 3자리 번호 (예: 123) 중복 방지를 원하면 unique = true 설정 */
  @Column(name = "code", length = 3, nullable = false, unique = true)
  private String code;

  /** 현재 미션 진행 스테이지 기본값 = 1 */
  @Builder.Default
  @Column(name = "stage", nullable = false)
  private int stage = 1;

  /** 완주 여부 (기본값 = false) */
  @Builder.Default
  @Column(name = "is_completed", nullable = false)
  private boolean completed = false;

  /** 상품 수령 여부 (기본값 = false) */
  @Builder.Default
  @Column(name = "is_reward_received", nullable = false)
  private boolean rewardReceived = false;

  /* ====== 동작 메서드 ====== */

  public void nextStage() {
    this.stage += 1;
  }

  public void completeMission() {
    this.completed = true;
  }

  public void receiveReward() {
    this.rewardReceived = true;
  }
}
