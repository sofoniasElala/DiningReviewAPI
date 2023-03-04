package com.portfolio.DiningReviewAPI.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.Optional;

//the following annotations generate Setter and Getter methods for each field
@Setter
@Getter

//this generates a constructor that takes no arguments
@NoArgsConstructor

@Entity
@Table(name = "dining_review")
public class DiningReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;
    private Long restaurantId;
    private Optional<Integer> peanutAllergyScore;
    private Optional<Integer> dairyAllergyScore;
    private Optional<Integer> eggAllergyScore;
    private String comment;
}
