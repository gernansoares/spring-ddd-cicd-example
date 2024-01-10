package com.ddd.cdci.example.application.response;

import com.ddd.cdci.example.domain.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class CreateOrderResponse {

    private UUID id;

    @JsonCreator
    public CreateOrderResponse(UUID id) {
        this.id = id;
    }

}
