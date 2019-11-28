package com.belatrixsf.jdbc.model;

public interface MovieManager {

  Integer addMovie(Movie movie);

  void deleteMovieById(Integer id);

}
