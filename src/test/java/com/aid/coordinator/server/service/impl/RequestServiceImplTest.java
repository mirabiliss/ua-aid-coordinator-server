package com.aid.coordinator.server.service.impl;

import com.aid.coordinator.server.dto.RequestDto;
import com.aid.coordinator.server.entity.Request;
import com.aid.coordinator.server.mapper.RequestMapper;
import com.aid.coordinator.server.repository.RequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RequestServiceImplTest {

  @Mock
  private RequestRepository requestRepository;

  @Mock
  private RequestMapper requestMapper;

  @InjectMocks
  private RequestServiceImpl requestService;

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
    when(requestRepository.findAll()).thenReturn(requests);

    // Act
    List<Request> result = requestService.getAllRequests();

    // Assert
    assertEquals(2, result.size());
    verify(requestRepository, times(1)).findAll();
  }

  @Test
  void getRequestById_withValidId_shouldReturnRequest() {
    // Arrange
    Long requestId = 1L;
    Request request = new Request();
    when(requestRepository.findById(requestId)).thenReturn(Optional.of(request));

    // Act
    Request result = requestService.getRequestById(requestId);

    // Assert
    assertEquals(request, result);
    verify(requestRepository, times(1)).findById(requestId);
  }

  @Test
  void getRequestById_withInvalidId_shouldReturnNull() {
    // Arrange
    Long requestId = 1L;
    when(requestRepository.findById(requestId)).thenReturn(Optional.empty());

    // Act
    Request result = requestService.getRequestById(requestId);

    // Assert
    assertEquals(null, result);
    verify(requestRepository, times(1)).findById(requestId);
  }

  @Test
  void createRequest_withValidRequestDto_shouldReturnCreatedRequest() {
    // Arrange
    RequestDto requestDto = RequestDto.builder()
        .userId(1L)
        .location("Location")
        .category("Category")
        .description("Description")
        .build();
    Request request = new Request();
    when(requestMapper.convertToEntity(requestDto)).thenReturn(request);
    when(requestRepository.save(request)).thenReturn(request);

    // Act
    Request result = requestService.createRequest(requestDto);

    // Assert
    assertEquals(request, result);
    verify(requestMapper, times(1)).convertToEntity(requestDto);
    verify(requestRepository, times(1)).save(request);
  }

  @Test
  void updateRequest_withExistingRequest_shouldReturnUpdatedRequest() {
    // Arrange
    Request request = new Request();
    request.setId(1L);
    when(requestRepository.existsById(request.getId())).thenReturn(true);
    when(requestRepository.save(request)).thenReturn(request);

    // Act
    Request result = requestService.updateRequest(request);

    // Assert
    assertEquals(request, result);
    verify(requestRepository, times(1)).existsById(request.getId());
    verify(requestRepository, times(1)).save(request);
  }

  @Test
  void updateRequest_withNonExistingRequest_shouldReturnNull() {
    // Arrange
    Request request = new Request();
    request.setId(1L);
    when(requestRepository.existsById(request.getId())).thenReturn(false);

    // Act
    Request result = requestService.updateRequest(request);

    // Assert
    assertEquals(null, result);
    verify(requestRepository, times(1)).existsById(request.getId());
    verify(requestRepository, times(0)).save(request);
  }

  @Test
  void deleteRequest_withValidId_shouldDeleteRequest() {
    // Arrange
    Long requestId = 1L;

    // Act
    requestService.deleteRequest(requestId);

    // Assert
    verify(requestRepository, times(1)).deleteById(requestId);
  }
}
