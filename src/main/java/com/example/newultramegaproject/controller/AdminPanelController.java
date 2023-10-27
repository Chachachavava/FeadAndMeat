package com.example.newultramegaproject.controller;

import com.example.newultramegaproject.domain.Product;
import com.example.newultramegaproject.domain.ProductType;
import com.example.newultramegaproject.repository.OrderListRepository;
import com.example.newultramegaproject.repository.ProductRepository;
import com.example.newultramegaproject.repository.ProductTypeRepository;
import com.example.newultramegaproject.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/adminPanel")
public class AdminPanelController {
    private final ProductTypeRepository productTypeRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final OrderListRepository orderListRepository;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

    @GetMapping
    public String showProduct(Model model){
        model.addAttribute("products",productRepository.findAll());
        if(productRepository.findAll().isEmpty()){
            log.info("No element with products");
        }
        return "AdminPanel/showProductAction";
    }
    @GetMapping("/order")
    public String showOrder(Model model){
        model.addAttribute("orders",orderListRepository.findAll());
        if(orderListRepository.findAll().isEmpty()){
            log.info("No element with order list");
        }
        return "AdminPanel/order";
    }
    @GetMapping("/addProduct")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("productTypes",productTypeRepository.findAll());
        if(productTypeRepository.findAll().isEmpty()){
            model.addAttribute("error", "No element with product types");
            log.info("No element with product types");
            return "error";
        }
        return "AdminPanel/addProduct";
    }
    @PostMapping("/addProduct")
    public String saveProduct(Product product, @RequestParam("image") MultipartFile file) throws IOException {
        productService.save(product);
        if (file.isEmpty()){
            log.info("No image at create product");
        }else {
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, product.getName() + ".png");
            Files.write(fileNameAndPath, file.getBytes());
        }
        return "redirect:/adminPanel/addProduct";
    }
    @GetMapping("/addProductType")
    public String addProductType(Model model){
        model.addAttribute("productType", new ProductType());
        return "AdminPanel/addProductType";
    }
    @PostMapping("/addProductType")
    public String saveProductType(@Valid ProductType productType, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("error", "No name with product types");
            log.info("No element with product types");
            return "error";
        }
        productTypeRepository.save(productType);
        log.info("Product type save : "+productType);
        return "redirect:/adminPanel/addProductType";
    }
    @GetMapping("/delete")
    public String delete(Model model){
        model.addAttribute("products",productRepository.findAll());
        return "AdminPanel/delete";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,Model model){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            log.info("Product delete : "+product.get());
            productRepository.deleteById(id);
            log.info("Delete product");
            return "redirect:/adminPanel";
        }else {
            log.info("Product with id = "+id+" not found");
            model.addAttribute("error","product is not found");
            return "error";
        }

    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,Model model){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            model.addAttribute("productTypes",productTypeRepository.findAll());
            model.addAttribute("product", product.get());
            return "AdminPanel/update";
        }else {
            log.info("Product with id = "+id+" not found");
            model.addAttribute("error","product is not found");
            return "error";
        }

    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, Product product,@RequestParam("image") MultipartFile file) throws IOException {
        product.setId(id);
        if(!file.isEmpty()) {
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, product.getName() + ".png");
            Files.write(fileNameAndPath, file.getBytes());
        }
        productRepository.save(product);
        return "redirect:/adminPanel";
    }
}
