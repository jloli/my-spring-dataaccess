package com.belatrixsf.jdbc;

import com.belatrixsf.model.Movie;
import com.belatrixsf.model.MovieManager;
import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class JdbcMovieManager implements MovieManager {

  private final SimpleJdbcInsert simpleJdbcInsert;

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


  public JdbcMovieManager(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.setExceptionTranslator(new CustomSQLErrorCodeTranslator());

    this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName("MOVIE")
        .usingGeneratedKeyColumns("ID");
  }


  public Integer addMovie(Movie movie) {
    HashMap<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("NAME", movie.getName());
    parameters.put("DIRECTOR", movie.getDirector());

    Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
    return id.intValue();
  }


  public void deleteMovieById(Integer id) {
    SqlParameterSource parameter = new MapSqlParameterSource()
        .addValue("id", id);

    namedParameterJdbcTemplate.update("delete from MOVIE where ID = :id", parameter);
  }
}
