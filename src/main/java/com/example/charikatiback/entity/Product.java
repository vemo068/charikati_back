package com.example.charikatiback.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue
    private Long productId;
    private String name;
    private int sellPrice;
    private int buyPrice;
    private int stock;

    public void setStock(int stock) {
        this.stock = stock;
    }
}
