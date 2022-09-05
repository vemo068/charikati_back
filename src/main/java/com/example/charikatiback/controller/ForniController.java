package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Buy;
import com.example.charikatiback.entity.Forni;
import com.example.charikatiback.repository.BuyRepository;
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
    @Autowired
    private BuyRepository buyRepository;

    @RequestMapping("fornis")
    public List<Forni> getAllForni(){
        return forniRepository.findByIsDeleted(false);
    }

    @PostMapping("addforni")
    public ResponseEntity<Forni> postForni(@RequestBody Forni forni) throws URISyntaxException {
        Forni newForni=Forni.builder()
                .forniId(forni.getForniId())
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

       Forni forni=forniRepository.findByForniId(forniId);
        for (Buy buy : buyRepository.findByForniAndIsDeleted(forni,false)) {
           buy.setDeleted(true);
           buyRepository.save(buy);


        }
       forni.setDeleted(true);
       forniRepository.save(forni);


    }

    @RequestMapping(value = "forni",method = RequestMethod.GET)
    public @ResponseBody
    Forni getForni(@RequestParam("id") Long forniId){
        Forni forni=forniRepository.findByForniId(forniId);
        return forni;
    }


}
