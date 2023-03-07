package com.portfolio.DiningReviewAPI.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;


//the following annotations generate Setter and Getter methods for each field
@Setter
@Getter

// this generates a constructor that takes in required arguments
@RequiredArgsConstructor
//this class basically represents a table called "Dining_review". each field is a column
@Entity
@Table(name = "Dining_review")
public class DiningReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)// disables Setter method generation for this field since the value is auto-generated
    private Long id;

    private String userName;
    private Long restaurantId;

    @Column(columnDefinition = "integer default null")
    private Integer peanutAllergyScore;
    @Column(columnDefinition = "integer default null")
    private Integer dairyAllergyScore;
    @Column(columnDefinition = "integer default null")
    private Integer eggAllergyScore;
    private String comment;
    private ReviewStatus status;
}
