package com.example.newultramegaproject.controller;

import com.example.newultramegaproject.domain.Customer;
import com.example.newultramegaproject.repository.RoleRepository;
import com.example.newultramegaproject.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user" ,new Customer());
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(Customer user) {
        user.addRole(roleRepository.getById(2L));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        customerService.save(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}