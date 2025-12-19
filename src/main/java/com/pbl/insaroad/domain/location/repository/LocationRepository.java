/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pbl.insaroad.domain.location.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

  /** 특정 사용자(userId)가 방문하지 않은 Location 전체 조회 - visitedLocationIds가 비어있어도 안전 */
  @Query(
      """
      select l
      from Location l
      where l.id not in (
        select v
        from User u
        join u.visitedLocationIds v
        where u.id = :userId
      )
      """)
  List<Location> findAllUnvisitedByUserId(Long userId);
}
