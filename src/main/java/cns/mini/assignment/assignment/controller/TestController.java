package cns.mini.assignment.assignment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestController {

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('Admin')")
        ResponseEntity<String> forAdmin(){
            return ResponseEntity.status(200).body("Response form backend: Your are admin");
    }

    @GetMapping("/forUser")
    @PreAuthorize("hasRole('User')")
    ResponseEntity<String> forUser(){
        return ResponseEntity.status(200).body("Response from backend: Your are user");
    }

}
