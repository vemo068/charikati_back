package com.example.charikatiback.controller;


import com.example.charikatiback.entity.Sell;
import com.example.charikatiback.entity.Total;
import com.example.charikatiback.repository.BuyRepository;
import com.example.charikatiback.repository.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TotalsController {
    @Autowired
    private SellRepository sellRepository;
    @Autowired
    private BuyRepository buyRepository;


    @RequestMapping(value = "yeartotal",method = RequestMethod.GET)
    public @ResponseBody
    Total getYearTotal(@RequestParam("year") Long year){



        Total total=Total.builder()
                .total(1)
                .sellsTotal(1)
                .buysTotal(1)
                .build();
        return total;
    }


}
