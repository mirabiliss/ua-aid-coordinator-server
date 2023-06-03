package com.aid.coordinator.server.service;

import com.aid.coordinator.server.model.User;
import com.aid.coordinator.server.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Transactional
  public String createUser(User user) {
    try {
      if (!userRepository.existsByEmail(user.getEmail())) {
        Long maxId = userRepository.findMaxId();
        user.setId(null == maxId ? 0 : maxId + 1);
        userRepository.save(user);
        return "User created successfully.";
      } else {
        return "User already exists in the database.";
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Transactional
  public String updateUser(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      try {
        User userToBeUpdated = userRepository.findById(user.getId()).get();
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setEmail(user.getEmail());
        userRepository.save(userToBeUpdated);
        return "User updated.";
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        throw e;
      }
    } else {
      return "User does not exist in the database.";
    }
  }

  @Transactional
  public String deleteUser(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      try {
        userRepository.delete(user);
        return "User deleted successfully.";
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        throw e;
      }
    } else {
      return "User does not exist";
    }
  }

}
