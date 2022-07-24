package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Forni;
import com.example.charikatiback.repository.ForniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping
public class ForniController {
    @Autowired
    private ForniRepository forniRepository;

    @RequestMapping("forni")
    public List<Forni> getAllForni(){
        return forniRepository.findAll();
    }

    @PostMapping("addForni")
    public Forni postForni(@RequestBody Forni forni) throws URISyntaxException {
        Forni newForni=Forni.builder()
                .name(forni.getName())
                .phone(forni.getPhone())

                .build();

        newForni=forniRepository.save(newForni);
        if (newForni == null) {
            return null;
        }
        else{
            return newForni;
        }

    }

}
