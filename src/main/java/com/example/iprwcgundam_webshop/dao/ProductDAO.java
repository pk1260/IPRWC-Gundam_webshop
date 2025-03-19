package com.example.iprwcgundam_webshop.dao;

import com.example.iprwcgundam_webshop.model.Product;
import com.example.iprwcgundam_webshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductDAO extends Product {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<UUID> create(List<Product> products) {
        return productRepository.saveAll(products).stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public Product updateById(UUID id, Product updatedProduct) {
        if (productRepository.existsById(id)) {
            updatedProduct.setId(id);
            return productRepository.save(updatedProduct);
        } else {
            return null;
        }
    }

}
