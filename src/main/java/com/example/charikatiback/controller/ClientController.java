package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Client;
import com.example.charikatiback.entity.Product;
import com.example.charikatiback.entity.Sell;
import com.example.charikatiback.repository.ClientRepository;
import com.example.charikatiback.repository.OrderSellRepository;
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
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SellRepository sellRepository;
    @Autowired
    private OrderSellRepository orderSellRepository;

    @GetMapping("clients")
    public List<Client> getAllProducts(){
        return clientRepository.findAll();
    }


    @PostMapping("addClient")
    public ResponseEntity<Client> postProduct(@RequestBody Client client) throws URISyntaxException {
        Client newClient=Client.builder()
                .clientId(client.getClientId())
                .name(client.getName())

                .phone(client.getPhone())
                .nif(client.getNif())
                .rcn(client.getRcn())

                .build();

        newClient=clientRepository.save(newClient);
        if (newClient == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newClient.getClientId())
                    .toUri();
            return ResponseEntity.created(uri).body(newClient);
        }

    }


    @RequestMapping(value="deleteclient", method = {RequestMethod.GET, RequestMethod.DELETE})
    public @ResponseBody
    void deleteClient(@RequestParam("id") Long clientId){



     Client client=  clientRepository.findByClientId(clientId);
     List<Sell> sells=sellRepository.findByClient(client);
        for(Sell sell: sells){
            orderSellRepository.deleteBySell(sell);
            sellRepository.delete(sell);
        }
        clientRepository.delete(client);


    }
}
