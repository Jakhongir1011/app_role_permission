package com.example.appnewssite.service;

import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.RoleDto;
import com.example.appnewssite.repository.RoleRepository;
import com.example.appnewssite.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addRoleService(RoleDto roleDto) {
        boolean existsByName = roleRepository.existsByName(roleDto.getName());
        if (existsByName)
            return new ApiResponse("",false);
        Role role = new Role(
                roleDto.getName(),
                roleDto.getPermission(),
                roleDto.getDescription()
        );
        roleRepository.save(role);
        return new ApiResponse("Saved",true);
    }
}
