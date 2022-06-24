package com.example.appnewssite.controller;

import com.example.appnewssite.aop.CheckPermission;
import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.RoleDto;
import com.example.appnewssite.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class RoleController {

    @Autowired
    RoleService roleService;

//    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @CheckPermission(permission = "ADD_PERMISSION")
    @PostMapping("/role")
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDto roleDto){
        ApiResponse apiResponse = roleService.addRoleService(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

//    @PreAuthorize("hasAuthority('EDIT_ROLE')")
    @CheckPermission(permission = "EDIT_PERMISSION")
    @PutMapping("/role/{id}")
    public HttpEntity<?> editRole(@PathVariable Long id, @Valid @RequestBody RoleDto roleDto){
        ApiResponse apiResponse = roleService.editRoleService(roleDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    @DeleteMapping("/role/{id}")
    public HttpEntity<?> deleteRole(@PathVariable Long id){
        ApiResponse apiResponse = roleService.deleteRoleService(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202:407).body(id);
    }


}
