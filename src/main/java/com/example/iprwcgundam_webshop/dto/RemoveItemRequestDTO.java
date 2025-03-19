package com.example.iprwcgundam_webshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RemoveItemRequestDTO {
    private UUID cartItemId;
}