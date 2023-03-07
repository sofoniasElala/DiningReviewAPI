package com.portfolio.DiningReviewAPI.Controller;

import com.portfolio.DiningReviewAPI.Model.*;
import com.portfolio.DiningReviewAPI.Repository.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@RestController
@RequestMapping("/dining_reviews")
public class DiningReviewsController {
    
    private final DiningReviewRepository diningReviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public DiningReviewsController(DiningReviewRepository diningReviewRepository, UserRepository userRepository, 
    RestaurantRepository restaurantRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }


    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.CREATED)
    public DiningReview submitDiningReview(@RequestBody DiningReview diningReview){
        ;
        Optional<User> isUserRegistered = userRepository.findByUserNameIgnoreCase(diningReview.getUserName());
        Optional<Restaurant> restaurantExists = restaurantRepository.findById(diningReview.getRestaurantId());

        if(!isUserRegistered.isPresent() || !restaurantExists.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Make sure user name and restaurant id are valid.");

       diningReview.setStatus(ReviewStatus.Pending);
       DiningReview submittedDiningReview = diningReviewRepository.save(diningReview);

       return submittedDiningReview;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public DiningReview getReview(@PathVariable("id") Long id){
        Optional<DiningReview> review = diningReviewRepository.findById(id);
        if(!review.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review with ID" + id + "does not exist.");
        
        return review.get();
    }
}
