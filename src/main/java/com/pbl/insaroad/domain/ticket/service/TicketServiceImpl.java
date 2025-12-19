/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.ticket.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pbl.insaroad.domain.game.exception.GameErrorCode;
import com.pbl.insaroad.domain.ticket.dto.request.TicketRequest.ConsumeTicketRequest;
import com.pbl.insaroad.domain.ticket.dto.request.TicketRequest.VerifyTicketRequest;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.ConsumeTicketResponse;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.TicketItemResponse;
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

  @Override
  public Ticket issueNewTicket(Long userId) {

    List<Ticket> latestValid =
        ticketRepository.findLatestValidTicketForUpdate(
            userId, LocalDate.now(), PageRequest.of(0, 1));

    if (!latestValid.isEmpty()) {
      return latestValid.getFirst();
    }

    String token = tokenGenerator.generate();
    Ticket ticket = ticketMapper.toEntity(userId, token, LocalDate.now().plusDays(7));
    return ticketRepository.save(ticket);
  }

  @Override
  public List<TicketItemResponse> getTicketsByUserCode(String userCode) {
    User user = getUserByCodeOrThrow(userCode);

    return ticketRepository.findAllByUserIdOrderByCreatedAtDesc(user.getId()).stream()
        .map(ticketMapper::toItemResponse)
        .toList();
  }

  private User getUserByCodeOrThrow(String userCode) {
    if (userCode == null || userCode.isBlank()) {
      throw new CustomException(GameErrorCode.USER_CODE_REQUIRED);
    }

    return userRepository
        .findByCode(userCode)
        .orElseThrow(() -> new CustomException(GameErrorCode.USER_NOT_FOUND));
  }

  @Override
  public Optional<Ticket> findLatestValidTicket(Long userId) {

    List<Ticket> tickets =
        ticketRepository.findLatestValidTicket(userId, LocalDate.now(), PageRequest.of(0, 1));

    return tickets.isEmpty() ? Optional.empty() : Optional.of(tickets.getFirst());
  }
}
