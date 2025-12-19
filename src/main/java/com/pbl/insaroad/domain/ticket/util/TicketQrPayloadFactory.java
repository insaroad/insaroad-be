/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.ticket.util;

import org.springframework.stereotype.Component;

@Component
public class TicketQrPayloadFactory {

  private static final String QR_BASE_URL = "https://api.danchu.site/ticket";
  private static final String QR_VERSION = "1";

  public String create(String token) {
    return QR_BASE_URL + "?v=" + QR_VERSION + "&token=" + token;
  }
}
