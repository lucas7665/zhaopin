package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

    @GetMapping("/show")
    public String showImages() {
        return "show";  // 对应templates/show.html
    }
} 