package com.example.appnewssite.controller;


import com.example.appnewssite.entity.User;
import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.LoginDto;
import com.example.appnewssite.payload.RegisterDto;
import com.example.appnewssite.security.JwtProvider;
import com.example.appnewssite.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService registerService;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody RegisterDto registerDto){
        ApiResponse apiResponse = registerService.registerService(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 407).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( loginDto.getUserName(),loginDto.getPassword()));
        User user = (User) authenticate.getPrincipal();

        String token = JwtProvider.generateToken(user.getUsername(),user.getRole());
        return ResponseEntity.ok(token);
    }


}
