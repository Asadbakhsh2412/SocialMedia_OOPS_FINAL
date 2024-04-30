package com.bits.hyderabad.SocialMedia.service;

import com.bits.hyderabad.SocialMedia.model.DetailsUserResponse;
import com.bits.hyderabad.SocialMedia.model.User;
import com.bits.hyderabad.SocialMedia.model.dto.ResponseDTO;
import com.bits.hyderabad.SocialMedia.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String loginUser(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist"));

        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username/Password Incorrect");
        }
        return"Login Successful";
    }

    public String registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        userRepository.save(user);
        return "Account created successfully";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public DetailsUserResponse getUserDetails(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist"));
        DetailsUserResponse detailsUserResponse = new DetailsUserResponse();
        detailsUserResponse.setUserID(user.getId());
        detailsUserResponse.setEmail(user.getEmail());
        detailsUserResponse.setName(user.getName());
        return detailsUserResponse;
    }
}
