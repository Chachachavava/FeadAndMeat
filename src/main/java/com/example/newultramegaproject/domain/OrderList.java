package com.example.newultramegaproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @NonNull
    private Customer customer;
    @OneToMany(fetch = FetchType.EAGER)
    private List<CustomerOrder> orders = new ArrayList<>();

    public OrderList() {}
    public OrderList(Customer customer) {
        this.customer = customer;
    }
    public void addOrder(CustomerOrder order) {
        orders.add(order);
    }
    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", items=" + orders +
                '}';
    }
}
