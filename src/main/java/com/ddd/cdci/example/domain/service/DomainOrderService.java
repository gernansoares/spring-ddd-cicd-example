package com.ddd.cdci.example.domain.service;

import com.ddd.cdci.example.domain.Order;
import com.ddd.cdci.example.domain.Product;
import com.ddd.cdci.example.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

public class DomainOrderService implements OrderService {

    private final OrderRepository orderRepository;

    public DomainOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public UUID createOrder(Product product, BigDecimal quantity) {
        final Order order = Order.of(UUID.randomUUID(), product, quantity);
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public UUID addItem(UUID orderId, Product product, BigDecimal quantity) {
        final Order order = getById(orderId);
        final UUID itemId = UUID.randomUUID();
        order.addItem(itemId, product, quantity);
        orderRepository.save(order);
        return itemId;
    }

    @Override
    public void removeItem(UUID orderId, UUID itemId) {
        final Order order = getById(orderId);
        order.removeItem(itemId);
        orderRepository.save(order);
    }

    @Override
    public void completeOrder(UUID orderId) {
        final Order order = getById(orderId);
        order.complete();
        orderRepository.save(order);
    }

    private Order getById(UUID id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }
}
