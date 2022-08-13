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
public class Forni {
    @Id
    @GeneratedValue
    private Long forniId;
    private String name;
    private String phone;
    private boolean isDeleted;
}
