package com.example.newultramegaproject.controller;

import com.example.newultramegaproject.domain.CartItem;
import com.example.newultramegaproject.domain.Customer;
import com.example.newultramegaproject.domain.Product;
import com.example.newultramegaproject.repository.CartItemRepository;
import com.example.newultramegaproject.repository.ProductRepository;
import com.example.newultramegaproject.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    @GetMapping
    public String shoppingCart(Authentication authentication, Model model){
        Customer customer = (Customer) authentication.getPrincipal();
        model.addAttribute("items",customer.getShoppingCart().getItems());
        return "home/cart";
    }
    @GetMapping("/add/{id}")
    public String add(@PathVariable Long id,Authentication authentication,Model model){
        Customer customer = (Customer) authentication.getPrincipal();
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            cartService.addToCart(customer,product.get());
            return "redirect:/cart";
        }else {
            log.info("Product with id = "+id+" not found");
            model.addAttribute("error","product is not found");
            return "error";
        }
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,Authentication authentication,Model model){
        Customer customer = (Customer) authentication.getPrincipal();
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        if(cartItem.isPresent()) {
            cartService.deleteFromCart(customer,cartItem.get().getId());
            return "redirect:/cart";
        }else {
            log.info("Cart item with id = "+id+" not found");
            model.addAttribute("error","cart item is not found");
            return "error";
        }
    }
    @GetMapping("/plus/{id}")
    public String plus(@PathVariable Long id,Authentication authentication,Model model){
        Customer customer = (Customer) authentication.getPrincipal();
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        if(cartItem.isPresent()) {
            cartService.plus(customer,cartItem.get().getId());
            return "redirect:/cart";
        }else {
            log.info("Cart item with id = "+id+" not found");
            model.addAttribute("error","cart item is not found");
            return "error";
        }
    }
    @GetMapping("/minus/{id}")
    public String minus(@PathVariable Long id,Authentication authentication,Model model){
        Customer customer = (Customer) authentication.getPrincipal();
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        if(cartItem.isPresent()) {
            cartService.minus(customer,cartItem.get().getId());
            return "redirect:/cart";
        }else {
            log.info("Cart item with id = "+id+" not found");
            model.addAttribute("error","cart item is not found");
            return "error";
        }
    }
}
