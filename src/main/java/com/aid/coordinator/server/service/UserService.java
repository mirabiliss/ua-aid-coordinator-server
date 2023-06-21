package com.aid.coordinator.server.service;

import com.aid.coordinator.server.entity.User;

import java.util.List;

public interface UserService {
  List<User> getAllUsers();
  User getUserById(Long id);
  User findByEmailAndPassword(String email, String password);
  User createUser(User user);
  User updateUser(User user);
  void deleteUser(Long id);
}