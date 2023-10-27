package com.example.newultramegaproject.service;

import com.example.newultramegaproject.domain.CartItem;
import com.example.newultramegaproject.domain.Customer;
import com.example.newultramegaproject.domain.Product;
import com.example.newultramegaproject.domain.ShoppingCart;
import com.example.newultramegaproject.repository.CartItemRepository;
import com.example.newultramegaproject.repository.CustomerRepository;
import com.example.newultramegaproject.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public void addToCart(Customer customer, Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        CartItem existingCartItem = customer.getShoppingCart().find(cartItem);
        if (existingCartItem!=null){
            existingCartItem.setQuantity(existingCartItem.getQuantity()+1);
            customer.getShoppingCart().replace(cartItemRepository.save(existingCartItem));
        }else {
            cartItem = cartItemRepository.save(cartItem);
            ShoppingCart shoppingCart = customer.getShoppingCart();
            shoppingCart.add(cartItem);
            customer.setShoppingCart(shoppingCart);;
            customerRepository.save(customer);
        }
    }
    public void deleteFromCart(Customer customer, Long id) {
        customer.getShoppingCart().getItems().removeIf(item -> item.getId().equals(id));
        customerRepository.save(customer);
    }

    public void plus(Customer customer, Long id) {
        Optional<CartItem> existingCartItem = cartItemRepository.findById(id);
        if (existingCartItem.isPresent()){
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity()+1);
            ShoppingCart shoppingCart = customer.getShoppingCart();
            shoppingCart.replace(cartItem);
            cartItemRepository.save(cartItem);
            customer.setShoppingCart(shoppingCart);
            customerRepository.save(customer);
        }

    }

    public void minus(Customer customer, Long id) {
        Optional<CartItem> existingCartItem = cartItemRepository.findById(id);
        if (existingCartItem.isPresent()){
            CartItem cartItem = existingCartItem.get();
            if (cartItem.getQuantity()>1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                ShoppingCart shoppingCart = customer.getShoppingCart();
                shoppingCart.replace(cartItem);
                cartItemRepository.save(cartItem);
                customer.setShoppingCart(shoppingCart);
                customerRepository.save(customer);
            }
        }
    }
}
