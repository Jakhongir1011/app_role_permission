package com.example.appnewssite.controller;

import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.RoleDto;
import com.example.appnewssite.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize("hasAuthority('ADD_USER')")
    @PostMapping("/role")
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDto roleDto){
        ApiResponse apiResponse = roleService.addRoleService(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }




}
