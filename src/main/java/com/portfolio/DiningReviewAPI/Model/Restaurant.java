package com.portfolio.DiningReviewAPI.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

//the following annotations generate Setter and Getter methods for each field
@Setter
@Getter

// this generates a constructor that takes no arguments
@NoArgsConstructor
//this class basically represents a table called "restaurant". each field is a column
@Entity
@Table(name="Restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // disables Setter method generation for this field since the value is auto-generated
    private Long id;

    private String name;
    private String address;
    private Double peanutAllergyScore;
    private Double eggAllergyScore;
    private Double dairyAllergyScore;
    private Double overallScore;

    
}
