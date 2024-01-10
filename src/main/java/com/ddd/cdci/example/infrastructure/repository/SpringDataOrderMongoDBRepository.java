package com.ddd.cdci.example.infrastructure.repository;

import com.ddd.cdci.example.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataOrderMongoDBRepository extends MongoRepository<Order, UUID> {
}
