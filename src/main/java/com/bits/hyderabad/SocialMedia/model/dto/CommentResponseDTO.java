package com.bits.hyderabad.SocialMedia.model.dto;

public class CommentResponseDTO {
    private Long commentID;
    private String commentBody;
    private UserDTO commentCreator;

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public UserDTO getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(UserDTO commentCreator) {
        this.commentCreator = commentCreator;
    }
}
