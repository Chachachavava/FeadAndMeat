package com.example.newultramegaproject.repository;

import com.example.newultramegaproject.domain.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {
}
