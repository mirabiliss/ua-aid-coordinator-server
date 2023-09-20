package com.aid.coordinator.server.service.impl;

import com.aid.coordinator.server.entity.User;
import com.aid.coordinator.server.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testGetUserById() {
    // Create a sample user
    User user = new User();
    user.setId(1L);
    user.setName("John");
    user.setEmail("john@example.com");
    user.setPassword("secret");

    // Mock the UserRepository behavior
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    when(userRepository.findById(2L)).thenReturn(Optional.empty());

    // Test getting an existing user
    User existingUser = userService.getUserById(1L);
    assertNotNull(existingUser);
    assertEquals(user.getId(), existingUser.getId());
    assertEquals(user.getName(), existingUser.getName());
    assertEquals(user.getEmail(), existingUser.getEmail());
    assertEquals(user.getPassword(), existingUser.getPassword());

    // Test getting a non-existing user
    User nonExistingUser = userService.getUserById(2L);
    assertNull(nonExistingUser);
  }

  @Test
  void testCreateUser() {
    // Create a sample user
    User user = new User();
    user.setName("Alice");
    user.setEmail("alice@example.com");
    user.setPassword("password");

    // Mock the UserRepository behavior
    when(userRepository.save(user)).thenReturn(user);

    // Test user creation
    User createdUser = userService.createUser(user);
    assertNotNull(createdUser);
    assertEquals(user.getName(), createdUser.getName());
    assertEquals(user.getEmail(), createdUser.getEmail());
    assertEquals(user.getPassword(), createdUser.getPassword());
  }

  @Test
  void testUpdateUser() {
    // Create a sample user
    User user = new User();
    user.setId(1L);
    user.setName("John");
    user.setEmail("john@example.com");
    user.setPassword("secret");

    // Mock the UserRepository behavior
    when(userRepository.existsById(1L)).thenReturn(true);
    when(userRepository.save(user)).thenReturn(user);

    // Test updating an existing user
    User updatedUser = userService.updateUser(user);
    assertNotNull(updatedUser);
    assertEquals(user.getId(), updatedUser.getId());
    assertEquals(user.getName(), updatedUser.getName());
    assertEquals(user.getEmail(), updatedUser.getEmail());
    assertEquals(user.getPassword(), updatedUser.getPassword());

    // Test updating a non-existing user
    User nonExistingUser = new User();
    nonExistingUser.setId(2L);
    User result = userService.updateUser(nonExistingUser);
    assertNull(result);
  }

  @Test
  void testDeleteUser() {
    // Mock the UserRepository behavior
    doNothing().when(userRepository).deleteById(1L);
    doThrow(new EmptyResultDataAccessException(1)).when(userRepository).deleteById(2L);

    // Test deleting an existing user
    assertDoesNotThrow(() -> userService.deleteUser(1L));

    // Test deleting a non-existing user
    assertThrows(EmptyResultDataAccessException.class, () -> userService.deleteUser(2L));
  }
}
