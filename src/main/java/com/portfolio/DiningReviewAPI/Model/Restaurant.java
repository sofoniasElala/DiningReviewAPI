package com.portfolio.DiningReviewAPI.Model;

import jakarta.persistence.*; 
import lombok.Getter;
import lombok.Setter;

//the following annotations generate Setter and Getter methods for each field
@Setter
@Getter

//this class basically represents a table called "restaurant". each field is a column
@Entity
@Table(name="RESTAURANT")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Double peanutAllergyScore;
    private Double eggAllergyScore;
    private Double dairyAllergyScore;
    private Double overallScore;

    
}
