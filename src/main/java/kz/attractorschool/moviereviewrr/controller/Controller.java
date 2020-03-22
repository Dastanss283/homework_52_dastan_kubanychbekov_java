package kz.attractorschool.moviereviewrr.controller;

import kz.attractorschool.moviereviewrr.model.Movie;
import kz.attractorschool.moviereviewrr.model.Review;
import kz.attractorschool.moviereviewrr.model.User;
import kz.attractorschool.moviereviewrr.repository.MovieRepository;
import kz.attractorschool.moviereviewrr.repository.ReviewRepository;
import kz.attractorschool.moviereviewrr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    @Autowired
    MovieRepository mr;

    @Autowired
    ReviewRepository rr;

    @Autowired
    UserRepository ur;

    @GetMapping("/movie")
    public Iterable<Movie> getMovie() {
        Sort s = Sort.by(Sort.Order.asc("title"));

        return mr.findAll(s);
    }

    @GetMapping("/movienew/{year}")
    public Iterable<Movie> getMovie(@PathVariable("year") int year) {
        return mr.findAllByReleaseYear(year);
    }

    @GetMapping("/moviesortbyname")
    public Iterable<Movie> getMovieSortByName() {
        Sort s = Sort.by(Sort.Order.asc("title"));
        int page = 1;
        int count = 5;
        Pageable pageable = PageRequest.of(page, count, s);
        Page<Movie> res = mr.findAllBy(pageable);
        return res;
    }

    @GetMapping("/moviesortbyrating")
    public Iterable<Movie> getMovieSortByRating() {
        Sort s = Sort.by(Sort.Order.desc("rating"));
        int page = 1;
        int count = 5;
        Pageable pageable = PageRequest.of(page, count, s);
        Page<Movie> res = mr.findAllBy(pageable);
        return res;
    }

    @GetMapping("/moviebetween/{year}/{year2}")
    public Iterable<Movie> getMovieBetween
            (@PathVariable("year") int year,
             @PathVariable("year2") int year2) {
        Sort s = Sort.by(Sort.Order.asc("title"));

        return mr.getMoviesBetween(year, year2, s);
    }

    @GetMapping("/movienewbyactor/{actor}")
    public Iterable<Movie> getMovieByActor
            (@PathVariable("actor") String actor) {
        return mr.findAllByActors(actor);
    }

    @GetMapping("/movienewbytitle/{title}")
    public Iterable<Movie> getMovieByTitle
            (@PathVariable("title") String title) {
        return mr.findByTitle(title);
    }

    @GetMapping("/movienewbyrating/{rating}")
    public Iterable<Movie> getMovieByTitle
            (@PathVariable("rating") double rating) {
        return mr.findAllByRating(rating);
    }

    @GetMapping("/reviewbymovie/{movie_id}")
    public Iterable<Review> getReviewByMovieId
            (@PathVariable("movie_id") String movie_id) {
        return rr.findAllByMovie_Id(movie_id);
    }

    @GetMapping("/reviewbyuserid/{reviewer_id}")
    public Iterable<Review> getReviewByUserId
            (@PathVariable("reviewer_id") String id) {
        return rr.findAllByReviewer_Id(id);
    }

    @GetMapping("/user/{name}")
    public Iterable<User> getUserByName
            (@PathVariable("name") String name) {
        return ur.findByName(name);
    }

    @GetMapping("/checkreview/{user_id}/{movie_id}")
    //проверка на отзыв по фильму
    public boolean checkUserMovie(@PathVariable("user_id") String user_id,
                                  @PathVariable("movie_id") String movie_id) {
        return rr.existsAllByReviewer_IdAndMovie_Id(user_id, movie_id);
    }

    @GetMapping("/userchek/{email}")
    public boolean checkUser
            (@PathVariable("email") String email) {
        return ur.existsByEmail(email);
    }
}