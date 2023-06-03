package com.aid.coordinator.server.controller;

import com.aid.coordinator.server.model.User;
import com.aid.coordinator.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "getAllUsers", method = RequestMethod.GET)
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @RequestMapping(value = "getUser", method = RequestMethod.POST)
  public User getUserByEmail(@RequestBody String email) {
    return userService.getUserByEmail(email);
  }

  @RequestMapping(value = "createUser", method = RequestMethod.POST)
  public String createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @RequestMapping(value = "updateUser", method = RequestMethod.PUT)
  public String updateUser(@RequestBody User user) {
    return userService.updateUser(user);
  }

  @RequestMapping(value = "deleteUser", method = RequestMethod.DELETE)
  public String deleteUser(@RequestBody User user) {
    return userService.deleteUser(user);
  }

}
