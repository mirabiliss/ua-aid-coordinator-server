package com.aid.coordinator.server.exception;

import com.aid.coordinator.server.entity.enums.ErrorType;

public class UserNotFoundException extends ServiceException {

  private static final String DEFAULT_MESSAGE = "User was not found!";

  public UserNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.VALIDATION_ERROR_TYPE;
  }

}
