package com.example.iprwcgundam_webshop.dto;

import com.example.iprwcgundam_webshop.model.Cart;
import com.example.iprwcgundam_webshop.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String username;
    private Role role;
    private Cart cart;
}
