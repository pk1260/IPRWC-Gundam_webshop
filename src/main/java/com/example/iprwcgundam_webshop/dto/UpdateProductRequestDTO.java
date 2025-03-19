package com.example.iprwcgundam_webshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequestDTO {
    private String name;
    private String description;
    private Float price;
    private Integer stock;
    private String grade;
}
