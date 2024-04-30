package com.bits.hyderabad.SocialMedia.service;

import com.bits.hyderabad.SocialMedia.model.Comment;
import com.bits.hyderabad.SocialMedia.model.Post;
import com.bits.hyderabad.SocialMedia.model.User;
import com.bits.hyderabad.SocialMedia.model.dto.CommentDTO;
import com.bits.hyderabad.SocialMedia.repository.CommentRepository;
import com.bits.hyderabad.SocialMedia.repository.PostRepository;
import com.bits.hyderabad.SocialMedia.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public String createComment(CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getUserID())
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        Post post = postRepository.findById(commentDTO.getPostID())
                .orElseThrow(() -> new RuntimeException("Post does not exist"));

        Comment comment = new Comment();
        comment.setCommentCreator(user);
        comment.setPost(post);
        comment.setCommentBody(commentDTO.getCommentBody());
        comment.setDate(LocalDate.now());

        commentRepository.save(comment);
        return "Comment created successfully";
    }

    public String editComment(Long commentID, String commentBody) {
        Comment comment = commentRepository.findById(commentID)
                .orElseThrow(() -> new RuntimeException("Comment does not exist"));
        comment.setCommentBody(commentBody);
        commentRepository.save(comment);
        return "Comment edited successfully";
    }

    @Transactional
    public String deleteComment(Long commentID) {
        commentRepository.findById(commentID)
                .orElseThrow(() -> new RuntimeException("Comment does not exist"));

        commentRepository.deleteById(commentID);
        return "Comment deleted";
    }


    @Transactional
    public void deleteByPost(Post post) {
        commentRepository.deleteByPost(post);
    }

    public Comment getComment(Long commentID) {
        return commentRepository.findById(commentID).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment does not exist"));
    }
}
