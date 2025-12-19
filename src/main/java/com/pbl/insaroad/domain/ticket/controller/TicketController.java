/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.ticket.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pbl.insaroad.domain.ticket.dto.request.TicketRequest.ConsumeTicketRequest;
import com.pbl.insaroad.domain.ticket.dto.request.TicketRequest.VerifyTicketRequest;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.ConsumeTicketResponse;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.TicketItemResponse;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.VerifyTicketResponse;
import com.pbl.insaroad.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Ticket", description = "교환권 관련 API")
@RequestMapping("/api/tickets")
public interface TicketController {

  @PostMapping("/admin/verify")
  @Operation(summary = "[관리자] 교환권 검증", description = "token으로 교환권 유효/사용 여부를 확인합니다. (사용 처리 X)")
  ResponseEntity<BaseResponse<VerifyTicketResponse>> verifyTicket(
      @RequestBody @Valid VerifyTicketRequest request);

  @PostMapping("/admin/consume")
  @Operation(summary = "[관리자] 교환권 사용 처리", description = "token으로 교환권을 사용 처리합니다. (멱등 보장)")
  ResponseEntity<BaseResponse<ConsumeTicketResponse>> consumeTicket(
      @RequestBody @Valid ConsumeTicketRequest request);

  @GetMapping("/users/{userCode}")
  @Operation(
      summary = "사용자 코드로 티켓 조회",
      description = "userCode로 사용자를 찾고, 해당 사용자의 티켓 목록(최신순)을 반환합니다.")
  ResponseEntity<BaseResponse<List<TicketItemResponse>>> getTicketsByUserCode(
      @PathVariable String userCode);
}
