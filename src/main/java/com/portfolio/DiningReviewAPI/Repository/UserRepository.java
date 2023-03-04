package com.portfolio.DiningReviewAPI.Repository;

import org.springframework.data.repository.CrudRepository;
import com.portfolio.DiningReviewAPI.Model.User;
import java.util.Optional;

//CRUD repository for Entity: User
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserNameOptional(String userName);
    User findByUserName(String userName);
}
