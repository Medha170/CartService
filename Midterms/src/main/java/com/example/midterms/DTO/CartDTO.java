package com.example.midterms.DTO;

import com.example.midterms.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
public class CartDTO {
    private Long id;
    private Long UserId;
    private String date;
    private HashMap<String, Long> product;
}
