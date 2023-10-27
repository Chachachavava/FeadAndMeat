package com.example.newultramegaproject.controller;

import com.example.newultramegaproject.domain.Customer;
import com.example.newultramegaproject.domain.CustomerOrder;
import com.example.newultramegaproject.repository.CustomerOrderRepository;
import com.example.newultramegaproject.service.CustomerService;
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
@RequestMapping("/order")
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final CustomerService customerService;
    private final CustomerOrderRepository customerOrderRepository;
    @GetMapping
    public String createOrder(Authentication authentication,Model model){
        Customer customer = (Customer) authentication.getPrincipal();
        CustomerOrder customerOrder = customerService.addOrder(customer);
        if(customerOrder!=null) {
            log.info("Order save : "+customerOrder);
            return "redirect:/order/" + customerOrder.getId();
        }else {
            log.info("Cart is null");
            model.addAttribute("error","cart is null");
            return "error";
        }
    }
    @GetMapping("/{id}")
    public String create(@PathVariable Long id, Model model) {
        Optional<CustomerOrder> customerOrder = customerOrderRepository.findById(id);
        if(customerOrder.isPresent()) {
            model.addAttribute("customerOrder", customerOrder.get());
            return "Shop/order";
        }else {
            log.info("Order with id = "+id+" not found");
            model.addAttribute("error","order is not found");
            return "error";
        }
    }
}
