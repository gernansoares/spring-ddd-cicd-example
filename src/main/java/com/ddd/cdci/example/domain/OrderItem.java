package com.ddd.cdci.example.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public final class OrderItem {

    private UUID id;

    private UUID productId;

    private BigDecimal quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private OrderItem(UUID id, Product product, BigDecimal quantity) {
        this.id = id;
        this.productId = product.getId();
        this.quantity = quantity;
        this.price = product.getPrice();
        this.totalPrice = this.price.multiply(quantity);
    }

    public static OrderItem of(UUID id, Product product, BigDecimal quantity) {
        return new OrderItem(id, product, quantity);
    }

}
