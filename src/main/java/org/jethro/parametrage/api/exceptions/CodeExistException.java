package org.jethro.parametrage.api.exceptions;

public class CodeExistException extends RuntimeException {
  public CodeExistException() {
  }

  public CodeExistException(String message) {
    super(message);
  }

  public CodeExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
