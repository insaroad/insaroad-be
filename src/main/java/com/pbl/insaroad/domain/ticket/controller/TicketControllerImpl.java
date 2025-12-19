/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.ticket.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pbl.insaroad.domain.ticket.dto.request.TicketRequest.ConsumeTicketRequest;
import com.pbl.insaroad.domain.ticket.dto.request.TicketRequest.VerifyTicketRequest;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.ConsumeTicketResponse;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.TicketItemResponse;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.VerifyTicketResponse;
import com.pbl.insaroad.domain.ticket.service.TicketService;
import com.pbl.insaroad.global.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TicketControllerImpl implements TicketController {

  private final TicketService ticketService;

  @Override
  public ResponseEntity<BaseResponse<VerifyTicketResponse>> verifyTicket(
      @RequestBody @Valid VerifyTicketRequest request) {
    return ResponseEntity.ok(BaseResponse.success(ticketService.verifyTicket(request)));
  }

  @Override
  public ResponseEntity<BaseResponse<ConsumeTicketResponse>> consumeTicket(
      @RequestBody @Valid ConsumeTicketRequest request) {
    return ResponseEntity.ok(BaseResponse.success(ticketService.consumeTicket(request)));
  }

  @Override
  public ResponseEntity<BaseResponse<List<TicketItemResponse>>> getTicketsByUserCode(
      String userCode) {
    return ResponseEntity.ok(BaseResponse.success(ticketService.getTicketsByUserCode(userCode)));
  }
}
