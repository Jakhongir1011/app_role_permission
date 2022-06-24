package com.example.appnewssite.service;

import com.example.appnewssite.payload.ApiResponse;
import com.example.appnewssite.payload.RoleDto;
import com.example.appnewssite.repository.RoleRepository;
import com.example.appnewssite.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ApiResponse editRoleService(RoleDto roleDto, Long id) {
        Optional<Role> byId = roleRepository.findById(id);
        if (byId.isPresent()){
            Role role = byId.get();
            role.setName(roleDto.getName());
            role.setDescription(roleDto.getDescription());
            role.setPermission(roleDto.getPermission());
            roleRepository.save(role);
            return new ApiResponse( "edit role success",true);
        }
        return new ApiResponse("Role not found",false);
    }

    public ApiResponse deleteRoleService(Long id) {
        boolean b = roleRepository.existsById(id);
        if (!b){
            return new ApiResponse("role not found",false);
        }
        roleRepository.deleteById(id);
        return new ApiResponse("it's delete role success ",true);
    }


}
