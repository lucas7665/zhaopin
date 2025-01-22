package com.example.demo.controller;

import com.example.demo.entity.Activity;
import com.example.demo.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.ZoneOffset;
import java.util.List;

@Controller
public class ImageController {

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping(value = "/show", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    public String showImages(Model model) {
        List<Activity> activities = activityRepository.findCurrentActivities();
        Activity activity = activities.isEmpty() ? null : activities.get(0);
        
        if (activity != null) {
            model.addAttribute("endTime", 
                activity.getEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            model.addAttribute("title", activity.getTitle());
            model.addAttribute("description", activity.getDescription());
        } else {
            model.addAttribute("endTime", 0);
            model.addAttribute("title", "暂无活动");
            model.addAttribute("description", "敬请期待下一场活动");
        }
        return "show";  // 对应templates/show.html
    }
} 