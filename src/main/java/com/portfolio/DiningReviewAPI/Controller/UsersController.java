package com.portfolio.DiningReviewAPI.Controller;

import org.springframework.web.bind.annotation.*;
import com.portfolio.DiningReviewAPI.Model.*;
import com.portfolio.DiningReviewAPI.Repository.*;
import java.util.Optional;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.util.ObjectUtils;


import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UserRepository userRepository;

    public UsersController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Iterable<User> getUsers(){
        Iterable<User> usersExists = userRepository.findAll();

        if(!usersExists.iterator().hasNext()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No users found.");

        return usersExists;

    }

    @GetMapping("/{userName}")
    @ResponseStatus(HttpStatus.FOUND)
    public User getUser(@PathVariable String userName){
        Optional<User> userExists = userRepository.findByUserNameIgnoreCase(userName);

        if(!userExists.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found.");

        return userExists.get();

    }

    @PostMapping("/create_user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        Optional<User> userExistsAlready= userRepository.findByUserNameIgnoreCase(user.getUserName());

        if(ObjectUtils.isEmpty(user.getUserName())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User name is required.");
        if(userExistsAlready.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User name taken.");

        User newUser = userRepository.save(user);

        return newUser;
    }

    @PutMapping("/update_user_profile")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUserProfile(@RequestBody User user){
        User updatedUser;
        Optional<User> userExists = userRepository.findByUserNameIgnoreCase(user.getUserName());
        if(!userExists.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found.");

        updatedUser = userExists.get();

        updatedUser.setState(user.getState());
        updatedUser.setCity(user.getCity());
        updatedUser.setZip(user.getZip());
        updatedUser.setDairyAllergy(user.getDairyAllergy());
        updatedUser.setEggAllergy(user.getEggAllergy());
        updatedUser.setPeanutAllergy(user.getPeanutAllergy());

        return updatedUser;
    }
    
}
