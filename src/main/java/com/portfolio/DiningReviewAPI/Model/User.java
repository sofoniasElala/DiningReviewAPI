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
//this class basically represents a table called "User". each field is a column
@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String userName;

    private String city;
    private String state;
    private String zip;

    private Boolean peanutAllergy;
    private Boolean dairyAllergy;
    private Boolean eggAllergy;
}
