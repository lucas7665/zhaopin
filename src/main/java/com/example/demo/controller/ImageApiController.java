package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
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
        File directory = new File(imagePath);
        if (!directory.exists() || !directory.isDirectory()) {
            directory.mkdirs();
            return Arrays.asList();
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
    }
}