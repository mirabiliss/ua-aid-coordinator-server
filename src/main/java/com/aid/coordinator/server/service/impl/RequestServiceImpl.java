package com.aid.coordinator.server.service.impl;

import com.aid.coordinator.server.dto.RequestDto;
import com.aid.coordinator.server.entity.Request;
import com.aid.coordinator.server.mapper.RequestMapper;
import com.aid.coordinator.server.repository.RequestRepository;
import com.aid.coordinator.server.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

  private final RequestRepository requestRepository;
  private final RequestMapper requestMapper;


  @Autowired
  public RequestServiceImpl(final RequestRepository requestRepository, final RequestMapper requestMapper) {
    this.requestRepository = requestRepository;
    this.requestMapper = requestMapper;
  }

  @Override
  public List<Request> getAllRequests() {
    return requestRepository.findAll();
  }

  @Override
  public Request getRequestById(final Long id) {
    final Optional<Request> optionalRequest = requestRepository.findById(id);
    return optionalRequest.orElse(null);
  }

  @Override
  public Request createRequest(final RequestDto requestDto) {
    return requestRepository.save(requestMapper.convertToEntity(requestDto));
  }

  @Override
  public Request updateRequest(final Request request) {
    if (requestRepository.existsById(request.getId())) {
      return requestRepository.save(request);
    }
    return null;
  }

  @Override
  public void deleteRequest(final Long id) {
    requestRepository.deleteById(id);
  }
}