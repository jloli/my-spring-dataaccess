package com.belatrixsf.jdbc;

import com.belatrixsf.model.Movie;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MovieRowMapper implements RowMapper<Movie> {

  public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
    Movie movie = new Movie();
    movie.setId(rs.getInt("ID"));
    movie.setName(rs.getString("NAME"));
    movie.setDirector(rs.getString("DIRECTOR"));
    return movie;
  }

}
