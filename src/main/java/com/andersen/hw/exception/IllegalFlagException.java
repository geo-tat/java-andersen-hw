package com.andersen.hw.exception;

import lombok.Getter;

@Getter
public class IllegalFlagException extends RuntimeException {
  private final String message;

  public IllegalFlagException(String message) {
    this.message = message;
  }
}
