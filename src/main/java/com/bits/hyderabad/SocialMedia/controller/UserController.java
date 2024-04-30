package com.bits.hyderabad.SocialMedia.controller;

import com.bits.hyderabad.SocialMedia.model.DetailsUserResponse;
import com.bits.hyderabad.SocialMedia.model.User;
import com.bits.hyderabad.SocialMedia.model.dto.LoginRequest;
import com.bits.hyderabad.SocialMedia.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/users")
    public List<DetailsUserResponse> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<DetailsUserResponse> userResponseDTOs = new ArrayList<>();

        for (User user : users) {
            DetailsUserResponse responseDTO = new DetailsUserResponse();
            responseDTO.setName(user.getName());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setUserID(user.getId());
            userResponseDTOs.add(responseDTO);
        }
        return userResponseDTOs;
    }


    @GetMapping("/user")
    public DetailsUserResponse getUserDetails(@RequestParam Long userID) {
        return userService.getUserDetails(userID);
    }
}
