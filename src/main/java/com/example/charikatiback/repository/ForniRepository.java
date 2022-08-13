package com.example.charikatiback.repository;

import com.example.charikatiback.entity.Buy;
import com.example.charikatiback.entity.Forni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForniRepository extends JpaRepository<Forni, Long> {
    Forni findByForniId(Long id);
    public List<Forni> findByIsDeleted(boolean isDeleted);
}

