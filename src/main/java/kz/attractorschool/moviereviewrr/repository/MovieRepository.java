package kz.attractorschool.moviereviewrr.repository;

import kz.attractorschool.moviereviewrr.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

    Iterable<Movie> findAll(Sort s);

    Iterable<Movie> findAllByReleaseYear(int year);

    Iterable<Movie> findAllByReleaseYearBetween(int year, int year2, Sort s);

    @Query("{'releaseYear' : { '$gte' : ?0, '$lte' : ?1 }}")
    Iterable<Movie> getMoviesBetween(int year, int year2, Sort s);

    Iterable<Movie> findAllByActors(String actor);

    Iterable<Movie> findByTitle(String title);

    Iterable<Movie> findAllByRating(double rating);

    Page<Movie> findAllBy(Pageable pageable);
}
