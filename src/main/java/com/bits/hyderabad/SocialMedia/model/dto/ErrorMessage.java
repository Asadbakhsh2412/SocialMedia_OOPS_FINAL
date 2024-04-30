package com.bits.hyderabad.SocialMedia.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {

    @JsonProperty("Error")
    private String Error;

    public ErrorMessage(String Error) {
        this.Error = Error;
    }

}
