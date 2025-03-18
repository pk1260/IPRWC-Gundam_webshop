package com.example.iprwcgundam_webshop.seeder;

import com.example.iprwcgundam_webshop.dao.UserDAO;
import com.example.iprwcgundam_webshop.model.Role;
import com.example.iprwcgundam_webshop.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeeder {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger;

    @Value("${admin.name}")
    private String name;

    @Value("${admin.password}")
    private String password;

    public void seed() {
        var admin = User.builder()
                .username(name)
                .password(passwordEncoder.encode(password))
                .role(Role.ADMIN)
                .build();
        try {
            this.userDAO.save(admin);
        } catch (Exception e) {
            logger.error("Failed to seed admin user", e.getMessage());
        }
    }
}
