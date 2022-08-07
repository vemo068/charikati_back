package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Buy;
import com.example.charikatiback.entity.Client;
import com.example.charikatiback.entity.Forni;
import com.example.charikatiback.entity.Sell;
import com.example.charikatiback.repository.BuyRepository;
import com.example.charikatiback.repository.ClientRepository;
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
public class BuyController {
    @Autowired
    private BuyRepository buyRepository;
    @Autowired
    private ForniRepository forniRepository;

    @RequestMapping(value = "buys",method = RequestMethod.GET)
    public @ResponseBody
    List<Buy> getBuys(@RequestParam("id") Long ForniId){
        Forni forni=forniRepository.findByForniId(ForniId);
        return buyRepository.findByForni(forni);
    }

    @PostMapping("addbuy")
    public ResponseEntity<Buy> postBuy(@RequestBody Buy buy) throws URISyntaxException {
        Buy newBuy=Buy.builder()
                .buyId(buy.getBuyId())
                .forni(buy.getForni())
                .date(buy.getDate())
                .total(buy.getTotal())
                .build();

        newBuy=buyRepository.save(newBuy);
        if (newBuy == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newBuy.getBuyId())
                    .toUri();
            return ResponseEntity.created(uri).body(newBuy);
        }

    }

    @RequestMapping(value = "buy",method = RequestMethod.GET)
    public @ResponseBody
    Buy getBuy(@RequestParam("id") Long buyId){
        Buy buy=buyRepository.findByBuyId(buyId);
        return buy;
    }

    @RequestMapping(value="deletebuy", method = {RequestMethod.GET, RequestMethod.DELETE})
    public @ResponseBody
    void deleteBuy(@RequestParam("id") Long buyId){

        buyRepository.deleteById(buyId);


    }
}
