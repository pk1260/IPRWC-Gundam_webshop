package com.example.iprwcgundam_webshop.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private String description;

    @Column
    private String imageUrl;

    public Product(String name, double price, String description, String imageUrl) {
        this.id = null;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Product() {
    }
}
