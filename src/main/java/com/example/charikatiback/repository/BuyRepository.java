package com.example.charikatiback.repository;

import com.example.charikatiback.entity.Buy;
import com.example.charikatiback.entity.Client;
import com.example.charikatiback.entity.Forni;
import com.example.charikatiback.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy, Long> {
    public Buy findByBuyId(Long id);
    public List<Buy> findByForniAndIsDeleted(Forni forni ,boolean isDeleted);

}

