package com.belatrixsf.model;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MovieLister {

  private final MovieFinder movieFinder;

  public MovieLister(MovieFinder movieFinder) {
    this.movieFinder = movieFinder;
  }

  public List<Movie> findAllMovies() {
    List<Movie> movies = movieFinder.findAll();
    if (movies.isEmpty()) {
      return Collections.emptyList();
    }
    return movies;
  }

  public List<Movie> findMoviesByDirector(String director) {
    return movieFinder.findMoviesByDirector(director);
  }

}
