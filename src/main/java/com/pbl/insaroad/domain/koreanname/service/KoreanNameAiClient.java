/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.koreanname.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbl.insaroad.domain.koreanname.dto.response.KoreanNameAiResponse;
import com.pbl.insaroad.domain.koreanname.exception.KoreanNameErrorCode;
import com.pbl.insaroad.global.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KoreanNameAiClient {

  private final WebClient webClient;
  private final ObjectMapper objectMapper;
  private final String model;

  public KoreanNameAiClient(
      @Value("${openai.api-key}") String apiKey,
      @Value("${openai.model:gpt-4.1-mini}") String model,
      @Value("${openai.url:https://api.openai.com/v1}") String openAiUrl,
      ObjectMapper objectMapper) {

    // 키가 제대로 들어오는지 한번 찍어보자
    log.info(
        "[KoreanNameAiClient] OpenAI apiKey length = {}", apiKey == null ? null : apiKey.length());
    log.info(
        "[KoreanNameAiClient] OpenAI apiKey preview = '{}'",
        apiKey == null ? null : apiKey.substring(0, Math.min(6, apiKey.length())));

    this.model = model;
    this.objectMapper = objectMapper;
    this.webClient =
        WebClient.builder()
            .baseUrl(openAiUrl)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
  }

  public KoreanNameAiResponse requestKoreanName(String systemPrompt, String userPrompt) {
    try {
      Map<String, Object> body =
          Map.of(
              "model", model,
              "response_format", Map.of("type", "json_object"),
              "messages",
                  List.of(
                      Map.of("role", "system", "content", systemPrompt),
                      Map.of("role", "user", "content", userPrompt)));

      String rawResponse =
          webClient
              .post()
              .uri("/chat/completions")
              .bodyValue(body)
              .retrieve()
              .bodyToMono(String.class)
              .block();

      // choices[0].message.content 에 JSON 문자열이 들어있다고 가정
      String jsonContent =
          objectMapper
              .readTree(rawResponse)
              .path("choices")
              .get(0)
              .path("message")
              .path("content")
              .asText();

      return objectMapper.readValue(jsonContent, KoreanNameAiResponse.class);
    } catch (Exception e) {
      log.error("OpenAI API 호출 실패", e);
      throw new CustomException(KoreanNameErrorCode.OPENAI_REQUEST_FAILED);
    }
  }
}
