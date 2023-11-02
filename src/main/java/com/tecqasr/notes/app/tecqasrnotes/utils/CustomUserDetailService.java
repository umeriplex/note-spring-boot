package com.tecqasr.notes.app.tecqasrnotes.utils;

import com.tecqasr.notes.app.tecqasrnotes.entities.User;
import com.tecqasr.notes.app.tecqasrnotes.exceptions.ResourceNotFoundException;
import com.tecqasr.notes.app.tecqasrnotes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username).stream().findFirst().orElseThrow(()-> new ResourceNotFoundException("User not found", "User with email "+username+" not found",404));
        return user;

    }
}
