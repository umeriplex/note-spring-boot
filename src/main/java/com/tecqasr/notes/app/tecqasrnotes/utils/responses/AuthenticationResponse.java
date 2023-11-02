package com.tecqasr.notes.app.tecqasrnotes.utils.responses;

import com.tecqasr.notes.app.tecqasrnotes.payloads.UserDto;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private UserDto user;
    private String token;

    public AuthenticationResponse(String jwt, UserDto userDto) {
        this.user = userDto;
        this.token = jwt;
    }
}
