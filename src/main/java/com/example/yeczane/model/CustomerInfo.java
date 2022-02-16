package com.example.yeczane.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class CustomerInfo {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    private String customerName;

    @Getter @Setter
    @Column(nullable = false)
    private String customerAddress;

    @Getter @Setter
    @Column(nullable = false)
    private String customerPhone;

    @OneToOne
    @Getter @Setter
    private Users user;
}
