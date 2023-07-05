package com.aid.coordinator.server.controller;

import com.aid.coordinator.server.entity.User;
import com.aid.coordinator.server.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Get all users")
  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    log.info("Getting all users");
    final List<User> users = userService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @Operation(summary = "Get user by ID")
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(final @PathVariable Long id) {
    log.info("Getting user by id {}", id);
    final User user = userService.getUserById(id);
    if (user != null) {
      return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Find user in DB")
  @PostMapping("/login")
  public ResponseEntity<User> findByEmailAndPassword(final @RequestBody User user) {
    if (log.isInfoEnabled()) {
      log.info("Getting user by email {} and password {}", user.getEmail(), user.getPassword());
    }
    final User resUser = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
    if (resUser != null) {
      return new ResponseEntity<>(resUser, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Create user")
  @PostMapping
  public ResponseEntity<User> createUser(final @RequestBody User user) {
    if (log.isInfoEnabled()) {
      log.info("Creating user with email {}", user.getEmail());
    }
    final User createdUser = userService.createUser(user);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  @Operation(summary = "Update user")
  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(final @PathVariable Long id, final @RequestBody User user) {
    if (log.isInfoEnabled()) {
      log.info("Updating user with email {}", user.getEmail());
    }
    user.setId(id);
    final User updatedUser = userService.updateUser(user);
    if (updatedUser != null) {
      return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Delete user")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(final @PathVariable Long id) {
    if (log.isInfoEnabled()) {
      log.info("Deleting user with id {}", id);
    }
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}