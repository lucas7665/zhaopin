package com.example.demo.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;    // 职位名称
} 