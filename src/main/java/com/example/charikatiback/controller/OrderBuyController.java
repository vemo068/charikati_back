package com.example.charikatiback.controller;


import com.example.charikatiback.entity.*;
import com.example.charikatiback.repository.BuyRepository;
import com.example.charikatiback.repository.OrderBuyRepository;
import com.example.charikatiback.repository.OrderSellRepository;
import com.example.charikatiback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping
public class OrderBuyController {
    @Autowired
    private OrderBuyRepository orderBuyRepository;
    @Autowired
    private BuyRepository buyRepository;
    @Autowired
    private ProductRepository productRepository;



    @RequestMapping(value = "orderbuys",method = RequestMethod.GET)
    public @ResponseBody
    List<OrderBuy> getOrderBuys(@RequestParam("id") Long buyId){
        Buy buy=buyRepository.findByBuyId(buyId);
        return orderBuyRepository.findByBuy(buy);
    }
    @PostMapping("addorderbuy")
    public ResponseEntity<OrderBuy> postOrderBuy(@RequestBody OrderBuy orderBuy) throws URISyntaxException {
        OrderBuy newOrderBuy=OrderBuy.builder()
                .product(orderBuy.getProduct())
                .buy(orderBuy.getBuy())
                .quantity(orderBuy.getQuantity())
                .total(orderBuy.getTotal())

                .build();

        newOrderBuy=orderBuyRepository.save(newOrderBuy);
        if (newOrderBuy == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            Product product=productRepository.findByProductId(newOrderBuy.getProduct().getProductId());
            Buy buy=buyRepository.findByBuyId(newOrderBuy.getBuy().getBuyId());
           product.setStock(product.getStock()+newOrderBuy.getQuantity());
            buy.setTotal(buy.getTotal()+newOrderBuy.getTotal());
            productRepository.save(product);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newOrderBuy.getOrderBuyId())
                    .toUri();
            return ResponseEntity.created(uri).body(newOrderBuy);
        }

    }


    @RequestMapping(value="deleteorderbuy", method = {RequestMethod.GET, RequestMethod.DELETE})
    public @ResponseBody
    void deleteOrderBuy(@RequestParam("id") Long orderBuyId){
        OrderBuy orderBuy=orderBuyRepository.findByOrderBuyId(orderBuyId);
        Buy buy=orderBuy.getBuy();
        buy.setTotal(buy.getTotal()-orderBuy.getTotal());
        orderBuyRepository.deleteById(orderBuyId);


    }
}
