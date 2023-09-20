package com.aid.coordinator.server.cucumber;

import com.aid.coordinator.server.entity.User;
import com.aid.coordinator.server.service.UserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserSteps {

  @Autowired
  private final UserService userService;
  private User user;
  private User registeredUser;

  public CreateUserSteps(UserService userService) {
    this.userService = userService;
  }

  @Given("a user with valid registration details")
  public void givenAUserWithValidRegistrationDetails() {
    user = new User();
    user.setName("John");
    user.setEmail("john@example.com");
    user.setPassword("securePassword");
  }

  @When("I register the user")
  public void whenIRegisterTheUser() {
    registeredUser = userService.createUser(user);
  }

  @Then("the user is registered successfully")
  public void thenTheUserIsRegisteredSuccessfully() {
    assertNotNull(registeredUser.getId());
    assertEquals(user.getName(), registeredUser.getName());
    assertEquals(user.getEmail(), registeredUser.getEmail());
  }
}
