package com.example.iprwcgundam_webshop.controller;

import com.example.iprwcgundam_webshop.dao.UserDAO;
import com.example.iprwcgundam_webshop.model.Cart;
import com.example.iprwcgundam_webshop.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/user")
@RestController
//@PreAuthorize("hasRole('ADMIN')")
public class UserController extends User {
    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userDAO.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
        Optional<User> user = userDAO.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        if (user.getCart() == null) {
//            user.setCart(new Cart());
//        }
//        User createdUser = userDAO.save(user);
//        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<User> updateUserById(@PathVariable("id") UUID id, @RequestBody User user) {
//        Optional<User> existingUser = userDAO.findById(id);
//        if (existingUser.isPresent()) {
//            user.setId(id);
//            User updatedUser = userDAO.save(user);
//            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") UUID id) {
        Optional<User> user = userDAO.findById(id);
        if (user.isPresent()) {
            userDAO.delete(user.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
