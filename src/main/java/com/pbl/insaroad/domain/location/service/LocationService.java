/* 
 * Copyright (c) SKU PBL Team4 
 */
package com.pbl.insaroad.domain.location.service;

import com.pbl.insaroad.domain.location.dto.request.LocationRequest.CreateLocationRequest;
import com.pbl.insaroad.domain.location.dto.response.LocationResponse;

public interface LocationService {

  LocationResponse create(CreateLocationRequest request);

  void deleteById(Long locationId);
}
