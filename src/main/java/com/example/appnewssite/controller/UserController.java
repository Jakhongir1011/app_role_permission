package com.example.appnewssite.controller;

import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.UserDto;
import com.example.appnewssite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public HttpEntity<?> addUsers(@Valid  @RequestBody UserDto userDto){
       ApiResponse apiResponse = userService.addUser(userDto);
       return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
