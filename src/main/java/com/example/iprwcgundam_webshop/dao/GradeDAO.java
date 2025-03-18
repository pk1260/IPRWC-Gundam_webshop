//package com.example.iprwcgundam_webshop.dao;
//
//import com.example.iprwcgundam_webshop.model.Grade;
//import com.example.iprwcgundam_webshop.model.Product;
//import com.example.iprwcgundam_webshop.repository.GradeRepository;
//import com.example.iprwcgundam_webshop.repository.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.UUID;
//
//@Component
//@RequiredArgsConstructor
//public class GradeDAO {
//    private final GradeRepository gradeRepository;
//    private final ProductRepository productRepository;
//
//    public List<Grade> findAll() {
//        return gradeRepository.findAll();
//    }
//
//    public Grade findById(UUID id) {
//        return gradeRepository.findById(id).orElse(null);
//    }
//
//    public List<Grade> findByGradeId(UUID productId) {
//        return gradeRepository.findByProductId(productId);
//    }
//
//    public Grade save(Grade grade) {
//        return gradeRepository.save(grade);
//    }
//
//    public Grade updateStock(UUID id, int newStock) {
//        Grade grade = gradeRepository.findById(id).orElse(null);
//        if (grade == null) {
//            return null;
//        }
//
//        grade.setStock(newStock);
//        return gradeRepository.save(grade);
//    }
//
//    public List<Grade> updateGradesOnProduct(UUID productId, List<Grade> grades) {
//        Product product = productRepository.findById(productId).orElse(null);
//        if (product == null) {
//            return null;
//        }
//
//        for (Grade grade : grades) {
//            grade.setProduct(product);
//            gradeRepository.save(grade);
//        }
//
//        return grades;
//    }
//}