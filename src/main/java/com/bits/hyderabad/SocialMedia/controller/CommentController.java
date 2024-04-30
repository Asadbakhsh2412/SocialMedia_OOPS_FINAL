package com.bits.hyderabad.SocialMedia.controller;

import com.bits.hyderabad.SocialMedia.model.Comment;
import com.bits.hyderabad.SocialMedia.model.CommentCreator;
import com.bits.hyderabad.SocialMedia.model.ResponseComment;
import com.bits.hyderabad.SocialMedia.model.dto.CommentDTO;
import com.bits.hyderabad.SocialMedia.model.dto.EditCommentRequest;
import com.bits.hyderabad.SocialMedia.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public String createComment(@RequestBody CommentDTO commentDTO) {
        return commentService.createComment(commentDTO);
    }

    @GetMapping("/comment")
    public ResponseEntity<?> getComment (@RequestParam Long commentID){
        Comment comment = commentService.getComment(commentID);

        ResponseComment commentResponse = new ResponseComment();
        CommentCreator commentCreator = new CommentCreator();

        commentCreator.setUserID(comment.getCommentCreator().getId());
        commentCreator.setName(comment.getCommentCreator().getName());
        commentResponse.setCommentCreator(commentCreator);
        commentResponse.setCommentID(comment.getId());
        commentResponse.setCommentBody(comment.getCommentBody());

        return ResponseEntity.ok(commentResponse);

    }


    @PatchMapping("/comment")
    public String editComment(@RequestBody EditCommentRequest editCommentRequest) {
        return commentService.editComment(editCommentRequest.getCommentID(), editCommentRequest.getCommentBody());
    }


    @DeleteMapping("/comment")
    public String deleteComment(@RequestParam Long commentID) {
        return commentService.deleteComment(commentID);
    }
}
