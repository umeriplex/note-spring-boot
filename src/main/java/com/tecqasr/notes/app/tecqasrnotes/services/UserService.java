package com.tecqasr.notes.app.tecqasrnotes.services;

import com.tecqasr.notes.app.tecqasrnotes.payloads.UserDto;
import com.tecqasr.notes.app.tecqasrnotes.utils.responses.AuthenticationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    UserDto signUp(UserDto userDto) throws IOException;

    UserDto getUserByEmail(String email);
}
