package com.example.midterms.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Cart {
    Long id;
    Long userId;
    String Date;
    ArrayList<Product> products;
}
