package com.aid.coordinator.server.service.impl;

import com.aid.coordinator.server.entity.User;
import com.aid.coordinator.server.repository.UserRepository;
import com.aid.coordinator.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User getUserById(final Long id) {
    final Optional<User> optionalUser = userRepository.findById(id);
    return optionalUser.orElse(null);
  }

  @Override
  public User findByEmailAndPassword(final String email, final String password) {
    final Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);
    return optionalUser.orElse(null);
  }

  @Override
  public User createUser(final User user) {
    return userRepository.save(user);
  }

  @Override
  public User updateUser(final User user) {
    if (userRepository.existsById(user.getId())) {
      return userRepository.save(user);
    }
    return null;
  }

  @Override
  public void deleteUser(final Long id) {
    userRepository.deleteById(id);
  }
}