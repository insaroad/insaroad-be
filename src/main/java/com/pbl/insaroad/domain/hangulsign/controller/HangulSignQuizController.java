/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.hangulsign.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbl.insaroad.domain.hangulsign.dto.request.AnswerRequest;
import com.pbl.insaroad.domain.hangulsign.dto.response.AnswerResponse;
import com.pbl.insaroad.domain.hangulsign.dto.response.QuizResponse;
import com.pbl.insaroad.domain.hangulsign.service.HangulSignQuizService;
import com.pbl.insaroad.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HangulSignQuiz", description = "한글 간판 퀴즈 API")
@RestController
@RequestMapping("/api/hangul-sign/quizzes")
@RequiredArgsConstructor
public class HangulSignQuizController {

  private final HangulSignQuizService quizService;

  @Operation(summary = "랜덤 퀴즈 조회", description = "한글 간판 퀴즈를 랜덤으로 조회합니다.")
  @GetMapping("/random")
  public ResponseEntity<BaseResponse<QuizResponse>> getRandomQuiz() {
    QuizResponse response = quizService.getRandomQuiz();
    return ResponseEntity.ok(BaseResponse.success("랜덤 퀴즈 조회 성공", response));
  }

  @Operation(summary = "퀴즈 정답 제출", description = "사용자가 선택한 답을 제출하고 정답 여부를 확인합니다.")
  @PostMapping("/{quizId}/answer")
  public ResponseEntity<BaseResponse<AnswerResponse>> submitAnswer(
      @PathVariable Long quizId, @Valid @RequestBody AnswerRequest answerRequest) {
    AnswerResponse response = quizService.submitAnswer(quizId, answerRequest);
    return ResponseEntity.ok(BaseResponse.success("정답 제출 완료", response));
  }
}
