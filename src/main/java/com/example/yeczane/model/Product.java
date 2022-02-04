package com.example.yeczane.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
public class Product {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter @NotNull
    @Column(nullable = false)
    private String name;

    @Getter @Setter @NotNull
    @Column(nullable = false)
    private String code;

    @Getter @Setter @NotNull
    @Column(nullable = false)
    private Double price;

    @Getter @Setter
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date createDate;

    @Getter @Setter
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;
}
