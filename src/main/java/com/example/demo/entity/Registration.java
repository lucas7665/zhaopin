package com.example.demo.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "registrations")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;        // 姓名
    
    @Column(nullable = false)
    private String phone;       // 联系方式
    
    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;  // 求职意向
    
    @Column(nullable = false)
    private LocalDateTime createTime = LocalDateTime.now();  // 报名时间
} 