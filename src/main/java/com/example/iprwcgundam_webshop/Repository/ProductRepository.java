package com.example.iprwcgundam_webshop.Repository;

import com.example.iprwcgundam_webshop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
