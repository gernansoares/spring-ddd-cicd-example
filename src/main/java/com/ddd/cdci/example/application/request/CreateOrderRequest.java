package com.ddd.cdci.example.application.request;

import com.ddd.cdci.example.domain.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateOrderRequest {

    @NotNull(message = "Product is required")
    private Product product;

    @NotNull(message = "Quantity is required")
    @DecimalMin(value = "0.01", message = "Quantity must be greater than 0")
    private BigDecimal quantity;

    @JsonCreator
    public CreateOrderRequest(Product product, BigDecimal quantity) {
        this.product = product;
        this.quantity = quantity;
    }

}
