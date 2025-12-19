/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.ticket.repository;

import java.util.Optional;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pbl.insaroad.domain.ticket.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

  Optional<Ticket> findByToken(String token);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("select t from Ticket t where t.token = :token")
  Optional<Ticket> findByTokenForUpdate(@Param("token") String token);

  /** 사용자당 1장의 교환권만 허용 (멱등) */
  Optional<Ticket> findTop1ByUserIdOrderByCreatedAtDesc(Long userId);
}
