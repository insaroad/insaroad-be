/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.hangulsign.mapper;

import org.springframework.stereotype.Component;

import com.pbl.insaroad.domain.hangulsign.dto.request.QuizCreateRequest;
import com.pbl.insaroad.domain.hangulsign.dto.response.AnswerResponse;
import com.pbl.insaroad.domain.hangulsign.dto.response.QuizCreateResponse;
import com.pbl.insaroad.domain.hangulsign.dto.response.QuizResponse;
import com.pbl.insaroad.domain.hangulsign.entity.HangulSignQuiz;

@Component
public class HangulSignQuizMapper {

  public QuizResponse toQuizResponse(HangulSignQuiz quiz) {
    return QuizResponse.builder()
        .id(quiz.getId())
        .questionImageUrl(quiz.getQuestionImageUrl())
        .choice1(quiz.getChoice1())
        .choice2(quiz.getChoice2())
        .choice3(quiz.getChoice3())
        .build();
  }

  public QuizCreateResponse toQuizCreateResponse(HangulSignQuiz quiz) {
    return QuizCreateResponse.builder()
        .questionImageUrl(quiz.getQuestionImageUrl())
        .answerImageUrl(quiz.getAnswerImageUrl())
        .choice1(quiz.getChoice1())
        .choice2(quiz.getChoice2())
        .choice3(quiz.getChoice3())
        .correctChoice(quiz.getCorrectChoice())
        .build();
  }

  public HangulSignQuiz toEntity(QuizCreateRequest request) {
    return HangulSignQuiz.builder()
        .questionImageUrl(request.getQuestionImageUrl())
        .answerImageUrl(request.getAnswerImageUrl())
        .choice1(request.getChoice1())
        .choice2(request.getChoice2())
        .choice3(request.getChoice3())
        .correctChoice(request.getCorrectChoice())
        .build();
  }

  public AnswerResponse toAnswerResponse(
      boolean isCorrect, String answerImageUrl, Integer currentStage) {
    return AnswerResponse.builder()
        .isCorrect(isCorrect)
        .answerImageUrl(answerImageUrl)
        .currentStage(currentStage)
        .build();
  }
}
