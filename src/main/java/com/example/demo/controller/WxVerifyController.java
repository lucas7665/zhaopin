package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxVerifyController {
    
    @GetMapping("/MP_verify_ZXpuFHgD8543NltN.txt")
    public String verify() {
        return "ZXpuFHgD8543NltN";
    }
    
    @GetMapping("/test")
    public String test() {
        return "Server is running";
    }
} 