package kz.attractorschool.moviereviewrr.repository;

import kz.attractorschool.moviereviewrr.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, String> {
    List<Review> findAllByMovie_Id(String id);

    List<Review> findAllByReviewer_Id(String id);

    boolean existsAllByReviewer_IdAndMovie_Id(String movie_id, String user_id);

}
