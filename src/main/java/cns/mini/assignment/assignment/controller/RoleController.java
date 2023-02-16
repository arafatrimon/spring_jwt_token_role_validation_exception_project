package cns.mini.assignment.assignment.controller;

import cns.mini.assignment.assignment.entity.Role;
import cns.mini.assignment.assignment.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;
    @PostMapping("/createNewRole")
    public Role createNewRole(@RequestBody Role role){
        return  roleRepository.save(role);
    }
}
