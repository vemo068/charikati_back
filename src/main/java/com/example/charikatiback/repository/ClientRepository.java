package com.example.charikatiback.repository;

import com.example.charikatiback.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
    public Client findByClientId(Long id);
}
