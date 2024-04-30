package com.bits.hyderabad.SocialMedia.model.dto;

public class UserDTO {
    private Long userID;
    private String name;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
