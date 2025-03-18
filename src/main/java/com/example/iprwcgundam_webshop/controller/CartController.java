package com.example.iprwcgundam_webshop.controller;

import com.example.iprwcgundam_webshop.dao.CartDAO;
import com.example.iprwcgundam_webshop.dao.ProductDAO;
import com.example.iprwcgundam_webshop.model.Cart;
import com.example.iprwcgundam_webshop.model.CartItem;
import com.example.iprwcgundam_webshop.model.Product;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartDAO cartDAO;
    private final ProductDAO productDAO;

    @PostMapping(value = "/order/{userId}")
    public ResponseEntity<Map<String, String>> placeOrder(@PathVariable UUID userId) {
        Cart cart = cartDAO.getCartByUserId(userId);
        if (cart == null || cart.getCartItems().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Cart is empty or not found for user: " + userId));
        }

        for (CartItem item : cart.getCartItems()) {
            Product product = productDAO.findById(item.getProductId());
            if (product == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "Product with ID: " + item.getProductId() + " not found."));
            }
            if (!cartDAO.isProductInStock(item)) {
                return ResponseEntity.badRequest().body(Map.of("message", "Product with ID: " + item.getProductId() + " is out of stock."));
            }
            cartDAO.updateStock(item);
        }

        cartDAO.clearCartByUserId(userId);

        return new ResponseEntity<>(Map.of("message", "Order successful"), HttpStatus.OK);
    }

    @PostMapping(value = "/save/{userId}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable UUID userId, @RequestBody CartItem cartItem) {
        Cart cart = cartDAO.getCartByUserId(userId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        if (cart.getCartItems() == null) {
            cart.setCartItems(new ArrayList<>());
        }
        if (cartItem.getProductId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        cartItem.setCart(cart); // Set the Cart reference
        cart.getCartItems().add(cartItem);
        Cart updatedCart = cartDAO.saveCart(userId, cart);
        return ResponseEntity.ok(updatedCart);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable UUID userId) {
        Cart cart = cartDAO.getCartByUserId(userId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }
}
