package com.ddd.cdci.example.application;

import com.ddd.cdci.example.application.request.AddItemRequest;
import com.ddd.cdci.example.application.request.CreateOrderRequest;
import com.ddd.cdci.example.application.response.AddItemResponse;
import com.ddd.cdci.example.application.response.CreateOrderResponse;
import com.ddd.cdci.example.domain.Order;
import com.ddd.cdci.example.domain.Product;
import com.ddd.cdci.example.domain.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody @Valid final CreateOrderRequest request) {
        final UUID orderId = orderService.createOrder(request.getProduct(), request.getQuantity());
        return new ResponseEntity(new CreateOrderResponse(orderId), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{orderId}/items", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddItemResponse> addItem(@PathVariable final UUID orderId,
                                                   @RequestBody @Valid final AddItemRequest request) {
        final UUID itemId = orderService.addItem(orderId, request.getProduct(), request.getQuantity());
        return new ResponseEntity(new AddItemResponse(itemId), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{orderId}/items")
    public void removeItem(@PathVariable final UUID orderId,
                           @RequestParam final UUID itemId) {
        orderService.removeItem(orderId, itemId);
    }

    @PutMapping(value = "/{orderId}/complete")
    public void completeOrder(@PathVariable final UUID orderId) {
        orderService.completeOrder(orderId);
    }

}
