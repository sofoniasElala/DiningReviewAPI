package com.portfolio.DiningReviewAPI.Repository;

import org.springframework.data.repository.CrudRepository;
import com.portfolio.DiningReviewAPI.Model.DiningReview;
import com.portfolio.DiningReviewAPI.Model.ReviewStatus;
import java.util.List; 

//CRUD repository for Entity: DiningReview
public interface DiningReviewRepository extends CrudRepository <DiningReview, Long> {
    List<DiningReview> findAllByStatus(ReviewStatus status);
    List<DiningReview> findAllByRestaurantIdAndStatus(Long restaurantId, ReviewStatus status);
}
