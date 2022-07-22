package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Client;
import com.example.charikatiback.entity.Sell;
import com.example.charikatiback.repository.ClientRepository;
import com.example.charikatiback.repository.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SellController {
    @Autowired
    private SellRepository sellRepository;
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value = "sells",method = RequestMethod.GET)
    public @ResponseBody
    List<Sell> getSells(@RequestParam("id") Long clientId){
        Client client=clientRepository.findByClientId(clientId);
        return sellRepository.findByClient(client);
    }

}
