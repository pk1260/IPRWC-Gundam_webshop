package com.example.iprwcgundam_webshop.controller;

import com.example.iprwcgundam_webshop.dao.CartDAO;
import com.example.iprwcgundam_webshop.dao.ProductDAO;
import com.example.iprwcgundam_webshop.dto.RemoveItemRequestDTO;
import com.example.iprwcgundam_webshop.dto.UpdateItemQuantityRequestDTO;
import com.example.iprwcgundam_webshop.model.Cart;
import com.example.iprwcgundam_webshop.model.CartItem;
import com.example.iprwcgundam_webshop.model.Product;
import com.example.iprwcgundam_webshop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartDAO cartDAO;
    private final ProductDAO productDAO;
    private final CartRepository cartRepository;

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

    @PutMapping(value = "/update-quantity/{userId}")
    public ResponseEntity<Cart> updateCartItemQuantity(@PathVariable UUID userId, @RequestBody UpdateItemQuantityRequestDTO request) {
        Cart cart = cartDAO.getCartByUserId(userId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        cartRepository.updateCartItemQuantity(request.getCartItemId(), request.getQuantity());
        Cart updatedCart = cartDAO.getCartByUserId(userId);
        return ResponseEntity.ok(updatedCart);
    }

    @PostMapping(value = "/remove/{userId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable UUID userId, @RequestBody RemoveItemRequestDTO request) {
        Cart cart = cartDAO.getCartByUserId(userId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        cartRepository.deleteCartItemById(request.getCartItemId());
        Cart updatedCart = cartDAO.getCartByUserId(userId);
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
