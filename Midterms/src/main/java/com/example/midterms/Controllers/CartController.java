package com.example.midterms.Controllers;

import com.example.midterms.Service.CartService;
import com.example.midterms.Service.CartServiceInterface;
import com.example.midterms.models.Cart;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/carts")
public class CartController {

    private CartService cartService;

    @GetMapping("/carts")
    public ArrayList<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    @GetMapping("/carts/{id}")
    public Cart getSingleCart(@PathVariable("id") Long id){
        return cartService.getSingleCart(id);
    }

    @GetMapping("/carts")
    @ResponseBody
    public ArrayList<Cart> getInDateRange(@RequestParam("start-date") @JsonFormat(pattern="yyyy-MM-DD") Date startDate,
                                          @RequestParam("end-date") @JsonFormat(pattern="yyyy-MM-DD") Date endDate){
        return new ArrayList<>();
    }

    @GetMapping("/carts/user/{id}")
    public ArrayList<Cart> getUserCart(@PathVariable("id") Long id){
        return new ArrayList<>();
    }

    @PostMapping("/carts")
    public Cart AddProduct(Cart newProd){
        return cartService.AddnewCart(newProd);
    }

    @PutMapping("/carts/{id}")
    public Cart UpdateProduct(@RequestBody Cart cart, @PathVariable("id") Long id){
        return cartService.UpdateCart(cart, id);
    }

    @DeleteMapping("/carts/{id}")
    public Cart DeleteProduct(@RequestBody Cart cart, @PathVariable("id") Long id){
        return cartService.DeleteCart(cart,id);
    }
}
