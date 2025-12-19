/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.game.mapper;

import java.util.List;

import com.pbl.insaroad.domain.game.dto.response.GameResponse.CompleteResponse;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.FinishResponse;
import com.pbl.insaroad.domain.game.dto.response.GameResponse.GameProgressResponse;
import com.pbl.insaroad.domain.location.dto.LocationResponse;
import com.pbl.insaroad.domain.location.entity.Location;
import com.pbl.insaroad.domain.ticket.entity.Ticket;
import com.pbl.insaroad.domain.user.entity.User;

public final class GameMapper {

  private GameMapper() {}

  /* ---------- 미완주 ---------- */

  public static GameProgressResponse toInProgress(User user, List<Location> unvisited) {
    return GameProgressResponse.builder()
        .completed(false)
        .complete(
            CompleteResponse.builder()
                .userCode(user.getCode())
                .unvisitedLocations(unvisited.stream().map(LocationResponse::from).toList())
                .build())
        .finish(null)
        .build();
  }

  /* ---------- 완주 + 티켓 있음 ---------- */

  public static GameProgressResponse toCompletedWithTicket(User user, Ticket ticket, String qrUrl) {
    return GameProgressResponse.builder()
        .completed(true)
        .complete(null)
        .finish(FinishResponse.builder().exchangeUrl(qrUrl).issuedAt(ticket.getCreatedAt()).build())
        .build();
  }

  /* ---------- 완주 + 티켓 없음 ---------- */

  public static GameProgressResponse toCompletedWithoutTicket() {
    return GameProgressResponse.builder().completed(true).complete(null).finish(null).build();
  }
}
