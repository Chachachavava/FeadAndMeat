package com.example.newultramegaproject.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NonNull
    private String name;
    @OneToMany(mappedBy = "productType")
    private List<Product> products;

    public ProductType() {

    }
    public ProductType(String name) {
        this.name = name;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
