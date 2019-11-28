package com.belatrixsf.jdbc.dao;

import com.belatrixsf.jdbc.model.Movie;
import com.belatrixsf.jdbc.model.MovieFinder;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

@Service
public class JdbcMovieFinder implements MovieFinder {

  private final JdbcTemplate jdbcTemplate;

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


  public JdbcMovieFinder(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }


  public List<Movie> findAll() {
    return jdbcTemplate.query("select * from MOVIE", new MovieRowMapper());
  }

  public List<Movie> findMoviesByDirector(String director) {
    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("director", director);
    return namedParameterJdbcTemplate
        .query("select * from MOVIE where DIRECTOR = :director", namedParameters,
            new MovieRowMapper());
  }

}
