package com.aid.coordinator.server.service;

import com.aid.coordinator.server.model.Request;
import com.aid.coordinator.server.repository.RequestRepository;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RequestService {

  @Autowired
  private RequestRepository requestRepository;

  public List<Request> getAllRequests() {
    return requestRepository.findAll();
  }

  @Transactional
  public String createRequest(Request request) {
    try {
      Long maxId = requestRepository.findMaxId();
      request.setId(null == maxId ? 0 : maxId + 1);
      requestRepository.save(request);
      return "Request created successfully: " + request;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Transactional
  public String updateRequest(Request request) {
    if (requestRepository.existsById(request.getId())) {
      try {
        Request requestToBeUpdated = requestRepository.findById(request.getId()).get();
        requestToBeUpdated.setUser(request.getUser());
        requestToBeUpdated.setLocation(request.getLocation());
        requestToBeUpdated.setCategory(request.getCategory());
        requestToBeUpdated.setDescription(request.getDescription());
        requestRepository.save(requestToBeUpdated);
        return "Request updated: " + request ;
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        throw e;
      }
    } else {
      return "Request does not exist in the database.";
    }
  }

  @Transactional
  public String deleteRequest(Request request) {
    if (requestRepository.existsById(request.getId())) {
      try {
        requestRepository.delete(request);
        return "Request deleted successfully.";
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        throw e;
      }
    } else {
      return "Request does not exist in database.";
    }
  }

}
