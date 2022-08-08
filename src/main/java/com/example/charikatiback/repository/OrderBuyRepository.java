package com.example.charikatiback.repository;

import com.example.charikatiback.entity.Buy;
import com.example.charikatiback.entity.OrderBuy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderBuyRepository extends JpaRepository<OrderBuy, Long> {
    public List<OrderBuy> findByBuy(Buy buy);

    OrderBuy findByOrderBuyId(Long orderBuyId);
}

