package kz.attractorschool.moviereviewrr.repository;

import kz.attractorschool.moviereviewrr.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Iterable<User> findByEmail(String email);

    Iterable<User> findByName(String name);

    boolean existsByEmail(String email);
}
