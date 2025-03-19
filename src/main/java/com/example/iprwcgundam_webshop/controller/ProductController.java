package com.example.iprwcgundam_webshop.controller;

import com.example.iprwcgundam_webshop.dao.ProductDAO;
import com.example.iprwcgundam_webshop.dto.UpdateProductRequestDTO;
import com.example.iprwcgundam_webshop.model.Product;
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
    @DeleteMapping(path = "/all")
    public ResponseEntity<Void> deleteAllProducts() {
        productDAO.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable UUID id, @RequestBody UpdateProductRequestDTO updatedProduct) {
        Product existingProduct = productDAO.findById(id);
        if (existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (updatedProduct.getName() != null) {
            existingProduct.setName(updatedProduct.getName());
        }
        if (updatedProduct.getDescription() != null) {
            existingProduct.setDescription(updatedProduct.getDescription());
        }
        if (updatedProduct.getPrice() != null) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }
        if (updatedProduct.getStock() != null) {
            existingProduct.setStock(updatedProduct.getStock());
        }
        if (updatedProduct.getGrade() != null) {
            existingProduct.setGrade(updatedProduct.getGrade());
        }

        Product updated = productDAO.save(existingProduct);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
