package com.bits.hyderabad.SocialMedia.repository;

import com.bits.hyderabad.SocialMedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByDateDesc();

}
