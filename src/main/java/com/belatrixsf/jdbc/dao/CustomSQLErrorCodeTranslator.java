package com.belatrixsf.jdbc.dao;

import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

public class CustomSQLErrorCodeTranslator extends SQLErrorCodeSQLExceptionTranslator {

  @Override
  protected DataAccessException customTranslate(String task, String sql,
      SQLException sqlException) {

    if (sqlException.getErrorCode() == 23502) {
      return new InvalidMovieNameException("Movie name is not valid.", sqlException);
    }
    return null;
  }

}
