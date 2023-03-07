package com.portfolio.DiningReviewAPI.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.portfolio.DiningReviewAPI.Model.ReviewStatus;
import com.portfolio.DiningReviewAPI.Model.AdminReviewAction;
import java.util.List;
import java.util.Optional;
import java.util.IntSummaryStatistics;

import com.portfolio.DiningReviewAPI.Model.*;
import com.portfolio.DiningReviewAPI.Repository.*;

@RestController
@RequestMapping("/admin")
public class AdminReviewActionsController {
    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;

    public AdminReviewActionsController(DiningReviewRepository diningReviewRepository, 
    RestaurantRepository restaurantRepository){
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/pending_reviews")
    @ResponseStatus(HttpStatus.FOUND)
    public List<DiningReview> getPendingReviews(){
        List<DiningReview> pendingReviewsAvailable = diningReviewRepository.findAllByStatus(ReviewStatus.Pending);

        if(pendingReviewsAvailable.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pending reviews available.");

        return pendingReviewsAvailable;
    }

    @GetMapping("/pending_reviews/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public DiningReview getPendingReview(@PathVariable("id") Long id){
        Optional<DiningReview> pendingReview = diningReviewRepository.findById(id);

        if(!pendingReview.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pending review with id " + id + " available.");

        return pendingReview.get();
    }

    @PutMapping("/pending_reviews/update_review_status")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant updateReviewStatus(@RequestParam Long id, @RequestParam AdminReviewAction action){
        DiningReview updatedReview;
        Restaurant updatedRestaurant = restaurantRepository.findById(id).get();
        Optional <DiningReview> reviewExists = diningReviewRepository.findById(id);

        if(!reviewExists.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Reivew with id " + id + " not found");
        if (action == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review action is required.");
        updatedReview = reviewExists.get();

        if (action.getAcceptReview()) {
            updatedReview.setStatus(ReviewStatus.Accepted);
            diningReviewRepository.save(updatedReview);
            updatedRestaurant = this.computeAvgscores(updatedReview);
        } else {
            updatedReview.setStatus(ReviewStatus.Rejected);
            diningReviewRepository.save(updatedReview);
        }

        return updatedRestaurant;

    }

    private Restaurant computeAvgscores(DiningReview updatedReview){
         List<DiningReview>  allApprovedReviews = diningReviewRepository.findAllByRestaurantIdAndStatus(updatedReview.getRestaurantId(), ReviewStatus.Accepted);
         IntSummaryStatistics dairyAllergyScores = allApprovedReviews.stream().filter(a -> a.getPeanutAllergyScore().isPresent()).map(s -> s.getDairyAllergyScore().get()).mapToInt(Integer::intValue).summaryStatistics();
         IntSummaryStatistics eggAllergyScores = allApprovedReviews.stream().filter(a -> a.getEggAllergyScore().isPresent()).map(s -> s.getEggAllergyScore().get()).mapToInt(Integer::intValue).summaryStatistics();
         IntSummaryStatistics peanutAllergyScores = allApprovedReviews.stream().filter(a -> a.getPeanutAllergyScore().isPresent()).map(s -> s.getPeanutAllergyScore().get()).mapToInt(Integer::intValue).summaryStatistics();

         Double avgDairyScore = (double) dairyAllergyScores.getSum() / dairyAllergyScores.getCount();
         Double avgEggScore = (double) eggAllergyScores.getSum() / eggAllergyScores.getCount();
         Double avgPeanutScore = (double) peanutAllergyScores.getSum() / peanutAllergyScores.getCount();
         double overallScore = (avgDairyScore + avgEggScore + avgPeanutScore) / 3;

         Restaurant updatedRestaurant = restaurantRepository.findById(updatedReview.getRestaurantId()).get();
         updatedRestaurant.setDairyAllergyScore(avgDairyScore);
         updatedRestaurant.setEggAllergyScore(avgEggScore);
         updatedRestaurant.setPeanutAllergyScore(avgPeanutScore);
         updatedRestaurant.setOverallScore(overallScore);

         return restaurantRepository.save(updatedRestaurant);


    }
}
