package com.bits.hyderabad.SocialMedia.model.dto;

public class CommentDTO {

    private Long commentID;

    private Long userID;
    private Long postID;
    private String commentBody;

    private UserDTO commentCreator;

    public CommentDTO() {
    }

    public CommentDTO(Long commentID, String commentBody, UserDTO commentCreator) {
        this.commentID = commentID;
        this.commentBody = commentBody;
        this.commentCreator = commentCreator;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
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

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }
}
