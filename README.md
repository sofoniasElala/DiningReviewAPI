# DiningReviewAPI

This is a Java Spring Boot API that serves as a backend for a restaurant reviewing website catering to customers with allergies to egg, dairy, and peanut. It allows reviewers to create/update their profile, submit reviews for existing restaurants, and enables admins to accept/reject pending reviews. The API end points section provides further details. The app uses Spring Boot, Spring Data JPA, and H2 for database management. It was developed as a demonstration of my Java and Spring Boot development skills.


## Technologies Used

- JAVA/Spring Boot
- Hibernate/H2 - (on App Engine where its currently running, it uses google Cloud Sql for MySql)
- Lombok

## Getting Started

- Clone the repository
- Run `mvn clean install` to install the necessary dependencies
- Start the application with `mvn spring-boot:run`
- The API will be available at `http://localhost:3001`

### API Endpoints

Users
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

Admin
- GET `/admin/pending_reviews`
- .....

### API Endpoints - examples
- The API endpoint `/users/create_user` accepts HTTP POST requests in the following format:
    - `curl -X POST -H "Content-Type: application/json" -d "{\"userName\": \"John Doe\", \"city\": \"charlotte\", \"state\": \"NC\",\"zip\": \"20000\", \"peanutAllergy\": true,\"dairyAllergy\": false,\"eggAllergy\": false}"  https://dining-review-dot-sofonias-apps.ue.r.appspot.com/users/create_user`
- The API endpoint `/users/{userName}` accepts HTTP GET requests in the following format:
    - `curl "https://dining-review-dot-sofonias-apps.ue.r.appspot.com/users/John%20Doe"`
- The API endpoint `/dining_reviews/submit` accepts HTTP POST requests in the following format:
    - `curl -X POST https://dining-review-dot-sofonias-apps.ue.r.appspot.com/dining_reviews/submit  -d "{\"userName\":\"John Doe\",\"restaurantId\": 1,\"eggAllergyScore\":3.0, \"comment\": \"Good\"}" -H "Content-Type: application/json"`



---



