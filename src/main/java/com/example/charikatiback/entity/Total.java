package com.example.charikatiback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Total {
     int total;
     int sellsTotal;
     int buysTotal;
}
