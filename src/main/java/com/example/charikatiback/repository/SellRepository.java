package com.example.charikatiback.repository;

import com.example.charikatiback.entity.Client;
import com.example.charikatiback.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellRepository extends JpaRepository<Sell,Long> {
    public List<Sell> findByClient(Client client);
    public  Sell findBySellId(Long id);
}
