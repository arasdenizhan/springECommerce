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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date orderDate;

    @OneToOne
    @Getter @Setter
    private CustomerInfo customerInfo;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Getter @Setter
    private List<OrderDetails> orderDetails;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.TEMPORARY;
}
