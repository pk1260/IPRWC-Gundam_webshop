package com.example.iprwcgundam_webshop.dao;

import com.example.iprwcgundam_webshop.model.Cart;
import com.example.iprwcgundam_webshop.model.CartItem;
import com.example.iprwcgundam_webshop.model.Product;
import com.example.iprwcgundam_webshop.model.User;
import com.example.iprwcgundam_webshop.repository.CartRepository;
import com.example.iprwcgundam_webshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartDAO {
    private final ProductDAO productDAO;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public boolean isProductInStock(CartItem cartItem) {
        Product grade = productDAO.findById(cartItem.getProductId());
        System.out.println(grade);
        if (grade == null) {
            throw new IllegalArgumentException("Product not found " + cartItem.getProductId());
        }
        return grade.getStock() != 0 && grade.getStock() >= cartItem.getQuantity();
    }

    public void updateStock(CartItem cartItem) {
        Product grade = productDAO.findById(cartItem.getProductId());
        if (grade == null) {
            throw new IllegalArgumentException("Product not found " + cartItem.getProductId());
        }
        grade.setStock(grade.getStock() - cartItem.getQuantity());
        productDAO.save(grade);
    }

    public Cart saveCart(UUID userId, Cart cart) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found " + userId);
        }
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    public Cart getCartByUserId(UUID userId) {
        return cartRepository.findCartByUserId(userId);
    }

    public void clearCartByUserId(UUID userId) {
        cartRepository.deleteAllItemsByUserId(userId);
    }
}