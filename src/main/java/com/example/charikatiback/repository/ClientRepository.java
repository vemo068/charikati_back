package com.example.charikatiback.repository;

import com.example.charikatiback.entity.Buy;
import com.example.charikatiback.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    public Client findByClientId(Long id);
    public List<Client> findByIsDeleted(boolean isDeleted);
}
