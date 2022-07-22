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
public class Sell {
    @Id
    @GeneratedValue
    private Long sellId;
    private String date;
    private Long total;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
}
