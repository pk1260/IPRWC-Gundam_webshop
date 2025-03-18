package com.example.iprwcgundam_webshop.service;

import com.example.iprwcgundam_webshop.dao.UserDAO;
import com.example.iprwcgundam_webshop.model.Cart;
import com.example.iprwcgundam_webshop.model.Role;
import com.example.iprwcgundam_webshop.model.User;
import com.example.iprwcgundam_webshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public Optional<String> register(String username, String password) {
        Optional<User> foundUser = userDAO.findByUsername(username);
        if (foundUser.isPresent()) {
            return Optional.empty();
        }

        Cart cart = new Cart();

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(Role.CUSTOMER)
                .cart(cart)
                .build();

        cart.setUser(user);

        userDAO.save(user);
        String token = jwtService.generateToken(Map.of("role", user.getRole()), user.getId());
        return Optional.of(token);
    }

    public Role getRoleByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }

        return user.getRole();
    }

    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        User user = userDAO.loadUserByUsername(username);
        return jwtService.generateToken(Map.of("role", user.getRole()), user.getId());
    }

}
