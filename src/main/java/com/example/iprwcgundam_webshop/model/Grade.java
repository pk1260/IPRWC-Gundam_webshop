//package com.example.iprwcgundam_webshop.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.UUID;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "_grade")
//public class Grade {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
//    private String grade;
//    private int stock;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    @JsonBackReference
//    private Product product;
//}
