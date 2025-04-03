package org.jethro.parametrage.api.exceptions;

public class ProfessionCodeExistException extends Throwable {
  public ProfessionCodeExistException() {
  }

  public ProfessionCodeExistException(String message) {
    super(message);
  }

  public ProfessionCodeExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
