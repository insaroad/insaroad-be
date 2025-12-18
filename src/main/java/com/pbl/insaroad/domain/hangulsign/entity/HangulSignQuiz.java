/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.hangulsign.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.pbl.insaroad.global.common.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hangul_sign_quizzes")
public class HangulSignQuiz extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "question_image_url", nullable = false, length = 255)
  private String questionImageUrl;

  @Column(name = "answer_image_url", nullable = false, length = 255)
  private String answerImageUrl;

  @Column(name = "choice1", nullable = false, length = 20)
  private String choice1;

  @Column(name = "choice2", nullable = false, length = 20)
  private String choice2;

  @Column(name = "choice3", nullable = false, length = 20)
  private String choice3;

  @Column(name = "correct_choice", nullable = false, columnDefinition = "TINYINT")
  private Integer correctChoice;
}
