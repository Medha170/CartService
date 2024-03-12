package com.example.midterms.Service;

import com.example.midterms.DTO.CartDTO;
import com.example.midterms.models.Cart;
import com.example.midterms.models.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@Service
public class CartService implements CartServiceInterface{
    private final RestTemplate restTemplate=new RestTemplate();

    public int getCartSize(){
        ResponseEntity<CartDTO[]> responseEntity=restTemplate.getForEntity(
                "https://fakestoreapi.com/carts",
                CartDTO[].class
        );
        CartDTO[] products=responseEntity.getBody();
        if (products!=null) return products.length;
        return 0;
    }
    @Override
    public ArrayList<Cart> getAllCarts(){
        ArrayList<Cart> result=new ArrayList<>();
        for (long i=0; i<getCartSize(); i++){
            result.add(getSingleCart(i));
        }
        return result;
    }

    @Override
    public Cart getSingleCart(Long id){
        CartDTO cartDTO= restTemplate.getForObject(
                "https://fakestoreapi.com/carts/"+id,
                CartDTO.class
        );
        Cart cart=new Cart();
        assert cartDTO != null;
        cart.setId(cartDTO.getId());
        cart.setUserId(cartDTO.getUserId());
        cart.setDate(cartDTO.getDate());
        cart.setProducts(new ArrayList<>());
        cart.getProducts().addAll((Collection<? extends Product>) cartDTO.getProduct());
        return cart;
    }

    @Override
    public ArrayList<Cart> getDateInRange(@JsonFormat(pattern = "yyyy-MM-DD") Date startDate, @JsonFormat(pattern = "yyyy-MM-DD") Date endDate){
        return new ArrayList<>();
    }

    @Override
    public HashMap<String, Long> getUserCart(Long userid){
        HashMap<String, Long> result;
        CartDTO cartDTO=restTemplate.getForObject(
                "https://fakestoreapi.com/carts/carts/user/"+userid,
                CartDTO.class
        );
        result=cartDTO.getProduct();
        return result;
    }

    @Override
    public Cart AddnewCart(Cart cart){
        CartDTO cartDTO= restTemplate.postForObject(
                "https://fakestoreapi.com/carts",
                cart,
                CartDTO.class
        );
        Cart newProduct=new Cart();
        assert cartDTO != null;
        newProduct.setId(cartDTO.getId());
        newProduct.setUserId(cartDTO.getUserId());
        newProduct.setDate(cartDTO.getDate());
        newProduct.setProducts(new ArrayList<>());
        newProduct.getProducts().addAll((Collection<? extends Product>) cartDTO.getProduct());
        return newProduct;
    }

    @Override
    public Cart UpdateCart(Cart cart, Long id){
        String url = "https://fakestoreapi.com/carts/"+id;

        // Create the request entity with the updated product
        HttpEntity<Cart> requestEntity = new HttpEntity<>(cart);

        // Send the PUT request using exchange method
        ResponseEntity<CartDTO> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                CartDTO.class,
                id
        );
        CartDTO cartDTO=responseEntity.getBody();
        Cart updated=new Cart();
        assert cartDTO != null;
        updated.setId(cartDTO.getId());
        updated.setUserId(cartDTO.getUserId());
        updated.setDate(cartDTO.getDate());
        updated.setProducts(new ArrayList<>());
        updated.getProducts().addAll((Collection<? extends Product>) cartDTO.getProduct());
        return updated;
    }

    @Override
    public Cart DeleteCart(Cart cart, Long id){
        String url="https://fakestoreapi.com/carts/"+id;
        HttpEntity<Cart> requestEntity=new HttpEntity<>(cart);


        ResponseEntity<Void> cartDTO=restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                requestEntity,
                Void.class,
                id
        );
        return cart;
    }
}
