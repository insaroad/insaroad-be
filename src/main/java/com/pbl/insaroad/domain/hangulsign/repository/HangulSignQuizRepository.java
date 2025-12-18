/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.hangulsign.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pbl.insaroad.domain.hangulsign.entity.HangulSignQuiz;

public interface HangulSignQuizRepository extends JpaRepository<HangulSignQuiz, Long> {

  @Query(value = "SELECT * FROM hangul_sign_quizzes ORDER BY RAND() LIMIT 1", nativeQuery = true)
  Optional<HangulSignQuiz> findRandomQuiz();
}
