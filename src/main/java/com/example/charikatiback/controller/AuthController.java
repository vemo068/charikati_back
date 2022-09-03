package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Buy;
import com.example.charikatiback.entity.User;
import com.example.charikatiback.repository.UseerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private UseerRepository useerRepository;
    @GetMapping("users")
    public List<User> getAllUsers(){
        return useerRepository.findAll();
    }
    @PostMapping("/login")
    public User getCurrentUser(@RequestBody User loginInfo) throws URISyntaxException {

        User currentUser= useerRepository.findByNameAndPassword(loginInfo.getName(), loginInfo.getPassword());


        return currentUser;

    }
    @PostMapping("adduser")
    public ResponseEntity<User> postProduct(@RequestBody User user) throws URISyntaxException {
        User newUser=User.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .password(user.getPassword())
                .build();

        newUser= useerRepository.save(newUser);
        if (newUser == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newUser.getUserId())
                    .toUri();
            return ResponseEntity.created(uri).body(newUser);
        }

    }
}
