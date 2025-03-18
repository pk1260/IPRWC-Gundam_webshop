package com.example.iprwcgundam_webshop.controller;

import com.example.iprwcgundam_webshop.dto.AuthRequestDTO;
import com.example.iprwcgundam_webshop.dto.AuthResponseDTO;
import com.example.iprwcgundam_webshop.dto.RegisterResponseDTO;
import com.example.iprwcgundam_webshop.model.Role;
import com.example.iprwcgundam_webshop.model.User;
import com.example.iprwcgundam_webshop.repository.UserRepository;
import com.example.iprwcgundam_webshop.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO loginDTO) {
        String token = authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword());
        Role role = authenticationService.getRoleByUsername(loginDTO.getUsername());
        Optional<User> user = userRepository.findByUsername(loginDTO.getUsername());
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UUID id = user.get().getId();
        AuthResponseDTO response = new AuthResponseDTO(token, loginDTO.getUsername(), role, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody AuthRequestDTO registerDTO) {
        Optional<String> token = authenticationService.register(registerDTO.getUsername(), registerDTO.getPassword());
        if (!token.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String role = authenticationService.getRoleByUsername(registerDTO.getUsername()).name();
        RegisterResponseDTO response = new RegisterResponseDTO(token.get(), registerDTO.getUsername(), role);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
