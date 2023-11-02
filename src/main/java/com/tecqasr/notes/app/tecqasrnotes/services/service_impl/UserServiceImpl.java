package com.tecqasr.notes.app.tecqasrnotes.services.service_impl;

import com.tecqasr.notes.app.tecqasrnotes.entities.User;
import com.tecqasr.notes.app.tecqasrnotes.exceptions.ResourceNotFoundException;
import com.tecqasr.notes.app.tecqasrnotes.payloads.UserDto;
import com.tecqasr.notes.app.tecqasrnotes.repositories.UserRepository;
import com.tecqasr.notes.app.tecqasrnotes.services.FileService;
import com.tecqasr.notes.app.tecqasrnotes.services.UserService;
import com.tecqasr.notes.app.tecqasrnotes.utils.responses.AuthenticationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @Autowired
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDto signUp(UserDto userDto) throws IOException {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User","email",1));
        UserDto newUserDto = new UserDto();
        newUserDto.setId(user.getId());
        newUserDto.setEmail(user.getEmail());
        newUserDto.setName(user.getName());
        newUserDto.setImage(user.getImage());
        newUserDto.setPassword(user.getPassword());
        return newUserDto;
    }

}
