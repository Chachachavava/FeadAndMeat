package com.example.newultramegaproject.repository;

import com.example.newultramegaproject.domain.CartItem;
import com.example.newultramegaproject.domain.Customer;
import com.example.newultramegaproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
