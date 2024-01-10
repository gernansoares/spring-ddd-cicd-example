package com.ddd.cdci.example.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public final class Product {

    private UUID id;

    private String name;

    private BigDecimal price;

    private Product(UUID id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static Product of(UUID id, String name, BigDecimal price) {
        return new Product(id, name, price);
    }

}
