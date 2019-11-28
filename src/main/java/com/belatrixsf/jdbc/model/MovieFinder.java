package com.belatrixsf.jdbc.model;

import java.util.List;

public interface MovieFinder {

  List<Movie> findAll();

  List<Movie> findMoviesByDirector(String director);

}
