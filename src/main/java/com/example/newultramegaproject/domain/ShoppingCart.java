package com.example.newultramegaproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

@Entity
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @OneToOne
    private Customer customer;
    @OneToMany(fetch = FetchType.EAGER)
    private List<CartItem> items = new ArrayList<>();
    public ShoppingCart() {}
    public void add(CartItem cartItem){
        items.add(cartItem);
    }
    public CartItem find(CartItem cartItem){
        for (CartItem item: items) {
            if(Objects.equals(item.getProduct().getName(),cartItem.getProduct().getName())){
                return item;
            }
        }
        return null;
    }
    public ShoppingCart(Customer customer){
        this.customer = customer;
    }
    public void replace(CartItem cartItem){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equals(cartItem)) {
                items.set(i, cartItem);
            }
        }
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }
}
