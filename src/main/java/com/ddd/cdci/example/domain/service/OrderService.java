package com.ddd.cdci.example.domain.service;

import com.ddd.cdci.example.domain.Order;
import com.ddd.cdci.example.domain.Product;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    UUID createOrder(Product product, BigDecimal quantity);

    UUID addItem(UUID orderId, Product product, BigDecimal quantity);

    void removeItem(UUID orderId, UUID itemId);

    void completeOrder(UUID orderId);

}
