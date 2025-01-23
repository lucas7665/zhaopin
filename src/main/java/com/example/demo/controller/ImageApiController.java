package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class ImageApiController {

    @Value("${image.path}")
    private String imagePath;

    @GetMapping("/api/images")
    public List<String> getImages() {
        try {
            Resource resource = new ClassPathResource("static/pic");
            File directory = resource.getFile();
            
            if (!directory.exists() || !directory.isDirectory()) {
                return new ArrayList<>();
            }

            return Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                    .filter(file -> {
                        String name = file.getName().toLowerCase();
                        return name.endsWith(".jpg") || 
                               name.endsWith(".jpeg") || 
                               name.endsWith(".png") || 
                               name.endsWith(".gif");
                    })
                    .map(File::getName)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}