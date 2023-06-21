package com.aid.coordinator.server.service;

import com.aid.coordinator.server.dto.RequestDto;
import com.aid.coordinator.server.entity.Request;

import java.util.List;

public interface RequestService {
  List<Request> getAllRequests();
  Request getRequestById(Long id);
  Request createRequest(RequestDto requestDto);
  Request updateRequest(Request request);
  void deleteRequest(Long id);
}
