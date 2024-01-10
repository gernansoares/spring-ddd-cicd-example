package com.ddd.cdci.example.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public final class Order {

    private UUID id;

    private OrderStatus status;

    private List<OrderItem> items;

    private BigDecimal totalPrice;

    private Order(UUID id, Product product, BigDecimal quantity) {
        this.id = id;
        this.status = OrderStatus.CREATED;
        this.totalPrice = BigDecimal.ZERO;
        this.items = Arrays.asList(OrderItem.of(id, product, quantity));
        alterPrice(items.get(0).getTotalPrice(), true);
    }

    public static Order of(UUID id, Product product, BigDecimal quantity) {
        return new Order(id, product, quantity);
    }

    public void addItem(UUID id, Product product, BigDecimal quantity) {
        validateStateCompleted();
        validateProduct(product);
        validateQuantity(quantity);

        final OrderItem item = OrderItem.of(id, product, quantity);
        this.items.add(item);
        alterPrice(item.getTotalPrice(), true);
    }

    private void validateStateCompleted() {
        if (OrderStatus.COMPLETED.equals(this.status)) {
            throw new IllegalStateException("Order is be completed");
        }
    }

    private void validateProduct(final Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product is required");
        }
    }

    private void validateQuantity(final BigDecimal quantity) {
        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantity is required");
        }
    }

    private void alterPrice(BigDecimal value, boolean addToPrice) {
        if (addToPrice) {
            this.totalPrice = this.totalPrice.add(value);
        } else {
            this.totalPrice = this.totalPrice.subtract(value);
        }
    }

    public void removeItem(UUID id) {
        validateStateCompleted();
        final OrderItem item = getOrderItem(id);
        this.items.remove(item);
        alterPrice(item.getTotalPrice(), false);
    }

    private OrderItem getOrderItem(UUID id) {
        return this.items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
    }

    public void complete() {
        validateStateCompleted();
        this.status = OrderStatus.COMPLETED;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

}
