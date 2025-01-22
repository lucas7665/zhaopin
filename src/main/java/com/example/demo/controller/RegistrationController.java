package com.example.demo.controller;

import com.example.demo.entity.Position;
import com.example.demo.entity.Registration;
import com.example.demo.repository.PositionRepository;
import com.example.demo.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private PositionRepository positionRepository;
    
    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        try {
            String name = request.get("name");
            String phone = request.get("phone");

            // 校验姓名是否重复
            if (registrationRepository.existsByName(name)) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "您已报名成功，请勿重复提交");
                return ResponseEntity.badRequest().body(response);
            }

            // 校验手机号是否重复
            if (registrationRepository.existsByPhone(phone)) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "该手机号已报名成功，请勿重复提交");
                return ResponseEntity.badRequest().body(response);
            }

            Registration registration = new Registration();
            registration.setName(name);
            registration.setPhone(phone);
            
            Position position = positionRepository.findById(Long.parseLong(request.get("positionId")))
                .orElseThrow(() -> new RuntimeException("Position not found"));
            registration.setPosition(position);
            
            registrationRepository.save(registration);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "报名成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
} 