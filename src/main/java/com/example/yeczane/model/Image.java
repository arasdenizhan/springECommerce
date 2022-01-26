package com.example.yeczane.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Image {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob @Getter @Setter
    @Column(length = Integer.MAX_VALUE)
    private byte[] data;

    @ManyToOne @Getter @Setter
    private Product product;
}
