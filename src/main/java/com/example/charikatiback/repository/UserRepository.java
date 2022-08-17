package com.example.charikatiback.repository;

import com.example.charikatiback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long id);
    User findByNameAndPassword(String name, String password);

}

