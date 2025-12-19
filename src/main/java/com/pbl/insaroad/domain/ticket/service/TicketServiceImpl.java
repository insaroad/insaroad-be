/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.ticket.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pbl.insaroad.domain.ticket.dto.request.TicketRequest.ConsumeTicketRequest;
import com.pbl.insaroad.domain.ticket.dto.request.TicketRequest.VerifyTicketRequest;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.ConsumeTicketResponse;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.VerifyTicketResponse;
import com.pbl.insaroad.domain.ticket.entity.Ticket;
import com.pbl.insaroad.domain.ticket.exception.TicketErrorCode;
import com.pbl.insaroad.domain.ticket.mapper.TicketMapper;
import com.pbl.insaroad.domain.ticket.repository.TicketRepository;
import com.pbl.insaroad.domain.ticket.util.TicketTokenGenerator;
import com.pbl.insaroad.domain.user.entity.User;
import com.pbl.insaroad.domain.user.repository.UserRepository;
import com.pbl.insaroad.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;
  private final UserRepository userRepository;

  private final TicketMapper ticketMapper;
  private final TicketTokenGenerator tokenGenerator;

  @Override
  public VerifyTicketResponse verifyTicket(VerifyTicketRequest request) {
    validateToken(request.getToken());
    LocalDate today = LocalDate.now();

    return ticketRepository
        .findByToken(request.getToken())
        .map(
            ticket ->
                ticket.isExpired(today)
                    ? ticketMapper.toVerifyExpired(ticket)
                    : ticketMapper.toVerifyFound(ticket))
        .orElseGet(ticketMapper::toVerifyNotFound);
  }

  @Transactional
  public ConsumeTicketResponse consumeTicket(ConsumeTicketRequest request) {
    validateToken(request.getToken());

    Ticket ticket =
        ticketRepository
            .findByTokenForUpdate(request.getToken())
            .orElseThrow(() -> new CustomException(TicketErrorCode.TICKET_NOT_FOUND));

    boolean alreadyUsed = ticket.isUsed();
    if (!alreadyUsed) {
      ticket.consume(LocalDateTime.now());

      User user = userRepository.findById(ticket.getUserId()).orElse(null);
      if (user != null && !user.isRewardReceived()) {
        user.receiveReward();
      }
    }

    return ticketMapper.toConsume(ticket, !alreadyUsed);
  }

  private void validateToken(String token) {
    if (token == null || token.isBlank()) {
      throw new CustomException(TicketErrorCode.TICKET_TOKEN_REQUIRED);
    }
  }

  private Ticket issueNewTicket(Long userId) {
    try {
      String token = tokenGenerator.generate();

      Ticket ticket = ticketMapper.toEntity(userId, token, LocalDate.now().plusDays(7));

      return ticketRepository.save(ticket);

    } catch (Exception e) {
      log.error("[MISSION] ticket issue failed userId={}", userId, e);
      throw e;
    }
  }
}
