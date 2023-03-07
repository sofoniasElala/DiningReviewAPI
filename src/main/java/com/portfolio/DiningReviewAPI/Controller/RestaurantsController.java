package com.portfolio.DiningReviewAPI.Controller;

import com.portfolio.DiningReviewAPI.Model.*;
import com.portfolio.DiningReviewAPI.Repository.*;
import java.util.Optional;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/restaurants")
public class RestaurantsController {
    private final RestaurantRepository restaurantRepository;

    public RestaurantsController(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Restaurant getRestaurant(@PathVariable("id") Long id){
        Optional<Restaurant> restaurantExists = restaurantRepository.findById(id);

        if(!restaurantExists.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant with id " + id + "does not exist.");

        Restaurant  restaurant = restaurantExists.get();

        restaurant.setDairyAllergyScore(Double.parseDouble(String.format("%.2f", restaurant.getDairyAllergyScore())));
        restaurant.setEggAllergyScore(Double.parseDouble(String.format("%.2f", restaurant.getEggAllergyScore())));
        restaurant.setPeanutAllergyScore(Double.parseDouble(String.format("%.2f", restaurant.getPeanutAllergyScore())));

        return restaurant;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant){
        Optional<Restaurant> restaurantExists = restaurantRepository.findByNameAndZip(restaurant.getName(), restaurant.getZip());

        if(restaurantExists.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant already exists.");

        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Restaurant> searchRestaurants(@RequestParam String zip, String allergy){
        List<Restaurant> restaurants;
        switch(allergy.toLowerCase()){
            case "egg":
                restaurants = restaurantRepository.findAllByZipAndEggAllergyScoreIsNotNull(zip, Sort.by("eggAllergyScore").descending());
                break;
            case "dairy":
                restaurants  = restaurantRepository.findAllByZipAndDairyAllergyScoreIsNotNull(zip, Sort.by("dairyAllergyScore").descending());
                break;
            case "peanut":
                restaurants  =  restaurantRepository.findAllByZipAndPeanutAllergyScoreIsNotNull(zip, Sort.by("peanutAllergyScore").descending());
                break;
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Allergy info unavailable");
        }

        if(restaurants.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No restaurants found");

        return restaurants;
    }
}
