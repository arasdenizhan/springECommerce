package com.example.yeczane.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date orderDate;

    @Getter @Setter
    @Column(nullable = false)
    private String customerName;

    @Getter @Setter
    @Column(nullable = false)
    private String customerAddress;

    @Getter @Setter
    @Column(nullable = false)
    private String customerPhone;

    @OneToMany(mappedBy = "order")
    @Getter @Setter
    private List<OrderDetails> orderDetails;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.RECEIVED;
}