package com.aid.coordinator.server.service;

import com.aid.coordinator.server.entity.Request;

import java.util.List;

public interface RequestService {
  List<Request> getAllRequests();
  Request getRequestById(Long id);
  Request createRequest(Request request);
  Request updateRequest(Request request);
  void deleteRequest(Long id);
}
