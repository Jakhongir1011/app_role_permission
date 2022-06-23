package com.example.appnewssite.service;

import com.example.appnewssite.entity.User;
import com.example.appnewssite.exception.ResourceNotFoundException;
import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.UserDto;
import com.example.appnewssite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ApiResponse addUser(UserDto userDto) {

        return null;
    }


}
