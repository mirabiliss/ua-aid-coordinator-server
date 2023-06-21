package com.aid.coordinator.server.controller;

import com.aid.coordinator.server.dto.RequestDto;
import com.aid.coordinator.server.entity.Request;
import com.aid.coordinator.server.mapper.RequestMapper;
import com.aid.coordinator.server.service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RequestControllerTest {

  @Mock
  private RequestService requestService;

  @Mock
  private RequestMapper requestMapper;

  @InjectMocks
  private RequestController requestController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAllRequests_shouldReturnAllRequests() {
    // Arrange
    List<Request> requests = new ArrayList<>();
    requests.add(new Request());
    requests.add(new Request());
    when(requestService.getAllRequests()).thenReturn(requests);

    // Act
    ResponseEntity<List<Request>> response = requestController.getAllRequests();

    // Assert
    assertEquals(requests, response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(requestService, times(1)).getAllRequests();
  }

  @Test
  void getRequestById_withValidId_shouldReturnRequestDto() {
    // Arrange
    Long requestId = 1L;
    Request request = new Request();
    RequestDto requestDto = RequestDto.builder().build();
    when(requestService.getRequestById(requestId)).thenReturn(request);
    when(requestMapper.convertToDto(request)).thenReturn(requestDto);

    // Act
    ResponseEntity<RequestDto> response = requestController.getRequestById(requestId);

    // Assert
    assertEquals(requestDto, response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(requestService, times(1)).getRequestById(requestId);
    verify(requestMapper, times(1)).convertToDto(request);
  }

  @Test
  void getRequestById_withInvalidId_shouldReturnNotFound() {
    // Arrange
    Long requestId = 1L;
    when(requestService.getRequestById(requestId)).thenReturn(null);

    // Act
    ResponseEntity<RequestDto> response = requestController.getRequestById(requestId);

    // Assert
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(requestService, times(1)).getRequestById(requestId);
    verify(requestMapper, times(0)).convertToDto(any(Request.class));
  }

  @Test
  void createRequest_withValidRequestDto_shouldReturnCreatedRequest() {
    // Arrange
    RequestDto requestDto = RequestDto.builder().build();
    Request createdRequest = new Request();
    when(requestService.createRequest(requestDto)).thenReturn(createdRequest);

    // Act
    ResponseEntity<Request> response = requestController.createRequest(requestDto);

    // Assert
    assertEquals(createdRequest, response.getBody());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(requestService, times(1)).createRequest(requestDto);
  }

  @Test
  void updateRequest_withExistingRequest_shouldReturnUpdatedRequest() {
    // Arrange
    Long requestId = 1L;
    Request request = new Request();
    request.setId(requestId);
    Request updatedRequest = new Request();
    when(requestService.updateRequest(request)).thenReturn(updatedRequest);

    // Act
    ResponseEntity<Request> response = requestController.updateRequest(requestId, request);

    // Assert
    assertEquals(updatedRequest, response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(requestService, times(1)).updateRequest(request);
  }

  @Test
  void updateRequest_withNonExistingRequest_shouldReturnNotFound() {
    // Arrange
    Long requestId = 1L;
    Request request = new Request();
    request.setId(requestId);
    when(requestService.updateRequest(request)).thenReturn(null);

    // Act
    ResponseEntity<Request> response = requestController.updateRequest(requestId, request);

    // Assert
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(requestService, times(1)).updateRequest(request);
  }

  @Test
  void deleteRequest_withValidId_shouldDeleteRequest() {
    // Arrange
    Long requestId = 1L;

    // Act
    ResponseEntity<Void> response = requestController.deleteRequest(requestId);

    // Assert
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(requestService, times(1)).deleteRequest(requestId);
  }
}

