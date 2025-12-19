/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.ticket.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.ConsumeTicketResponse;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.VerifyTicketResponse;
import com.pbl.insaroad.domain.ticket.entity.Ticket;
import com.pbl.insaroad.domain.ticket.util.TicketQrPayloadFactory;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TicketMapper {

  private final TicketQrPayloadFactory qrPayloadFactory;

  public Ticket toEntity(Long userId, String token, LocalDate expiresAt) {
    return Ticket.builder().userId(userId).token(token).expiresAt(expiresAt).build();
  }

  public VerifyTicketResponse toVerifyFound(Ticket ticket) {
    return VerifyTicketResponse.builder()
        .valid(true)
        .used(ticket.isUsed())
        .ticketId(ticket.getId())
        .qrPayload(qrPayloadFactory.create(ticket.getToken()))
        .issuedAt(ticket.getCreatedAt())
        .usedAt(ticket.getUsedAt())
        .expiresAt(ticket.getExpiresAt())
        .build();
  }

  public VerifyTicketResponse toVerifyExpired(Ticket ticket) {
    return VerifyTicketResponse.builder()
        .valid(false)
        .used(false)
        .ticketId(ticket.getId())
        .qrPayload(qrPayloadFactory.create(ticket.getToken()))
        .issuedAt(ticket.getCreatedAt())
        .usedAt(ticket.getUsedAt())
        .expiresAt(ticket.getExpiresAt())
        .build();
  }

  public VerifyTicketResponse toVerifyNotFound() {
    return VerifyTicketResponse.builder().valid(false).used(false).build();
  }

  public ConsumeTicketResponse toConsume(Ticket ticket, boolean consumed) {
    return ConsumeTicketResponse.builder()
        .consumed(consumed)
        .ticketId(ticket.getId())
        .qrPayload(qrPayloadFactory.create(ticket.getToken()))
        .usedAt(ticket.getUsedAt())
        .expiresAt(ticket.getExpiresAt())
        .build();
  }
}
