package com.aid.coordinator.server.controller;

import com.aid.coordinator.server.dto.RequestDto;
import com.aid.coordinator.server.entity.Request;
import com.aid.coordinator.server.mapper.RequestMapper;
import com.aid.coordinator.server.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/requests")
@Tag(name = "Requests")
public class RequestController {

  private final RequestService requestService;
  private final RequestMapper requestMapper;

  @Autowired
  public RequestController(final RequestService requestService, final RequestMapper requestMapper) {
    this.requestService = requestService;
    this.requestMapper = requestMapper;
  }

  @Operation(summary = "Get all requests")
  @GetMapping
  public ResponseEntity<List<Request>> getAllRequests() {
    final List<Request> requests = requestService.getAllRequests();
    return new ResponseEntity<>(requests, HttpStatus.OK);
  }

  @Operation(summary = "Get request by id")
  @GetMapping("/{id}")
  public ResponseEntity<RequestDto> getRequestById(final @PathVariable Long id) {
    final Request request = requestService.getRequestById(id);
    final RequestDto dto = requestMapper.convertToDto(request);
    if (dto != null) {
      return new ResponseEntity<>(dto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Create request")
  @PostMapping
  public ResponseEntity<Request> createRequest(final @RequestBody RequestDto requestDto) {
    final Request createdRequest = requestService.createRequest(requestDto);
    return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
  }

  @Operation(summary = "Update request")
  @PutMapping("/{id}")
  public ResponseEntity<Request> updateRequest(final @PathVariable Long id, final @RequestBody Request request) {
    request.setId(id);
    final Request updatedRequest = requestService.updateRequest(request);
    if (updatedRequest != null) {
      return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Delete request")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRequest(final @PathVariable Long id) {
    requestService.deleteRequest(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
