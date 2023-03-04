package com.portfolio.DiningReviewAPI.Model;

import jakarta.persistence.*; 

//this is basically a table. "restaurant" table. each field is a column
@Entity
@Table(name="RESTAURANT")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;
    private String name;
    private String address;
    private Double peanutAllergyScore;
    private Double eggAllergyScore;
    private Double dairyAllergyScore;
    private Double overallScore;

    //the following code are setters and getters for each field

    public void setRestaurantId(Long restaurantId){
        this.restaurantId = restaurantId;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPeanutAllergyScore(Double peanutAllergyScore){
        this.peanutAllergyScore = peanutAllergyScore;
    }
    public void setEggAllergyScore(Double eggAllergyScore){
        this.eggAllergyScore = eggAllergyScore;
    }
    public void setDairyAllergyScore(Double dairyAllergyScore){
        this.dairyAllergyScore = dairyAllergyScore;
    }
    public void setOverallScore(Double overallScore){
        this.overallScore = overallScore;
    }
     
    public Long getRestaurantId(){
        return this.restaurantId;
    }
    public String getName(){
        return this.name;
    }
    public String getAddress(){
        return this.address;
    }
    public Double getPeanutAllergyScore(){
        return this.peanutAllergyScore;
    }
    public Double getEggAllergyScore(){
        return this.eggAllergyScore;
    }
    public Double getDairyAllergyScore(){
        return this.dairyAllergyScore;
    }
    public Double getOverallScore(){
        return this.overallScore;
    }

    
}
