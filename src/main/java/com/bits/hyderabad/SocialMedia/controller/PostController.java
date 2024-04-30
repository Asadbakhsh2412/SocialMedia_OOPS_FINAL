package com.bits.hyderabad.SocialMedia.controller;

import com.bits.hyderabad.SocialMedia.model.Comment;
import com.bits.hyderabad.SocialMedia.model.Post;
import com.bits.hyderabad.SocialMedia.model.dto.*;
import com.bits.hyderabad.SocialMedia.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public String createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @GetMapping("/")
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostDTO> postDTOs = new ArrayList<>();

        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setUserID(post.getUser().getId());
            postDTO.setPostID(post.getId());
            postDTO.setPostBody(post.getPostBody());
            postDTO.setDate(post.getDate());

            List<CommentResponseDTO> commentDTOs = new ArrayList<>();
            for (Comment comment : post.getComments()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUserID(comment.getCommentCreator().getId());
                userDTO.setName(comment.getCommentCreator().getName());

                CommentResponseDTO commentDTO = new CommentResponseDTO();
                commentDTO.setCommentID(comment.getId());
                commentDTO.setCommentBody(comment.getCommentBody());
                commentDTO.setCommentCreator(userDTO);
                commentDTOs.add(commentDTO);
            }
            postDTO.setComments(commentDTOs);
            postDTOs.add(postDTO);
        }
        return postDTOs;
    }

    @GetMapping("/post")
    public ResponseEntity<?> getPostDetails(@RequestParam Long postID) {
        Post post = postService.getPostDetails(postID);

        PostDTO postDTO = new PostDTO();
        postDTO.setUserID(post.getUser().getId());
        postDTO.setPostID(post.getId());
        postDTO.setPostBody(post.getPostBody());
        postDTO.setDate(post.getDate());

        List<CommentResponseDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserID(comment.getCommentCreator().getId());
            userDTO.setName(comment.getCommentCreator().getName());

            CommentResponseDTO commentDTO = new CommentResponseDTO();
            commentDTO.setCommentID(comment.getId());
            commentDTO.setCommentBody(comment.getCommentBody());
            commentDTO.setCommentCreator(userDTO);
            commentDTOs.add(commentDTO);
        }
        postDTO.setComments(commentDTOs);

        return ResponseEntity.ok().body(postDTO);
    }

    @PatchMapping("/post")
    public String editPost(@RequestBody EditPostRequest editPostRequest) {
        return postService.editPost(editPostRequest.getPostID(), editPostRequest.getPostBody());
    }

    @DeleteMapping("/post")
    public String deletePost(@RequestParam Long postID) {
        return postService.deletePost(postID);
    }

}
