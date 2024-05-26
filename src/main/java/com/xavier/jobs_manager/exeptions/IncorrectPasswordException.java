package com.xavier.jobs_manager.exeptions;

public class IncorrectPasswordException extends RuntimeException {
  public IncorrectPasswordException(String message) {
    super(message);
  }
}