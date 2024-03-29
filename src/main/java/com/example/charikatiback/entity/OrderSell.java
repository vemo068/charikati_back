package com.example.charikatiback.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderSellId;
    private Long total;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "sellId")
    private Sell sell;
}
