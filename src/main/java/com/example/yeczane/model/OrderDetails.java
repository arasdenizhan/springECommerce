package com.example.yeczane.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "orders_details")
public class OrderDetails {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @Getter @Setter
    private Product product;

    @Getter @Setter
    @Column(nullable = false)
    private Double amount;

    @Getter @Setter
    @Column(nullable = false)
    private Double price;

    @ManyToOne
    private Order order;

}
