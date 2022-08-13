package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Client;
import com.example.charikatiback.entity.OrderSell;
import com.example.charikatiback.entity.Sell;
import com.example.charikatiback.repository.ClientRepository;
import com.example.charikatiback.repository.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
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
        return sellRepository.findByClientAndIsDeleted(client,false);
    }

    @PostMapping("addsell")
    public ResponseEntity<Sell> postProduct(@RequestBody Sell sell) throws URISyntaxException {
        Sell newSell=Sell.builder()
                .sellId(sell.getSellId())
                .client(sell.getClient())
                .date(sell.getDate())
                .total(sell.getTotal())
                .build();

        newSell=sellRepository.save(newSell);
        if (newSell == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newSell.getSellId())
                    .toUri();
            return ResponseEntity.created(uri).body(newSell);
        }

    }

    @RequestMapping(value = "sell",method = RequestMethod.GET)
    public @ResponseBody
    Sell getSell(@RequestParam("id") Long sellId){
        Sell sell=sellRepository.findBySellId(sellId);
        return sell;
    }


    @RequestMapping(value="deletesell", method = {RequestMethod.GET, RequestMethod.DELETE})
    public @ResponseBody
    void deleteSell(@RequestParam("id") Long sellId){

      Sell sell=  sellRepository.findBySellId(sellId);
      sell.setDeleted(true);
        sellRepository.save(sell);

    }
}
