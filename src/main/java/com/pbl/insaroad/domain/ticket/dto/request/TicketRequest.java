/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.ticket.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TicketRequest {

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class VerifyTicketRequest {

    @NotBlank private String token;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ConsumeTicketRequest {

    @NotBlank private String token;
  }
}
