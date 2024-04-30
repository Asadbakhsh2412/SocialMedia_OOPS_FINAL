package com.bits.hyderabad.SocialMedia.repository;

import com.bits.hyderabad.SocialMedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
