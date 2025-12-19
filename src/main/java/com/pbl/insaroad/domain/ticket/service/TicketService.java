/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.ticket.service;

import java.util.List;

import com.pbl.insaroad.domain.ticket.dto.request.TicketRequest.ConsumeTicketRequest;
import com.pbl.insaroad.domain.ticket.dto.request.TicketRequest.VerifyTicketRequest;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.ConsumeTicketResponse;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.TicketItemResponse;
import com.pbl.insaroad.domain.ticket.dto.response.TicketResponse.VerifyTicketResponse;
import com.pbl.insaroad.domain.ticket.entity.Ticket;

public interface TicketService {

  /**
   * 티켓을 검증합니다. (사용 처리 없음)
   *
   * <p>주어진 {@code token} 에 해당하는 티켓이 존재하는지, 그리고 이미 사용된 티켓인지 여부를 확인합니다.
   *
   * <p>이 메서드는 <b>티켓을 실제로 사용 처리하지 않으며</b>, QR 스캔 시 사전 검증 용도로 사용됩니다.
   *
   * @param request 티켓 검증 요청 정보 (token)
   * @return 티켓 유효성 및 사용 여부 응답
   */
  VerifyTicketResponse verifyTicket(VerifyTicketRequest request);

  /* =========================
   * Consume (Check-in)
   * ========================= */

  /**
   * 티켓을 사용 처리합니다. (체크인)
   *
   * <p>해당 메서드는 멱등성을 보장하며, 이미 사용된 티켓에 대해 다시 호출되더라도 예외 없이 동일한 상태를 반환합니다.
   *
   * <p>일반적으로 관리자 또는 키오스크에서 QR 스캔 후 실제 입장/수거/처리 시점에 호출됩니다.
   *
   * @param request 티켓 사용 요청 정보 (token)
   * @return 티켓 사용 처리 결과 응답
   */
  ConsumeTicketResponse consumeTicket(ConsumeTicketRequest request);

  Ticket issueNewTicket(Long userId);

  List<TicketItemResponse> getTicketsByUserCode(String userCode);
}
