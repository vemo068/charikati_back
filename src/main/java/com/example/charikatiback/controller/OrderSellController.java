package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Client;
import com.example.charikatiback.entity.OrderSell;
import com.example.charikatiback.entity.Product;
import com.example.charikatiback.entity.Sell;
import com.example.charikatiback.repository.OrderSellRepository;
import com.example.charikatiback.repository.ProductRepository;
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
public class OrderSellController {
    @Autowired
    private OrderSellRepository orderSellRepository;
    @Autowired
    private SellRepository sellRepository;
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "ordersells",method = RequestMethod.GET)
    public @ResponseBody
    List<OrderSell> getOrderSells(@RequestParam("id") Long sellId){
        Sell sell=sellRepository.findBySellId(sellId);
        return orderSellRepository.findBySell(sell);
    }
    @PostMapping("addordersell")
    public ResponseEntity<OrderSell> postProduct(@RequestBody OrderSell orderSell) throws URISyntaxException {
        OrderSell newOrderSell=OrderSell.builder()
                .product(orderSell.getProduct())
                .sell(orderSell.getSell())
                .quantity(orderSell.getQuantity())
                .total(orderSell.getTotal())

                .build();

        newOrderSell=orderSellRepository.save(newOrderSell);
        if (newOrderSell == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            Product product=productRepository.findByProductId(newOrderSell.getProduct().getProductId());
            Sell sell=sellRepository.findBySellId(newOrderSell.getSell().getSellId());

            product.setStock(product.getStock()-newOrderSell.getQuantity());
            sell.setTotal(sell.getTotal()+newOrderSell.getTotal());

            sellRepository.save(sell);
            productRepository.save(product);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newOrderSell.getOrderSellId())
                    .toUri();
            return ResponseEntity.created(uri).body(newOrderSell);
        }

    }

}
