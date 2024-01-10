package com.ddd.cdci.example.infrastructure.config;

import com.ddd.cdci.example.domain.repository.OrderRepository;
import com.ddd.cdci.example.domain.service.DomainOrderService;
import com.ddd.cdci.example.domain.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class BeanConfiguration {

    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new DomainOrderService(orderRepository);
    }

}
