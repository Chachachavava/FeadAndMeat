package com.example.newultramegaproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER)
    private List<CartItem> items = new ArrayList<>();

    public CustomerOrder() {}
    public boolean addAllItem(ShoppingCart shoppingCart){
        return this.items.addAll(shoppingCart.getItems());
    }

}
