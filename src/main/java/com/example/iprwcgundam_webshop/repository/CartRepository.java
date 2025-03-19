package com.example.iprwcgundam_webshop.repository;

import com.example.iprwcgundam_webshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    Cart findCartByUserId(@Param("userId") UUID userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem ci WHERE ci.cart.user.id = :userId")
    void deleteAllItemsByUserId(@Param("userId") UUID userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem ci WHERE ci.id = :cartItemId")
    void deleteCartItemById(@Param("cartItemId") UUID cartItemId);

    @Modifying
    @Transactional
    @Query("UPDATE CartItem ci SET ci.quantity = :quantity WHERE ci.id = :cartItemId")
    void updateCartItemQuantity(@Param("cartItemId") UUID cartItemId, @Param("quantity") int quantity);
}
