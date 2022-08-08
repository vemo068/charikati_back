package com.example.charikatiback.repository;

import com.example.charikatiback.entity.OrderSell;
import com.example.charikatiback.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderSellRepository extends JpaRepository<OrderSell,Long> {
    public List<OrderSell> findBySell(Sell sell);

    void deleteBySell(Sell sell);

    OrderSell findByOrderSellId(Long orderSellId);
}
