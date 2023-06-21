package com.aid.coordinator.server.entity;

import com.aid.coordinator.server.repository.UserRepository;
import com.aid.coordinator.server.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserTest {

  @Mock
  private UserRepository userRepository;

  private User user;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    user = new User();
    user.setId(1L);
    user.setName("John");
    user.setSurname("Doe");
    user.setEmail("john.doe@example.com");
    user.setPassword("password");
  }

  @Test
  void testGettersAndSetters() {
    assertEquals(1L, user.getId());
    assertEquals("John", user.getName());
    assertEquals("Doe", user.getSurname());
    assertEquals("john.doe@example.com", user.getEmail());
    assertEquals("password", user.getPassword());
  }

  @Test
  void testEqualsAndHashCode() {
    User user1 = new User();
    user1.setId(1L);

    User user2 = new User();
    user2.setId(1L);

    User user3 = new User();
    user3.setId(2L);

    assertEquals(user1, user2);
    assertNotEquals(user1, user3);
    assertEquals(user1.hashCode(), user2.hashCode());
  }

  @Test
  void testGetAllUsers() {
    User user1 = new User();
    user1.setId(1L);
    User user2 = new User();
    user2.setId(2L);

    List<User> userList = Arrays.asList(user1, user2);
    when(userRepository.findAll()).thenReturn(userList);

    UserServiceImpl userService = new UserServiceImpl(userRepository);
    List<User> result = userService.getAllUsers();

    assertEquals(2, result.size());
    assertEquals(userList, result);
    verify(userRepository, times(1)).findAll();
  }

  @Test
  void testGetUserById() {
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    UserServiceImpl userService = new UserServiceImpl(userRepository);
    User result = userService.getUserById(1L);

    assertEquals(user, result);
    verify(userRepository, times(1)).findById(1L);
  }

  @Test
  void testFindByEmailAndPassword() {
    when(userRepository.findByEmailAndPassword("john.doe@example.com", "password")).thenReturn(Optional.of(user));

    UserServiceImpl userService = new UserServiceImpl(userRepository);
    User result = userService.findByEmailAndPassword("john.doe@example.com", "password");

    assertEquals(user, result);
    verify(userRepository, times(1)).findByEmailAndPassword("john.doe@example.com", "password");
  }

  @Test
  void testCreateUser() {
    when(userRepository.save(user)).thenReturn(user);

    UserServiceImpl userService = new UserServiceImpl(userRepository);
    User result = userService.createUser(user);

    assertEquals(user, result);
    verify(userRepository, times(1)).save(user);
  }

  @Test
  void testUpdateUser() {
    when(userRepository.existsById(1L)).thenReturn(true);
    when(userRepository.save(user)).thenReturn(user);

    UserServiceImpl userService = new UserServiceImpl(userRepository);
    User result = userService.updateUser(user);

    assertEquals(user, result);
    verify(userRepository, times(1)).existsById(1L);
    verify(userRepository, times(1)).save(user);
  }
}