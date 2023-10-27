package com.example.newultramegaproject.controller;

import com.example.newultramegaproject.repository.ProductRepository;
import com.example.newultramegaproject.repository.ProductTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog/feed")
@AllArgsConstructor
public class CatalogFeedController {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;

    @GetMapping
    public String feedCatalog(Model model){
        if(productTypeRepository.findById(2L).isPresent()) {
            model.addAttribute("products", productRepository.findByProductType(productTypeRepository.findById(2L).get()));
        }
        return "Shop/catalogFeed";
    }
}

