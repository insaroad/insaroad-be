/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.ticket.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pbl.insaroad.domain.ticket.dto.request.TicketRequest.ConsumeTicketRequest;
import com.pbl.insaroad.domain.ticket.dto.request.TicketRequest.VerifyTicketRequest;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.ConsumeTicketResponse;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.VerifyTicketResponse;
import com.pbl.insaroad.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "티켓", description = "교환권(티켓) 검증/사용 처리 API")
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
}
