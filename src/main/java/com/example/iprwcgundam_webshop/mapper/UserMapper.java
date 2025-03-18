package com.example.iprwcgundam_webshop.mapper;

import com.example.iprwcgundam_webshop.dto.UserCreateDTO;
import com.example.iprwcgundam_webshop.dto.UserResponseDTO;
import com.example.iprwcgundam_webshop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public User toEntity(UserCreateDTO userCreateDTO) {
        return User.builder()
                .username(userCreateDTO.getUsername())
                .password(userCreateDTO.getPassword())
                .build();
    }

    public UserResponseDTO fromEntity(User user) {
        return UserResponseDTO
                .builder()
                .username(user.getUsername())
                .id(user.getId())
                .cart(user.getCart())
                .role(user.getRole())
                .build();
    }
}
