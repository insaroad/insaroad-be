/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.location.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pbl.insaroad.domain.location.dto.request.LocationRequest.CreateLocationRequest;
import com.pbl.insaroad.domain.location.dto.response.LocationResponse;
import com.pbl.insaroad.domain.location.entity.Location;
import com.pbl.insaroad.domain.location.exception.LocationErrorCode;
import com.pbl.insaroad.domain.location.repository.LocationRepository;
import com.pbl.insaroad.global.exception.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocationServiceImpl implements LocationService {

  private final LocationRepository locationRepository;

  @Override
  @Transactional
  public LocationResponse create(CreateLocationRequest request) {
    if (request.getName() == null || request.getName().isBlank()) {
      throw new CustomException(LocationErrorCode.LOCATION_BAD_REQUEST);
    }

    Location saved =
        locationRepository.save(
            Location.builder()
                .name(request.getName())
                .nameEn(request.getNameEn())
                .description(request.getDescription())
                .address(request.getAddress())
                .imageUrl(request.getImageUrl())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build());

    return LocationResponse.from(saved);
  }

  @Override
  @Transactional
  public void deleteById(Long locationId) {
    Location location =
        locationRepository
            .findById(locationId)
            .orElseThrow(() -> new CustomException(LocationErrorCode.LOCATION_NOT_FOUND));
    locationRepository.delete(location);
  }
}
