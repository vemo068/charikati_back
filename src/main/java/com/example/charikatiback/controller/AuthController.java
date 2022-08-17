package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Sell;
import com.example.charikatiback.entity.User;
import com.example.charikatiback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User getCurrentUser(@RequestBody User loginInfo) throws URISyntaxException {

        User currentUser=userRepository.findByNameAndPassword(loginInfo.getName(), loginInfo.getPassword());


        return currentUser;

    }
    @PostMapping("adduser")
    public ResponseEntity<User> postProduct(@RequestBody User user) throws URISyntaxException {
        User newUser=User.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .password(user.getPassword())
                .build();

        newUser=userRepository.save(newUser);
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
