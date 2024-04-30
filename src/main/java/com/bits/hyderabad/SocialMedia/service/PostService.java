package com.bits.hyderabad.SocialMedia.service;

import com.bits.hyderabad.SocialMedia.model.Post;
import com.bits.hyderabad.SocialMedia.model.User;
import com.bits.hyderabad.SocialMedia.model.dto.PostDTO;
import com.bits.hyderabad.SocialMedia.repository.CommentRepository;
import com.bits.hyderabad.SocialMedia.repository.PostRepository;
import com.bits.hyderabad.SocialMedia.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    public PostService(PostRepository postRepository,UserRepository userRepository, CommentRepository commentRepository, CommentService commentService) {
        this.postRepository = postRepository;
        this.userRepository=userRepository;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }

    public String createPost(PostDTO postDTO) {
        User user = userRepository.findById(postDTO.getUserID()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist"));

        Post post = new Post();
        post.setPostBody(postDTO.getPostBody());
        post.setUser(user);
        post.setDate(LocalDate.now());
        postRepository.save(post);

        return "Post created successfully";
    }

    public Post getPostDetails(Long postID) {
        return postRepository.findById(postID).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exist"));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByDateDesc();
    }

    public String editPost(Long postId, String postBody) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setPostBody(postBody);
            postRepository.save(post);
            return "Post edited successfully";
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exist");
        }
    }

    public String deletePost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            commentService.deleteByPost(post);
            postRepository.delete(post);
            return "Post deleted";
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exist");
        }
    }

}
