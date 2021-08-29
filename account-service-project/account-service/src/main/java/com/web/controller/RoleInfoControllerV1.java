package com.web.controller;

import com.web.model.Role;
import com.web.repository.RoleInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class RoleInfoControllerV1 {

    private final RoleInfoRepository roleRepository;

    @GetMapping("/roles")
    public Set<Role> getRoles(){
        return null;
    }

    @PostMapping("/role/add")
    public void addRole(@RequestBody Role role){
        roleRepository.addRole(role);
    }
}
