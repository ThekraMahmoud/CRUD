package com.example.project1.Mudel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private String id;
    private String username;
    private int balance;
}
