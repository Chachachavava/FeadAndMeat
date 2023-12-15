package com.example.newultramegaproject.controller;

import com.example.newultramegaproject.repository.ProductRepository;
import com.example.newultramegaproject.repository.ProductTypeRepository;
import com.example.newultramegaproject.repository.CustomerRepository;
import com.example.newultramegaproject.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/catalog/meat")
@AllArgsConstructor
public class CatalogMeatController {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductService productService;

    @GetMapping
    public String meatCatalog(Model model){
        if(productTypeRepository.findById(1L).isPresent()) {
            model.addAttribute("products", productService.show(productRepository.findByProductType(productTypeRepository.findById(1L).get())));
        }
        return "Shop/catalogMeat";
    }
}
