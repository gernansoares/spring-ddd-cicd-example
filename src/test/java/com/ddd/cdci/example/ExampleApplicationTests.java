package com.ddd.cdci.example;

import com.ddd.cdci.example.domain.Order;
import com.ddd.cdci.example.domain.Product;
import com.ddd.cdci.example.domain.repository.OrderRepository;
import com.ddd.cdci.example.domain.service.DomainOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ExampleApplicationTests {

	private OrderRepository orderRepository;
	private DomainOrderService tested;

	@BeforeEach
	void setUp() {
		orderRepository = mock(OrderRepository.class);
		tested = new DomainOrderService(orderRepository);
	}

	@Test
	void shouldCreateOrder_thenSaveIt() {
		final Product product = Product.of(UUID.randomUUID(), "productName", BigDecimal.TEN);

		final UUID id = tested.createOrder(product, BigDecimal.ONE);

		verify(orderRepository).save(any(Order.class));
		assertNotNull(id);
	}

}
