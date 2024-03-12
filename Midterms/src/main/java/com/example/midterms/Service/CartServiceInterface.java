package com.example.midterms.Service;

import com.example.midterms.models.Cart;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public interface CartServiceInterface {
    ArrayList<Cart> getAllCarts();

    Cart getSingleCart(Long id);

    ArrayList<Cart> getDateInRange(@JsonFormat(pattern = "yyyy-MM-DD") Date startDate, @JsonFormat(pattern = "yyyy-MM-DD") Date endDate);

    HashMap<String, Long> getUserCart(Long userid);

    Cart AddnewCart(Cart cart);

    Cart UpdateCart(Cart cart, Long id);

    Cart DeleteCart(Cart cart, Long id);
}
