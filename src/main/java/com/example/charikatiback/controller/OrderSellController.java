package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Client;
import com.example.charikatiback.entity.OrderSell;
import com.example.charikatiback.entity.Sell;
import com.example.charikatiback.repository.OrderSellRepository;
import com.example.charikatiback.repository.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OrderSellController {
    @Autowired
    private OrderSellRepository orderSellRepository;
    @Autowired
    private SellRepository sellRepository;


    @RequestMapping(value = "ordersells",method = RequestMethod.GET)
    public @ResponseBody
    List<OrderSell> getOrderSells(@RequestParam("id") Long sellId){
        Sell sell=sellRepository.findBySellId(sellId);
        return orderSellRepository.findBySell(sell);
    }
}
