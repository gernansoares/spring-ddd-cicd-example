package com.ddd.cdci.example.domain.repository;

import com.ddd.cdci.example.domain.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    Optional<Order> findById(UUID id);

    void save(final Order order);

}
