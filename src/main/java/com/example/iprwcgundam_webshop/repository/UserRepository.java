package com.example.iprwcgundam_webshop.repository;

import com.example.iprwcgundam_webshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<Set<User>> findAllByUsername(String username);
}
