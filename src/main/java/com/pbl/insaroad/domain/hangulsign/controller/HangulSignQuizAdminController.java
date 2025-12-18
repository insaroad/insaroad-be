/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.hangulsign.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbl.insaroad.domain.hangulsign.dto.request.QuizCreateRequest;
import com.pbl.insaroad.domain.hangulsign.dto.response.QuizCreateResponse;
import com.pbl.insaroad.domain.hangulsign.service.HangulSignQuizService;
import com.pbl.insaroad.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HangulSignQuizAdmin", description = "한글 간판 퀴즈 관리자 API")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class HangulSignQuizAdminController {

  private final HangulSignQuizService quizService;

  @Operation(summary = "퀴즈 생성", description = "관리자가 새로운 한글 간판 퀴즈를 생성합니다.")
  @PostMapping("/quizzes")
  public ResponseEntity<BaseResponse<QuizCreateResponse>> createQuiz(
      @Valid @RequestBody QuizCreateRequest request) {
    QuizCreateResponse response = quizService.createQuiz(request);
    return ResponseEntity.ok(BaseResponse.success("퀴즈 생성 성공", response));
  }

  @Operation(summary = "전체 퀴즈 조회", description = "관리자가 등록된 모든 퀴즈를 조회합니다.")
  @GetMapping("/quizzes")
  public ResponseEntity<BaseResponse<List<QuizCreateResponse>>> getAllQuizzes() {
    List<QuizCreateResponse> response = quizService.getAllQuizzes();
    return ResponseEntity.ok(BaseResponse.success("전체 퀴즈 조회 성공", response));
  }
}
