package com.bits.hyderabad.SocialMedia.repository;

import com.bits.hyderabad.SocialMedia.model.Comment;
import com.bits.hyderabad.SocialMedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteByPost(Post post);
}
