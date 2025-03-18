//package com.example.iprwcgundam_webshop.controller;
//
//import com.example.iprwcgundam_webshop.dao.GradeDAO;
//import com.example.iprwcgundam_webshop.model.Grade;
//import lombok.RequiredArgsConstructor;
//import org.hibernate.engine.jdbc.Size;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/v1/grades")
//@RequiredArgsConstructor
//public class GradeController {
//    private final GradeDAO gradeDAO;
//
//    @GetMapping("/product/{productId}")
//    public ResponseEntity<List<Grade>> findByGradeId(@PathVariable UUID productId) {
//        List<Grade> grades = gradeDAO.findByGradeId(productId);
//        if (grades == null || grades.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(grades);
//    }
//}
