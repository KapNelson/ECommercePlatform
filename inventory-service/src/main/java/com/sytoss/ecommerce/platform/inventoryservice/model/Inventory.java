package com.sytoss.ecommerce.platform.inventoryservice.model;

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
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uid;
    @Column(nullable = false)
    private String productCode;
    @Column(nullable = false)
    private Integer quantity;
    private Integer reserved;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Storage storage;
}
