/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.hangulsign.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.pbl.insaroad.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HangulSignQuizService {

  private final HangulSignQuizRepository quizRepository;
  private final UserRepository userRepository;
  private final HangulSignQuizMapper quizMapper;

  @Transactional(readOnly = true)
  public QuizResponse getRandomQuiz() {
    HangulSignQuiz quiz =
        quizRepository
            .findRandomQuiz()
            .orElseThrow(() -> new CustomException(HangulSignQuizErrorCode.QUIZ_NOT_FOUND));

    return quizMapper.toQuizResponse(quiz);
  }

  public AnswerResponse submitAnswer(Long quizId, AnswerRequest request) {
    // 퀴즈 조회
    HangulSignQuiz quiz =
        quizRepository
            .findById(quizId)
            .orElseThrow(() -> new CustomException(HangulSignQuizErrorCode.QUIZ_NOT_FOUND));

    // 사용자 조회
    User user =
        userRepository
            .findByCode(request.getUserCode())
            .orElseThrow(() -> new CustomException(HangulSignQuizErrorCode.USER_NOT_FOUND));

    // 선택지 유효성 검증 (명시적으로 한 번 더 검증)
    if (request.getUserAnswer() < 1 || request.getUserAnswer() > 3) {
      throw new CustomException(HangulSignQuizErrorCode.INVALID_CHOICE);
    }

    // 정답 체크
    boolean isCorrect = quiz.getCorrectChoice().equals(request.getUserAnswer());

    String answerImageUrl = null;

    // 정답인 경우에만 스테이지 업데이트 및 이미지 URL 반환
    if (isCorrect) {
      answerImageUrl = quiz.getAnswerImageUrl();

      // user.getStage() == 2일 때만 nextStage() 호출
      if (user.getStage() == 2) {
        user.nextStage();
        userRepository.save(user);
        log.info("[서비스] 사용자 {}의 스테이지가 {}로 업데이트되었습니다.", user.getId(), user.getStage());
      }
    }

    return quizMapper.toAnswerResponse(isCorrect, answerImageUrl, user.getStage());
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
