package com.example.newultramegaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home/home";
    }

    @GetMapping("/about")
    public String about(){
        return "home/about";
    }

}
