package com.belatrixsf.jdbc;

import static org.assertj.core.api.Assertions.assertThat;

import com.belatrixsf.jdbc.dao.InvalidMovieNameException;
import com.belatrixsf.jdbc.model.Movie;
import com.belatrixsf.jdbc.model.MovieLister;
import com.belatrixsf.jdbc.model.MovieManager;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringJdbcConfig.class}, loader = AnnotationConfigContextLoader.class)
public class SpringJdbcTest {

  @Autowired
  private MovieLister movieLister;

  @Autowired
  private MovieManager movieManager;


  @Test
  public void whenFindAllMovies_shouldReturnNonEmptyList() {
    List<Movie> movies = movieLister.findAllMovies();

    assertThat(movies).hasSize(8);
  }

  @Test
  public void whenFindMoviesByDirector_shouldReturnNonEmptyList() {
    List<Movie> movies = movieLister.findMoviesByDirector("Stanley Kubrick");

    assertThat(movies).hasSize(3);
  }

  @Test
  public void whenAMovieIsAdded_shouldReturnNonEmptyList() {
    List<Movie> movies = movieLister.findMoviesByDirector("Ingmar Bergman");

    assertThat(movies).hasSize(0);

    Movie movie = new Movie();
    movie.setDirector("Ingmar Bergman");
    movie.setName("Cries and Whispers");
    movieManager.addMovie(movie);

    movies = movieLister.findMoviesByDirector("Ingmar Bergman");
    assertThat(movies).hasSize(1);
  }

  @Test
  public void whenDeleteMovieById_shouldReturnEmptyList() {
    List<Movie> movies = movieLister.findMoviesByDirector("Ivan Reitman");

    assertThat(movies).hasSize(1);
    assertThat(movies.get(0).getDirector()).isEqualTo("Ivan Reitman");

    movieManager.deleteMovieById(movies.get(0).getId());

    movies = movieLister.findMoviesByDirector("Ivan Reitman");
    assertThat(movies).hasSize(0);
  }

  @Test(expected = InvalidMovieNameException.class)
  public void whenAMovieIsAddedWithoutName_shouldThrowException() {
    Movie movie = new Movie();
    movie.setDirector("Akira Kurosawa");
    movieManager.addMovie(movie);
  }

}
