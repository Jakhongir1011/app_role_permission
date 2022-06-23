package com.example.appnewssite.service;

import com.example.appnewssite.entity.User;
import com.example.appnewssite.exception.ResourceNotFoundException;
import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.RegisterDto;
import com.example.appnewssite.repository.RoleRepository;
import com.example.appnewssite.repository.UserRepository;
import com.example.appnewssite.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public ApiResponse registerService(RegisterDto registerDto){

        if(!registerDto.getPassword().equals(registerDto.getPrePassword())){
            return new ApiResponse("password or prePassword error",false);
        }

        boolean username = userRepository.existsByUsername(registerDto.getUserName());
        if(username){
            return new ApiResponse("username is available",false);
        }

        User user = new User(
                registerDto.getFullName(),
                registerDto.getUserName(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(AppConstants.USER).
                        orElseThrow(()->new ResourceNotFoundException("role","user",AppConstants.USER)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("success",true);
    }

    public UserDetails loadUserByUsername(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(()->new ResourceNotFoundException(userName));
    }

}
