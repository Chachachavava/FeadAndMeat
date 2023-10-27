package com.example.newultramegaproject.repository;

import com.example.newultramegaproject.domain.Product;
import com.example.newultramegaproject.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductType(ProductType productType);
}
