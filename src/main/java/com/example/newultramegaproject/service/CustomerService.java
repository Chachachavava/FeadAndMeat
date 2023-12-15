package com.example.newultramegaproject.service;

import com.example.newultramegaproject.domain.*;
import com.example.newultramegaproject.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final OrderListRepository ordersRepository;

    public Customer save(Customer customer){
        customer = customerRepository.save(customer);
        ShoppingCart shoppingCart = shoppingCartRepository.save(new ShoppingCart(customer));
        customer.setShoppingCart(shoppingCart);
        customer.setOrders(ordersRepository.save(new OrderList(customer)));
        customerRepository.save(customer);
        return customer;
    }

    public CustomerOrder addOrder(Customer customer){
        if (!customer.getShoppingCart().getItems().isEmpty()) {
            ShoppingCart shoppingCart = customer.getShoppingCart();
            CustomerOrder customerOrder = new CustomerOrder();
            OrderList orderList = customer.getOrders();
            customerOrder.addAllItem(shoppingCart);
            customerOrder = customerOrderRepository.save(customerOrder);
            orderList.addOrder(customerOrder);
            customer.setOrders(ordersRepository.save(orderList));
            shoppingCart.setItems(new ArrayList<>());
            shoppingCart = shoppingCartRepository.save(shoppingCart);
            customer.setShoppingCart(shoppingCart);
            customerRepository.save(customer);
            return customerOrder;
        }
        return null;
    }
}
