package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Forni;
import com.example.charikatiback.repository.ForniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping
public class ForniController {
    @Autowired
    private ForniRepository forniRepository;

    @RequestMapping("fornis")
    public List<Forni> getAllForni(){
        return forniRepository.findAll();
    }

    @PostMapping("addforni")
    public ResponseEntity<Forni> postForni(@RequestBody Forni forni) throws URISyntaxException {
        Forni newForni=Forni.builder()
                .name(forni.getName())
                .phone(forni.getPhone())

                .build();

        newForni=forniRepository.save(newForni);
        if (newForni == null) {
            return null;
        }
        else{
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newForni.getForniId())
                    .toUri();
            return ResponseEntity.created(uri).body(newForni);
        }

    }


    @RequestMapping(value="deleteforni", method = {RequestMethod.GET, RequestMethod.DELETE})
    public @ResponseBody
    void deleteForni(@RequestParam("id") Long forniId){

        forniRepository.deleteById(forniId);


    }


}
