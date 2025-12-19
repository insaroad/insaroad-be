/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.ticket.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.LockModeType;

import org.springframework.data.domain.Pageable;
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

  List<Ticket> findAllByUserIdOrderByCreatedAtDesc(Long userId);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query(
      """
          select t
          from Ticket t
          where t.userId = :userId
            and t.usedAt is null
            and (t.expiresAt is null or t.expiresAt >= :today)
          order by t.createdAt desc
          """)
  List<Ticket> findLatestValidTicketForUpdate(
      @Param("userId") Long userId, @Param("today") LocalDate today, Pageable pageable);

  Optional<Ticket> findTop1ByUserIdOrderByCreatedAtDesc(Long userId);

  @Query(
      """
        SELECT t
        FROM Ticket t
        WHERE t.userId = :userId
          AND t.expiresAt >= :today
        ORDER BY t.createdAt DESC
      """)
  List<Ticket> findLatestValidTicket(
      @Param("userId") Long userId, @Param("today") LocalDate today, Pageable pageable);
}
