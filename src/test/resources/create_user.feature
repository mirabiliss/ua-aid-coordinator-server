Feature: User Registration
  As a coordinator
  I want to register a new user
  So that they can access the system

  Scenario: Successful user registration
    Given a user with valid registration details
    When I register the user
    Then the user is registered successfully
