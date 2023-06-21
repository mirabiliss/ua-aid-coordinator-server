package com.aid.coordinator.server.mapper;

import com.aid.coordinator.server.dto.RequestDto;
import com.aid.coordinator.server.entity.Request;
import com.aid.coordinator.server.entity.User;
import com.aid.coordinator.server.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RequestMapperTest {

  @Mock
  private UserRepository userRepository;

  private RequestMapper requestMapper;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    requestMapper = new RequestMapper(userRepository);
  }

  @Test
  public void testConvertToEntity() {
    // Arrange
    Long userId = 1L;
    String location = "Test Location";
    String category = "Test Category";
    String description = "Test Description";

    RequestDto requestDto = RequestDto.builder()
        .userId(userId)
        .location(location)
        .category(category)
        .description(description)
        .build();

    User user = new User();
    user.setId(userId);

    when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

    // Act
    Request request = requestMapper.convertToEntity(requestDto);

    // Assert
    assertEquals(userId, request.getUser().getId());
    assertEquals(location, request.getLocation());
    assertEquals(category, request.getCategory());
    assertEquals(description, request.getDescription());

    verify(userRepository, times(1)).findById(userId);
  }

  @Test
  public void testConvertToDto() {
    // Arrange
    Long userId = 1L;
    String location = "Test Location";
    String category = "Test Category";
    String description = "Test Description";

    User user = new User();
    user.setId(userId);

    Request request = new Request();
    request.setUser(user);
    request.setLocation(location);
    request.setCategory(category);
    request.setDescription(description);

    // Act
    RequestDto requestDto = requestMapper.convertToDto(request);

    // Assert
    assertEquals(userId, requestDto.getUserId());
    assertEquals(location, requestDto.getLocation());
    assertEquals(category, requestDto.getCategory());
    assertEquals(description, requestDto.getDescription());
  }
}

