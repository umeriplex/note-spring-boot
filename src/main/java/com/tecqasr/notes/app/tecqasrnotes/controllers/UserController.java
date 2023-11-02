package com.tecqasr.notes.app.tecqasrnotes.controllers;

import com.tecqasr.notes.app.tecqasrnotes.payloads.UserDto;
import com.tecqasr.notes.app.tecqasrnotes.services.FileService;
import com.tecqasr.notes.app.tecqasrnotes.services.UserService;
import com.tecqasr.notes.app.tecqasrnotes.utils.JwtUtil;
import com.tecqasr.notes.app.tecqasrnotes.utils.requests.LoginRequest;
import com.tecqasr.notes.app.tecqasrnotes.utils.responses.AuthenticationResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;


    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestParam("image") MultipartFile image,
                                          @RequestParam("email") String email,
                                          @RequestParam("password") String password,
                                          @RequestParam("name") String name) throws IOException {
        String imagePath = fileService.updateImage(path, image);
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setName(name);
        userDto.setImage(imagePath);

        UserDto user = userService.signUp(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) throws Exception {
        this.authenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwtUtil.generateToken(userDetails.getUsername());
        UserDto user = userService.getUserByEmail(request.getEmail());
        AuthenticationResponse response = new AuthenticationResponse(token, user);
        return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {
        try{
            System.out.println(username + " 4 " + password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        }catch (BadCredentialsException e){
            System.out.println("5 "+e.getMessage());
            throw new BadCredentialsException("Incorrect username or password");
        }catch (DisabledException e){
            System.out.println("6 "+e.getMessage());
            throw new DisabledException("User is not found");
        }
    }



}
