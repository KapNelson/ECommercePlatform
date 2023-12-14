package com.sytoss.ecommerce.platform.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uid;
    @Column(unique = true, nullable = false)
    private String code;
    private String name;
    private String description;
    private String image;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Price price;
}
