/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.ticket.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

public class TicketResponse {

  @Getter
  @Builder
  public static class VerifyTicketResponse {

    private boolean valid;
    private boolean used;

    private Long ticketId;
    private Long targetId;

    private String qrPayload;

    private LocalDateTime issuedAt;
    private LocalDateTime usedAt;
    private LocalDate expiresAt;
  }

  @Getter
  @Builder
  public static class ConsumeTicketResponse {

    private boolean consumed;

    private Long ticketId;
    private Long targetId;

    private String qrPayload;

    private LocalDateTime usedAt;
    private LocalDate expiresAt;
  }

  @Getter
  @Builder
  public static class TicketDetailResponse {

    private Long ticketId;

    private String token;
    private String qrPayload;

    private LocalDateTime issuedAt;
    private LocalDateTime usedAt;
    private LocalDate expiresAt;
  }

  @Getter
  @Builder
  public static class TicketItemResponse {

    private Long ticketId;

    private String token;
    private String qrPayload;

    private LocalDateTime issuedAt;
    private LocalDateTime usedAt;
    private LocalDate expiresAt;

    private boolean expired;
  }
}
