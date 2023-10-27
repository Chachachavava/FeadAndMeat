package com.example.newultramegaproject.controller;

import com.example.newultramegaproject.domain.Customer;
import com.example.newultramegaproject.repository.RoleRepository;
import com.example.newultramegaproject.repository.CustomerRepository;
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

    private CustomerRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user" ,new Customer());
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(Customer user) {
        user.addRole(roleRepository.getById(1L));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/home";
    }

}