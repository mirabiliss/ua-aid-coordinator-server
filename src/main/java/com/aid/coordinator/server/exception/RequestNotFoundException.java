package com.aid.coordinator.server.exception;

import com.aid.coordinator.server.entity.enums.ErrorType;

public class RequestNotFoundException extends ServiceException {

  private static final String DEFAULT_MESSAGE = "Request was not found!";

  public RequestNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.VALIDATION_ERROR_TYPE;
  }

}
