package com.belatrixsf.jdbc.dao;

import org.springframework.dao.DataAccessException;

public class InvalidMovieNameException extends DataAccessException {

  public InvalidMovieNameException(String msg, Throwable cause) {
    super(msg, cause);
  }

}
