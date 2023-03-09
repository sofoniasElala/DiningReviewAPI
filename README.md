# DiningReviewAPI

This repo contains a Java Spring Boot API that serves as a backend for a  mock restaurant reviewing website for customers with allergies to egg, dairy, and peanut. The API allows reviewers to create/update a profile, submit reviews for existing restuarants. then the admins can accept/reject those pending reviews..etc. check the API end points seciton for more. The app utilizes Spring Boot, Spring Data JPA, and H2 for database management. This project was developed as a demonstration of my skills in Java and Spring Boot development. 


## Technologies Used

- JAVA/Spring Boot
- Hibernate/H2
- Lombok

## Getting Started

- Clone the repository
- Run `mvn clean install` to install the necessary dependencies
- Start the application with `mvn spring-boot:run`
- The API will be available at `http://localhost:3001`

### API Endpoints

Users
- GET `/users` - returns a list of all users in the system
- POST `/users/create_user` - creates a new user in the system
- GET `/users/{userName}` - returns information about a specific user
- PUT `/users/update_user_profile` - updates information about a specific user

Restaurants
- GET `/Restaurants` - returns a list of all restaurants in the system
- POST `/Restaurants/add` - add a new restaurant in the system
- GET `/Restaurants/{id}` - returns information about a specific restaurant
- GET `/Restaurants/search` - search for restaurants by zip code and allergy(Have allergy rating)

DiningReviews
- POST `/dining_reviews/submit` - submit a review.  
- GET `/dining_reviews/{id}` - returns information about a specific review

### API Endpoints - examples
- The API endpoint `/users/create_user` accepts HTTP POST requests in the following format:
    - `curl -X POST -H "Content-Type: application/json" -d "{\"userName\": \"John Doe\", \"city\": \"charlotte\", \"state\": \"NC\",\"zip\": \"20000\", \"peanutAllergy\": true,\"dairyAllergy\": false,\"eggAllergy\": false}" http://localhost:3001/users/create_user`
- The API endpoint `/users/{userName}` accepts HTTP GET requests in the following format:
    - `curl "http://localhost:3001/users/John%20Doe"`
- The API endpoint `/dining_reviews/submit` accepts HTTP POST requests in the following format:
    - `curl -X POST http://localhost:3001/dining_reviews/submit  -d "{\"userName\":\"John Doe\",\"restaurantId\": 1,\"eggAllergyScore\":3.0, \"comment\": \"Good\"}" -H "Content-Type: application/json"`



---



