package com.ddd.cdci.example.infrastructure.config;

import com.ddd.cdci.example.infrastructure.repository.SpringDataOrderMongoDBRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = SpringDataOrderMongoDBRepository.class)
public class MongoDBConfiguration {
}
