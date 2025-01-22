package com.example.demo.repository;

import com.example.demo.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    // 根据姓名查询
    boolean existsByName(String name);
    
    // 根据手机号查询
    boolean existsByPhone(String phone);
} 