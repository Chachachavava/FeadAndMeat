package com.example.newultramegaproject.service;

import com.example.newultramegaproject.domain.Product;
import com.example.newultramegaproject.domain.ProductType;
import com.example.newultramegaproject.repository.ProductRepository;
import com.example.newultramegaproject.repository.ProductTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;

    @Transactional
    public Product save(Product product){
        Optional<ProductType> productTypeTemp = productTypeRepository.findById(product.getProductType().getId());
        if (productTypeTemp.isPresent()){
            productRepository.save(product);
            log.info(product+" product save");
            ProductType productType = productTypeTemp.get();
            productType.addProduct(product);
            productTypeRepository.save(productType);
            log.info(product+" productType update");
            return product;
        }
        return null;
    }
}
