package com.aid.coordinator.server.entity;

import com.aid.coordinator.server.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

  @Mock
  private UserRepository userRepository;

  private User user;
  private Request request;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    user = new User();
    user.setId(1L);

    request = new Request();
    request.setId(1L);
    request.setUser(user);
    request.setLocation("Location");
    request.setCategory("Category");
    request.setDescription("Description");
    request.setStatus("Status");
  }

  @Test
  void testGettersAndSetters() {
    assertEquals(1L, request.getId());
    assertEquals(user, request.getUser());
    assertEquals("Location", request.getLocation());
    assertEquals("Category", request.getCategory());
    assertEquals("Description", request.getDescription());
    assertEquals("Status", request.getStatus());
  }

  @Test
  void testEqualsAndHashCode() {
    Request request1 = new Request();
    request1.setId(1L);

    Request request2 = new Request();
    request2.setId(1L);

    Request request3 = new Request();
    request3.setId(2L);

    assertEquals(request1, request2);
    assertNotEquals(request1, request3);
    assertEquals(request1.hashCode(), request2.hashCode());
  }
}
