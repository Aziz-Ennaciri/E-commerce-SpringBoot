package com.ecommerce.aze_ecom.controller;

import com.ecommerce.aze_ecom.beans.category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class categoryController {
    private List<category> categories = new ArrayList<>();

    @GetMapping("/categories")
    public List<category> getAllCategories(){
        return categories;
    }
}
