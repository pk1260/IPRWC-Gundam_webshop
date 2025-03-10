package com.example.iprwcgundam_webshop.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/product")
@RestController
public class ProductController {
    @GetMapping()
    public String getProducts() {
        return "All products";
    }
}
