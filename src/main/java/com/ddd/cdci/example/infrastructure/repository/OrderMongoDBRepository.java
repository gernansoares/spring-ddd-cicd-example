package com.ddd.cdci.example.infrastructure.repository;

import com.ddd.cdci.example.domain.Order;
import com.ddd.cdci.example.domain.repository.OrderRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Primary
public class OrderMongoDBRepository implements OrderRepository {

    private final SpringDataOrderMongoDBRepository orderRepository;

    public OrderMongoDBRepository(final SpringDataOrderMongoDBRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> findById(final UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(final Order order) {
        orderRepository.save(order);
    }
}
