/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.hangulsign.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pbl.insaroad.domain.game.dto.request.GameRequest.CompleteRequest;
import com.pbl.insaroad.domain.hangulsign.dto.request.AnswerRequest;
import com.pbl.insaroad.domain.hangulsign.dto.request.QuizCreateRequest;
import com.pbl.insaroad.domain.hangulsign.dto.response.AnswerResponse;
import com.pbl.insaroad.domain.hangulsign.dto.response.QuizCreateResponse;
import com.pbl.insaroad.domain.hangulsign.dto.response.QuizResponse;
import com.pbl.insaroad.domain.hangulsign.entity.HangulSignQuiz;
import com.pbl.insaroad.domain.hangulsign.exception.HangulSignQuizErrorCode;
import com.pbl.insaroad.domain.hangulsign.mapper.HangulSignQuizMapper;
import com.pbl.insaroad.domain.hangulsign.repository.HangulSignQuizRepository;
import com.pbl.insaroad.domain.user.entity.User;
import com.pbl.insaroad.domain.user.repository.UserRepository;
import com.pbl.insaroad.domain.user.service.UserService;
import com.pbl.insaroad.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HangulSignQuizService {

  private final HangulSignQuizRepository quizRepository;
  private final HangulSignQuizMapper quizMapper;
  private final UserRepository userRepository;
  private final UserService userService;

  @Transactional(readOnly = true)
  public QuizResponse getRandomQuiz() {
    HangulSignQuiz quiz =
        quizRepository
            .findRandomQuiz()
            .orElseThrow(() -> new CustomException(HangulSignQuizErrorCode.QUIZ_NOT_FOUND));

    return quizMapper.toQuizResponse(quiz);
  }

  public AnswerResponse submitAnswer(
      Long quizId, AnswerRequest request, CompleteRequest completeRequest) {
    // 사용자 조회
    User user =
        userRepository
            .findByCode(completeRequest.getUserCode())
            .orElseThrow(() -> new CustomException(HangulSignQuizErrorCode.USER_NOT_FOUND));

    // 사용자 스테이지 검증
    if (user.getStage() != 3) {
      throw new CustomException(HangulSignQuizErrorCode.USER_NOT_STAGE_3);
    }

    // 퀴즈 조회
    HangulSignQuiz quiz =
        quizRepository
            .findById(quizId)
            .orElseThrow(() -> new CustomException(HangulSignQuizErrorCode.QUIZ_NOT_FOUND));

    // 선택지 유효성 검증 (명시적으로 한 번 더 검증)
    if (request.getUserAnswer() < 1 || request.getUserAnswer() > 3) {
      throw new CustomException(HangulSignQuizErrorCode.INVALID_CHOICE);
    }

    boolean isCorrect = quiz.getCorrectChoice().equals(request.getUserAnswer());

    String answerImageUrl = isCorrect ? quiz.getAnswerImageUrl() : null;

    userService.completeGame(completeRequest);

    return quizMapper.toAnswerResponse(isCorrect, answerImageUrl);
  }

  public QuizCreateResponse createQuiz(QuizCreateRequest request) {
    HangulSignQuiz quiz = quizMapper.toEntity(request);
    HangulSignQuiz savedQuiz = quizRepository.save(quiz);

    log.info("한글 간판 퀴즈가 생성되었습니다. ID: {}", savedQuiz.getId());

    return quizMapper.toQuizCreateResponse(savedQuiz);
  }

  @Transactional(readOnly = true)
  public List<QuizCreateResponse> getAllQuizzes() {
    List<HangulSignQuiz> quizzes = quizRepository.findAll();

    return quizzes.stream().map(quizMapper::toQuizCreateResponse).toList();
  }
}
