package com.belatrixsf.jdbc;

import org.springframework.dao.DataAccessException;

public class InvalidMovieNameException extends DataAccessException {

  public InvalidMovieNameException(String msg, Throwable cause) {
    super(msg, cause);
  }

}
