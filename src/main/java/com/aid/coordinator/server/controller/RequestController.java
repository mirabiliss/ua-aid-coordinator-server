package com.aid.coordinator.server.controller;

import com.aid.coordinator.server.entity.Request;
import com.aid.coordinator.server.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

  private final RequestService requestService;

  @Autowired
  public RequestController(RequestService requestService) {
    this.requestService = requestService;
  }

  @GetMapping
  public ResponseEntity<List<Request>> getAllRequests() {
    List<Request> requests = requestService.getAllRequests();
    return new ResponseEntity<>(requests, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
    Request request = requestService.getRequestById(id);
    if (request != null) {
      return new ResponseEntity<>(request, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Request> createRequest(@RequestBody Request request) {
    Request createdRequest = requestService.createRequest(request);
    return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody Request request) {
    request.setId(id);
    Request updatedRequest = requestService.updateRequest(request);
    if (updatedRequest != null) {
      return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
    requestService.deleteRequest(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
