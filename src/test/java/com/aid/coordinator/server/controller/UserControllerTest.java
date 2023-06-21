package com.aid.coordinator.server.controller;

import com.aid.coordinator.server.entity.User;
import com.aid.coordinator.server.service.UserService;
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

class UserControllerTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAllUsers_shouldReturnAllUsers() {
    // Arrange
    List<User> users = new ArrayList<>();
    users.add(new User());
    users.add(new User());
    when(userService.getAllUsers()).thenReturn(users);

    // Act
    ResponseEntity<List<User>> response = userController.getAllUsers();

    // Assert
    assertEquals(users, response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(userService, times(1)).getAllUsers();
  }

  @Test
  void getUserById_withValidId_shouldReturnUser() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    when(userService.getUserById(userId)).thenReturn(user);

    // Act
    ResponseEntity<User> response = userController.getUserById(userId);

    // Assert
    assertEquals(user, response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(userService, times(1)).getUserById(userId);
  }

  @Test
  void getUserById_withInvalidId_shouldReturnNotFound() {
    // Arrange
    Long userId = 1L;
    when(userService.getUserById(userId)).thenReturn(null);

    // Act
    ResponseEntity<User> response = userController.getUserById(userId);

    // Assert
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(userService, times(1)).getUserById(userId);
  }

  @Test
  void findByEmailAndPassword_withValidUser_shouldReturnUser() {
    // Arrange
    User user = new User();
    when(userService.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(user);

    // Act
    ResponseEntity<User> response = userController.findByEmailAndPassword(user);

    // Assert
    assertEquals(user, response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(userService, times(1)).findByEmailAndPassword(user.getEmail(), user.getPassword());
  }

  @Test
  void findByEmailAndPassword_withInvalidUser_shouldReturnNotFound() {
    // Arrange
    User user = new User();
    when(userService.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(null);

    // Act
    ResponseEntity<User> response = userController.findByEmailAndPassword(user);

    // Assert
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(userService, times(1)).findByEmailAndPassword(user.getEmail(), user.getPassword());
  }

  @Test
  void createUser_withValidUser_shouldReturnCreatedUser() {
    // Arrange
    User user = new User();
    when(userService.createUser(user)).thenReturn(user);

    // Act
    ResponseEntity<User> response = userController.createUser(user);

    // Assert
    assertEquals(user, response.getBody());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(userService, times(1)).createUser(user);
  }

  @Test
  void updateUser_withExistingUser_shouldReturnUpdatedUser() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    user.setEmail("test@example.com");
    when(userService.updateUser(user)).thenReturn(user);

    // Act
    ResponseEntity<User> response = userController.updateUser(userId, user);

    // Assert
    assertEquals(user, response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userId, user.getId());
    verify(userService, times(1)).updateUser(user);
  }

  @Test
  void updateUser_withNonExistingUser_shouldReturnNotFound() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    when(userService.updateUser(user)).thenReturn(null);

    // Act
    ResponseEntity<User> response = userController.updateUser(userId, user);

    // Assert
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(userService, times(1)).updateUser(user);
  }

  @Test
  void deleteUser_withValidId_shouldDeleteUser() {
    // Arrange
    Long userId = 1L;

    // Act
    ResponseEntity<Void> response = userController.deleteUser(userId);

    // Assert
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(userService, times(1)).deleteUser(userId);
  }
}
