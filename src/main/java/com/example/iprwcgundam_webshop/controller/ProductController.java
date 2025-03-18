package com.example.iprwcgundam_webshop.controller;

import com.example.iprwcgundam_webshop.dao.ProductDAO;
import com.example.iprwcgundam_webshop.model.Product;
import com.example.iprwcgundam_webshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductDAO productDAO;

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        Iterable<Product> products = productDAO.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(path = "/details/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Product product = productDAO.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<List<UUID>> create(@RequestBody List<Product> products) {
        List<UUID> createdProductIds = productDAO.create(products);
        return new ResponseEntity<>(createdProductIds, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable UUID id) {
        productDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable UUID id, @RequestBody Product updatedProduct) {
        Product product = productDAO.updateById(id, updatedProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
