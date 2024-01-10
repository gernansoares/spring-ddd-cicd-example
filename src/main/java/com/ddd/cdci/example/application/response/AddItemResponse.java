package com.ddd.cdci.example.application.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AddItemResponse {

    private UUID id;

    @JsonCreator
    public AddItemResponse(UUID id) {
        this.id = id;
    }

}
