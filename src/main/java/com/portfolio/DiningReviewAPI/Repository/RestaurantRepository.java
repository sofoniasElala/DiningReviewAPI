package com.portfolio.DiningReviewAPI.Repository;

import org.springframework.data.repository.CrudRepository;
import com.portfolio.DiningReviewAPI.Model.Restaurant;
import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Sort;



//CRUD repository for Entity: Restaurant
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    Optional<Restaurant> findByNameAndZip(String name, String zip);
    List<Restaurant> findAllByZipAndPeanutAllergyScoreIsNotNull(String zip, Sort sort);
    List<Restaurant> findAllByZipAndDairyAllergyScoreIsNotNull(String zip, Sort sort);
    List<Restaurant> findAllByZipAndEggAllergyScoreIsNotNull(String zip, Sort sort);
}
