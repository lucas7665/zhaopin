package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.config.ActivityConfig;
import java.time.ZoneOffset;

@Controller
public class ImageController {

    @Autowired
    private ActivityConfig activityConfig;

    @GetMapping(value = "/show", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    public String showImages(Model model) {
        model.addAttribute("endTime", 
            activityConfig.getEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        model.addAttribute("title", activityConfig.getTitle());
        model.addAttribute("description", activityConfig.getDescription());
        return "show";  // 对应templates/show.html
    }
} 