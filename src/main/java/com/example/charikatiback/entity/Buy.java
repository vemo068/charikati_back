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
public class Buy {
    @Id
    @GeneratedValue
    private Long buyId;
    private String date;
    private Long total;
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "forniId")
    private Forni forni;
}
